package com.lawencon.ticketing.application.dao.impl.nativequerry;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.dao.TicketsDao;
import com.lawencon.ticketing.application.model.Tickets;
@Profile("native")
@Repository
public class TicketsDaoImpl extends BaseDaoImpl implements TicketsDao{


	@Override
	public Tickets insert(Tickets data)  {
		this.em.persist(data);
		
		return data;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List <Tickets> getAllCustomersSides(final Long id)  {
		final String sql = "SELECT * FROM tickets "
				+ " INNER JOIN product ON product.id = tickets.product_id "
				+ " INNER JOIN status ON status.id = tickets.status_id "
				+ " INNER JOIN priorities ON priorities.id = tickets.priorities_id "
				+ " INNER JOIN users ON users.id = tickets.customers_id WHERE users.id = :userId";
		final List<Tickets> tickets = this.em.createNativeQuery(sql, Tickets.class)
				.setParameter("userId", id)
				.getResultList();
		return tickets;
	}

	@Override
	public Tickets update(final Tickets data)  {
		final Tickets ticketUpdated = this.em.merge(data);
		this.em.flush();
		return ticketUpdated;
	}

	@Override
	public Optional<Tickets> getById(final Long id)  {

		final Tickets tickets = this.em.find(Tickets.class, id);
		final Optional<Tickets> ticketsOptional = Optional.ofNullable(tickets);
		return ticketsOptional;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tickets> getAllPicSides(final Long id)  {
		final String sql = "SELECT * FROM tickets "
				+ " INNER JOIN product ON product.id = tickets.product_id "
				+ " INNER JOIN status ON status.id = tickets.status_id "
				+ " INNER JOIN priorities ON priorities.id = tickets.priorities_id "
				+ " INNER JOIN users ON users.id = tickets.customers_id WHERE pic_id = :userId";
		final List<Tickets> tickets = this.em.createNativeQuery(sql, Tickets.class)
				.setParameter("userId", id)
				.getResultList();
		return tickets;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tickets> countPriority(final String code, final Long customersId) {
		final String sql = "SELECT * from tickets t "
				+ "INNER JOIN priorities p ON p.id = t.priorities_id "
				+ "WHERE extract (month from t.created_at) = extract(month from now()) "
				+ "AND p.priorities_code ilike :code AND t.customers_id = :customersId" ;
		final List<Tickets> tickets = this.em.createNativeQuery(sql, Tickets.class)
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
