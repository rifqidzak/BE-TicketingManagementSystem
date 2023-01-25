package com.lawencon.ticketing.application.dto.comments;

import java.time.LocalDateTime;
import java.util.List;

public class CommentDataDto {
	private Long id;
	private String fillInTheComments;
	private String usersName;
	private Integer ver;
	private List<Long> fileId;
	private Long createdBy;
	private LocalDateTime createdAt;
	private Long photoId;
	
	public Long getPhotoId() {
		return photoId;
	}

	public void setPhotoId(Long photoId) {
		this.photoId = photoId;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public List<Long> getFileId() {
		return fileId;
	}

	public void setFileId(List<Long> fileId) {
		this.fileId = fileId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVer() {
		return ver;
	}

	public void setVer(Integer ver) {
		this.ver = ver;
	}

	public String getFillInTheComments() {
		return fillInTheComments;
	}

	public void setFillInTheComments(String fillInTheComments) {
		this.fillInTheComments = fillInTheComments;
	}

	public String getUsersName() {
		return usersName;
	}

	public void setUsersName(String usersName) {
		this.usersName = usersName;
	}

}
