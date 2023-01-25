package com.lawencon.ticketing.application.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "attachments_comments")
public class AttachmentsComments extends BaseModel{
	@ManyToOne
	@JoinColumn(name = "files_id")
	private Files fileId;
	
	@ManyToOne
	@JoinColumn(name = "comments_id")
	private Comments commentsId;
	
	public Comments getCommentsId() {
		return commentsId;
	}
	public void setCommentsId(Comments commentsId) {
		this.commentsId = commentsId;
	}
	public Files getFileId() {
		return fileId;
	}
	public void setFileId(Files fileId) {
		this.fileId = fileId;
	}

	
}
	

