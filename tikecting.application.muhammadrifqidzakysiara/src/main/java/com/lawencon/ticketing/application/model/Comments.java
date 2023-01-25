package com.lawencon.ticketing.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Comments extends BaseModel{
	@Column(name = "fill_in_the_comments")
	private String fillInTheComment;
	
	@ManyToOne
	@JoinColumn(name = "comments_id")
	private Comments commentId;
	
	@ManyToOne
	@JoinColumn(name = "users_id")
	private Users userId;
	
	@ManyToOne
	@JoinColumn(name = "tickets_id")
	private Tickets ticketsId;
	
	public String getFillInTheComment() {
		return fillInTheComment;
	}
	public void setFillInTheComment(String fillInTheComment) {
		this.fillInTheComment = fillInTheComment;
	}
	public Comments getCommentId() {
		return commentId;
	}
	public void setCommentId(Comments commentId) {
		this.commentId = commentId;
	}
	public Users getUserId() {
		return userId;
	}
	public void setUserId(Users userId) {
		this.userId = userId;
	}
	public Tickets getTicketsId() {
		return ticketsId;
	}
	public void setTicketsId(Tickets ticketsId) {
		this.ticketsId = ticketsId;
	}
	
	
}
