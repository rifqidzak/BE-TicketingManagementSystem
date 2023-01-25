package com.lawencon.ticketing.application.repository.nativequerry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.model.AttachmentsTickets;

@Repository
public interface AttachmentTicketRepository extends JpaRepository<AttachmentsTickets, Long> {
	
	@Query(value = "select * from attachments_tickets at2 where tickets_id = :ticketId", nativeQuery = true)
	List<AttachmentsTickets> getAllByTicketsId(@Param("ticketId") Long ticketId);
}
