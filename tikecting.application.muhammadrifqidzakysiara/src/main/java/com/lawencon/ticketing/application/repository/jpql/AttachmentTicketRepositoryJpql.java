package com.lawencon.ticketing.application.repository.jpql;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.model.AttachmentsTickets;

@Repository
public interface AttachmentTicketRepositoryJpql extends JpaRepository<AttachmentsTickets, Long> {
	
	@Query(value = "select at from AttachmentsTickets as at "
			+ " INNER JOIN at.ticketsId as t "
			+ "	where t.id = :ticketId")
	List<AttachmentsTickets> getAllByTicketsId(@Param("ticketId") Long ticketId);
}
