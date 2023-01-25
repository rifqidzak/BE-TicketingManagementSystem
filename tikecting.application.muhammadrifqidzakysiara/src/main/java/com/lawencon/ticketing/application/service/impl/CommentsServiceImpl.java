package com.lawencon.ticketing.application.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.ticketing.application.constant.RoleEnum;
import com.lawencon.ticketing.application.dao.AttachmentCommentsDao;
import com.lawencon.ticketing.application.dao.CommentsDao;
import com.lawencon.ticketing.application.dao.FilesDao;
import com.lawencon.ticketing.application.dao.TicketsDao;
import com.lawencon.ticketing.application.dao.UsersDao;
import com.lawencon.ticketing.application.dto.comments.CommentDataDto;
import com.lawencon.ticketing.application.dto.comments.CommentDto;
import com.lawencon.ticketing.application.dto.comments.CommentInsertDataReqDto;
import com.lawencon.ticketing.application.dto.comments.CommentInsertDataResDto;
import com.lawencon.ticketing.application.dto.comments.CommentInsertResDto;
import com.lawencon.ticketing.application.model.AttachmentsComments;
import com.lawencon.ticketing.application.model.Comments;
import com.lawencon.ticketing.application.model.Files;
import com.lawencon.ticketing.application.model.Tickets;
import com.lawencon.ticketing.application.model.Users;
import com.lawencon.ticketing.application.pojo.SendEmailPojo;
import com.lawencon.ticketing.application.service.CommentsService;
import com.lawencon.ticketing.application.service.JavaMailService;
import com.lawencon.ticketing.application.service.PrincipalService;

@Service
public class CommentsServiceImpl implements CommentsService {
	private final FilesDao fileDao;
	private final CommentsDao commentsDao;
	private final AttachmentCommentsDao attachmentCommentsDao;
	private final UsersDao usersDao;
	private final TicketsDao ticketsDao;
	private final PrincipalService principalService;
	private final JavaMailService javaMailService;

	public CommentsServiceImpl(FilesDao fileDao, CommentsDao commentsDao, AttachmentCommentsDao attachmentCommentsDao,
			UsersDao usersDao, TicketsDao ticketsDao, PrincipalService principalService,
			JavaMailService javaMailService) {
		this.attachmentCommentsDao = attachmentCommentsDao;
		this.fileDao = fileDao;
		this.commentsDao = commentsDao;
		this.usersDao = usersDao;
		this.ticketsDao = ticketsDao;
		this.principalService = principalService;
		this.javaMailService = javaMailService;
	}

	@Transactional
	@Override
	public CommentInsertResDto insert(CommentInsertDataReqDto data) {

		Comments commentsInsert = new Comments();
		final Optional<Users> usersOptional = usersDao.getById(principalService.getPrincipal().getId());
		commentsInsert.setUserId(usersOptional.get());

		final Optional<Tickets> ticketsOptional = ticketsDao.getById(data.getTicketsId());
		commentsInsert.setTicketsId(ticketsOptional.get());

		commentsInsert.setFillInTheComment(data.getFillInTheComment());
		commentsInsert.setCreatedBy(principalService.getPrincipal().getId());
		commentsInsert = commentsDao.insert(commentsInsert);

		if (data.getFileInsertDataReqDtos() != null) {
			for (int i = 0; i < data.getFileInsertDataReqDtos().size(); i++) {
				final AttachmentsComments attachmentsComments = new AttachmentsComments();
				final Files file = new Files();
				file.setFileName(data.getFileInsertDataReqDtos().get(i).getFileName());
				file.setExtensions(data.getFileInsertDataReqDtos().get(i).getExtensions());
				file.setCreatedBy(principalService.getPrincipal().getId());
				Files fileInsert = fileDao.insert(file);
				attachmentsComments.setCommentsId(commentsInsert);
				attachmentsComments.setFileId(fileInsert);
				attachmentsComments.setCreatedBy(principalService.getPrincipal().getId());
				attachmentCommentsDao.insert(attachmentsComments);
			}
		}

		final CommentInsertDataResDto commentInsertDataResDto = new CommentInsertDataResDto();
		commentInsertDataResDto.setId(commentsInsert.getId());

		final CommentInsertResDto commentInsertResDto = new CommentInsertResDto();
		commentInsertResDto.setData(commentInsertDataResDto);
		commentInsertResDto.setMessage("Comment success");
		if (RoleEnum.RS02.toString().equals(principalService.getPrincipal().getRoleUserId().getRoleUserCode())) {
			final Optional<Users> customer = usersDao.getById(principalService.getPrincipal().getId());
			final SendEmailPojo sendEmailPojo = new SendEmailPojo();
			sendEmailPojo.setEmail(customer.get().getPicId().getEmail());
			sendEmailPojo.setSubject(
					"Your Comment Has Been Reply In Ticket Code : " + ticketsOptional.get().getTicketsCode());
			sendEmailPojo.setBody("Comment : " + commentsInsert.getFillInTheComment());
			final Runnable r = () -> javaMailService.sendEmail(sendEmailPojo);
			final Thread thread = new Thread(r);
			thread.start();
		} else {
			final SendEmailPojo sendEmailPojo = new SendEmailPojo();
			sendEmailPojo.setEmail(ticketsOptional.get().getCustomersId().getEmail());
			sendEmailPojo.setSubject(
					"Your Comment Has Been Reply In Ticket Code : " + ticketsOptional.get().getTicketsCode());
			sendEmailPojo.setBody("Comment : " + commentsInsert.getFillInTheComment());
			final Runnable r = () -> javaMailService.sendEmail(sendEmailPojo);
			final Thread thread = new Thread(r);
			thread.start();
		}
		return commentInsertResDto;
	}

	@Override
	public CommentDto getAllComment(Long id) {
		final List<Comments> comments = commentsDao.getAll(id);
		final List<CommentDataDto> commentDataDtos = new ArrayList<>();
		for (int i = 0; i < comments.size(); i++) {
			final List<AttachmentsComments> attachmentsComments = attachmentCommentsDao.getAllByCommentsId(comments.get(i).getId());
			final CommentDataDto commentDataDto = new CommentDataDto();
			final List<Long> fileId = new ArrayList<>();
			for(int j = 0; j<attachmentsComments.size(); j++) {
				fileId.add(attachmentsComments.get(j).getFileId().getId());
			}
			commentDataDto.setFileId(fileId);
			commentDataDto.setFillInTheComments(comments.get(i).getFillInTheComment());
			commentDataDto.setUsersName(comments.get(i).getUserId().getFullName());
			commentDataDto.setVer(comments.get(i).getUserId().getVer());
			commentDataDto.setCreatedAt((comments.get(i).getCreatedAt()));
			commentDataDto.setCreatedBy((comments.get(i).getCreatedBy()));
			commentDataDto.setPhotoId(comments.get(i).getUserId().getFilesId().getId());
//			Optional<Users> userOptional = usersDao.getById(comments.get(i).getCreatedBy());
//			commentDataDto.setPhotoId(userOptional.get().getFilesId().getId());
			commentDataDtos.add(commentDataDto);
		}
		final CommentDto commentDto = new CommentDto();
		commentDto.setData(commentDataDtos);
		return commentDto;
	}

}
