package com.lawencon.ticketing.application.dao.impl.hql;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.dao.TicketsDao;
import com.lawencon.ticketing.application.dao.impl.nativequerry.BaseDaoImpl;
import com.lawencon.ticketing.application.model.Tickets;

@Profile("hql")
@Repository
public class TicketsDaoImpl extends BaseDaoImpl implements TicketsDao {

	@Override
	public Tickets insert(final Tickets data) {
		this.em.persist(data);

		return data;
	}

	@Override
	public List<Tickets> getAllCustomersSides(final Long id) {
		final String sql = "SELECT t FROM Tickets t" + " INNER JOIN FETCH t.productId "
				+ " INNER JOIN FETCH t.statusId " + " INNER JOIN FETCH t.prioritiesId "
				+ " INNER JOIN FETCH t.customersId " + " WHERE t.customersId.id = :userId";
		final List<Tickets> tickets = this.em.createQuery(sql, Tickets.class).setParameter("userId", id)
				.getResultList();
		return tickets;
	}

	@Override
	public Tickets update(final Tickets data) {
		final Tickets ticketUpdated = this.em.merge(data);
		this.em.flush();
		return ticketUpdated;
	}

	@Override
	public Optional<Tickets> getById(final Long id) {
		final Tickets tickets = this.em.find(Tickets.class, id);
		final Optional<Tickets> ticketsOptional = Optional.ofNullable(tickets);
		return ticketsOptional;
	}

	@Override
	public List<Tickets> getAllPicSides(final Long id) {
		final String sql = "SELECT t FROM Tickets t" + " INNER JOIN t.productId " + " INNER JOIN t.statusId "
				+ " INNER JOIN t.prioritiesId " + " INNER JOIN t.customersId WHERE t.customersId.picId.id = :userId";
		final List<Tickets> tickets = this.em.createQuery(sql, Tickets.class).setParameter("userId", id)
				.getResultList();
		return tickets;
	}

	@Override
	public List<Tickets> countPriority(final String code, final Long customersId) {
		final String sql = "SELECT t from Tickets t "
				+ "	INNER JOIN FETCH t.customersId c"
				+ " INNER JOIN FETCH t.prioritiesId p "
				+ " WHERE month(t.createdAt) = month(current_timestamp()) "
				+ " AND p.prioritiesCode = :code AND c.id = :customersId" ;
		final List<Tickets> tickets = this.em.createQuery(sql, Tickets.class)
				.setParameter("code", code)
				.setParameter("customersId", customersId)
				.getResultList();
		return tickets;
	}

	@Override
	public List<Tickets> getAllCustomersSidesByCompany(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
