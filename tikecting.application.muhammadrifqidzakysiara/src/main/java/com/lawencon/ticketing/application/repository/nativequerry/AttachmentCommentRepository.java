package com.lawencon.ticketing.application.repository.nativequerry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.application.model.AttachmentsComments;

@Repository
public interface AttachmentCommentRepository extends JpaRepository<AttachmentsComments, Long> {
	@Query(value = "select * from attachments_comments ac2 where comments_id = :commentId", nativeQuery = true)
	List<AttachmentsComments> getAllByCommentsId(@Param("commentId") Long commentId);
}
