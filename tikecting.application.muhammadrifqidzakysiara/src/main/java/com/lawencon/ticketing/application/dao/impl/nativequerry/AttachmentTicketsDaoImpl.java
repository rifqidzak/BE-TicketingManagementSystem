package com.lawencon.ticketing.application.dao.impl.nativequerry;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.dao.AttachmentTicketsDao;
import com.lawencon.ticketing.application.model.AttachmentsTickets;

@Profile("native")
@Repository
public class AttachmentTicketsDaoImpl extends BaseDaoImpl implements AttachmentTicketsDao {

	@Override
	public AttachmentsTickets insert(final AttachmentsTickets data) {
		this.em.persist(data);

		return data;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AttachmentsTickets> getAllByTicketsId(Long ticketId) {

		final String sql = "select * from attachments_tickets at2 where tickets_id = :ticketId";
		List<AttachmentsTickets> attachmentsTickets = this.em.createNativeQuery(sql, AttachmentsTickets.class)
				.setParameter("ticketId", ticketId).getResultList();
		return attachmentsTickets;
	}

}
