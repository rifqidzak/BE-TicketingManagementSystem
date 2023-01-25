package com.lawencon.ticketing.application.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "attachments_tickets")
public class AttachmentsTickets extends BaseModel{
	
	@ManyToOne
	@JoinColumn(name = "files_id")
	private Files fileId;
	
	@ManyToOne
	@JoinColumn(name = "tickets_id")
	private Tickets ticketsId;
	
	public Files getFileId() {
		return fileId;
	}
	
	public void setFileId(Files fileId) {
		this.fileId = fileId;
	}
	
	public Tickets getTicketsId() {
		return ticketsId;
	}
	
	public void setTicketsId(Tickets ticketsId) {
		this.ticketsId = ticketsId;
	}
	
	
	
	
}
