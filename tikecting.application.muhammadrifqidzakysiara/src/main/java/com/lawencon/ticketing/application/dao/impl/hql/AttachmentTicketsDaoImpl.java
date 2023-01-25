package com.lawencon.ticketing.application.dao.impl.hql;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.dao.AttachmentTicketsDao;
import com.lawencon.ticketing.application.dao.impl.nativequerry.BaseDaoImpl;
import com.lawencon.ticketing.application.model.AttachmentsTickets;

@Profile("hql")
@Repository
public class AttachmentTicketsDaoImpl extends BaseDaoImpl implements AttachmentTicketsDao {

	@Override
	public AttachmentsTickets insert(final AttachmentsTickets data) {
		this.em.persist(data);

		return data;
	}

	@Override
	public List<AttachmentsTickets> getAllByTicketsId(Long ticketId) {
		final String sql = "select at from AttachmentsTickets as at "
				+ " INNER JOIN at.ticketsId as t "
				+ "	where t.id = :ticketId";
		List<AttachmentsTickets> attachmentsTickets = this.em.createQuery(sql, AttachmentsTickets.class)
				.setParameter("ticketId", ticketId).getResultList();
		return attachmentsTickets;
	}

}
