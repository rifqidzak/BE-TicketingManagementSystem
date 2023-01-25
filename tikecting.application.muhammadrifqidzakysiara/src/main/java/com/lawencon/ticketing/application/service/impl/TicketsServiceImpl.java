package com.lawencon.ticketing.application.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.ticketing.application.constant.MessageEnum;
import com.lawencon.ticketing.application.constant.PrioritiesEnum;
import com.lawencon.ticketing.application.constant.StatusEnum;
import com.lawencon.ticketing.application.dao.AttachmentTicketsDao;
import com.lawencon.ticketing.application.dao.FilesDao;
import com.lawencon.ticketing.application.dao.PrioritiesDao;
import com.lawencon.ticketing.application.dao.ProductDao;
import com.lawencon.ticketing.application.dao.StatusDao;
import com.lawencon.ticketing.application.dao.TicketsDao;
import com.lawencon.ticketing.application.dao.UsersDao;
import com.lawencon.ticketing.application.dto.tickets.TicketDataDto;
import com.lawencon.ticketing.application.dto.tickets.TicketDto;
import com.lawencon.ticketing.application.dto.tickets.TicketsDto;
import com.lawencon.ticketing.application.dto.tickets.TicketInsertDataResDto;
import com.lawencon.ticketing.application.dto.tickets.TicketInsertResDto;
import com.lawencon.ticketing.application.dto.tickets.TicketUpdateDataResDto;
import com.lawencon.ticketing.application.dto.tickets.TicketUpdateResDto;
import com.lawencon.ticketing.application.dto.tickets.TicketsInsertDataReqDto;
import com.lawencon.ticketing.application.model.AttachmentsTickets;
import com.lawencon.ticketing.application.model.Files;
import com.lawencon.ticketing.application.model.Priorities;
import com.lawencon.ticketing.application.model.Product;
import com.lawencon.ticketing.application.model.Status;
import com.lawencon.ticketing.application.model.Tickets;
import com.lawencon.ticketing.application.model.Users;
import com.lawencon.ticketing.application.pojo.SendEmailPojo;
import com.lawencon.ticketing.application.service.GenerateService;
import com.lawencon.ticketing.application.service.JavaMailService;
import com.lawencon.ticketing.application.service.PrincipalService;
import com.lawencon.ticketing.application.service.TicketsService;

@Service
public class TicketsServiceImpl implements TicketsService {
	private final TicketsDao ticketsDao;
	private final StatusDao statusDao;
	private final FilesDao fileDao;
	private final AttachmentTicketsDao attachmentTicketsDao;
	private final GenerateService generate;
	private final PrioritiesDao prioritiesDao;
	private final ProductDao productDao;
	private final JavaMailService javaMailService;
	private final UsersDao usersDao;
	private final PrincipalService principalService;

	public TicketsServiceImpl(GenerateService generate, AttachmentTicketsDao attachmentTicketsDao,
			TicketsDao ticketsDao, StatusDao statusDao, FilesDao fileDao, PrioritiesDao prioritiesDao,
			ProductDao productDao, JavaMailService javaMailService, UsersDao usersDao,
			PrincipalService principalService) {
		this.ticketsDao = ticketsDao;
		this.statusDao = statusDao;
		this.fileDao = fileDao;
		this.attachmentTicketsDao = attachmentTicketsDao;
		this.generate = generate;
		this.prioritiesDao = prioritiesDao;
		this.productDao = productDao;
		this.javaMailService = javaMailService;
		this.usersDao = usersDao;
		this.principalService = principalService;
	}

	@Transactional
	@Override
	public TicketInsertResDto insert(TicketsInsertDataReqDto data) {

		Tickets ticketsInsert = new Tickets();
		ticketsInsert.setFillInTickets(data.getFillInTickets());
		ticketsInsert.setTitleTickets(data.getTitleTickets());
		ticketsInsert.setCustomersId(principalService.getPrincipal());
		final Optional<Status> stat = statusDao.getByCode(StatusEnum.S01.toString());
		ticketsInsert.setStatusId(stat.get());

		final Optional<Priorities> priority = prioritiesDao.getById(data.getPrioritiesId());
		ticketsInsert.setPrioritiesId(priority.get());
		if (priority.get().getPrioritiesCode().equals(PrioritiesEnum.P01.toString())) {
			final List<Tickets> tickets = ticketsDao.countPriority(priority.get().getPrioritiesCode(),
					principalService.getPrincipal().getId());
			if (tickets.size() >= 3) {
				throw new RuntimeException("Maximal Insert 3 Tickets High Priority In One Month");
			} else {
				final Optional<Product> product = productDao.getById(data.getProductId());
				ticketsInsert.setProductId(product.get());
				ticketsInsert.setTicketsCode(generate.generate(5));
				ticketsInsert.setCreatedBy(principalService.getPrincipal().getId());
				ticketsInsert = ticketsDao.insert(ticketsInsert);
			}
		} else if (priority.get().getPrioritiesCode().equals(PrioritiesEnum.P02.toString())) {
			final List<Tickets> tickets = ticketsDao.countPriority(priority.get().getPrioritiesCode(),
					principalService.getPrincipal().getId());
			if (tickets.size() >= 5) {
				throw new RuntimeException("Maximal Insert 5 Tickets High Priority In One Month");
			} else {
				final Optional<Product> product = productDao.getById(data.getProductId());
				ticketsInsert.setProductId(product.get());
				ticketsInsert.setTicketsCode(generate.generate(5));
				ticketsInsert.setCreatedBy(principalService.getPrincipal().getId());
				ticketsInsert = ticketsDao.insert(ticketsInsert);
			}
		} else {
			final Optional<Product> product = productDao.getById(data.getProductId());
			ticketsInsert.setProductId(product.get());
			ticketsInsert.setTicketsCode(generate.generate(5));
			ticketsInsert.setCreatedBy(principalService.getPrincipal().getId());
			ticketsInsert = ticketsDao.insert(ticketsInsert);
		}

		if (data.getFileInsertDataReqDtos() != null) {
			for (int i = 0; i < data.getFileInsertDataReqDtos().size(); i++) {
				final AttachmentsTickets attachmentsTickets = new AttachmentsTickets();
				final Files file = new Files();
				file.setFileName(data.getFileInsertDataReqDtos().get(i).getFileName());
				file.setExtensions(data.getFileInsertDataReqDtos().get(i).getExtensions());
				file.setCreatedBy(principalService.getPrincipal().getId());
				final Files fileInsert = fileDao.insert(file);
				attachmentsTickets.setTicketsId(ticketsInsert);
				attachmentsTickets.setFileId(fileInsert);
				attachmentsTickets.setCreatedBy(principalService.getPrincipal().getId());
				attachmentTicketsDao.insert(attachmentsTickets);
			}
		}

		final TicketInsertDataResDto ticketInsertDataResDto = new TicketInsertDataResDto();
		ticketInsertDataResDto.setId(ticketsInsert.getId());

		final TicketInsertResDto ticketInsertResDto = new TicketInsertResDto();
		ticketInsertResDto.setData(ticketInsertDataResDto);
		ticketInsertResDto.setMessage(MessageEnum.INSERTED.toString()+" "+"With Ticket Code : "+ticketsInsert.getTicketsCode());

		final SendEmailPojo sendEmailPojo = new SendEmailPojo();
		final Optional<Users> usersOptional = usersDao.getById(principalService.getPrincipal().getId());
		sendEmailPojo.setEmail(usersOptional.get().getPicId().getEmail());
		sendEmailPojo.setSubject(ticketsInsert.getTicketsCode());
		sendEmailPojo.setBody("A New Ticket Is Coming : " + ticketsInsert.getFillInTickets());
		final Runnable r = () -> javaMailService.sendEmail(sendEmailPojo);
		final Thread thread = new Thread(r);
		thread.start();

		return ticketInsertResDto;
	}

	@Override
	public TicketsDto getAllCustomersSides(final Long id) {
		final List<Tickets> tickets = ticketsDao.getAllCustomersSidesByCompany(id);
		final List<TicketDataDto> ticketDataDtos = new ArrayList<>();
		final List<Long> fileId = new ArrayList<>();
		for (int i = 0; i < tickets.size(); i++) {
			final List<AttachmentsTickets> attachmentsTickets = attachmentTicketsDao.getAllByTicketsId(tickets.get(i).getId());
			final TicketDataDto ticketDataDto = new TicketDataDto();
			for(int j = 0; j<attachmentsTickets.size(); j++) {
				fileId.add(attachmentsTickets.get(j).getFileId().getId());
			}
			ticketDataDto.setId(tickets.get(i).getId());
			ticketDataDto.setCustomerName(tickets.get(i).getCustomersId().getFullName());
			ticketDataDto.setFillInTickets(tickets.get(i).getFillInTickets());
			ticketDataDto.setTitle(tickets.get(i).getTitleTickets());
			ticketDataDto.setPrioritiesName(tickets.get(i).getPrioritiesId().getPrioritiesName());
			ticketDataDto.setTicketCode(tickets.get(i).getTicketsCode());
			ticketDataDto.setStatusName(tickets.get(i).getStatusId().getStatusName());
			ticketDataDto.setVer(tickets.get(i).getStatusId().getVer());
			ticketDataDto.setCreatedAt(tickets.get(i).getCreatedAt());
			ticketDataDto.setStatusCode(tickets.get(i).getStatusId().getStatusCode());
			ticketDataDtos.add(ticketDataDto);
		}
		
		final TicketsDto ticketDto = new TicketsDto();
		ticketDto.setData(ticketDataDtos);
		return ticketDto;
	}

	@Transactional
	@Override
	public TicketUpdateResDto update(final Long id) {
		final Optional<Tickets> ticketsOptional = ticketsDao.getById(id);
		final Optional<Status> status = statusDao.getById(ticketsOptional.get().getStatusId().getId());
		Tickets ticketsUpdate = new Tickets();
		if (ticketsOptional.isPresent()) {
			ticketsUpdate = ticketsOptional.get();
			final Status stat = new Status();

			final String bk;
			if (StatusEnum.S01.toString().equalsIgnoreCase(status.get().getStatusCode())
					|| StatusEnum.S02.toString().equalsIgnoreCase(status.get().getStatusCode())) {
				bk = StatusEnum.S03.toString();
			} else {
				bk = StatusEnum.S02.toString();
			}
			final Optional<Status> statusId = statusDao.getByCode(bk);
			stat.setId(statusId.get().getId());
			stat.setStatusName(statusId.get().getStatusName());
			stat.setVer(statusId.get().getVer());
			ticketsUpdate.setStatusId(stat);
			ticketsUpdate.setUpdatedBy(principalService.getPrincipal().getId());
			ticketsUpdate.setUpdatedAt(LocalDateTime.now());
		}
		ticketsUpdate = ticketsDao.update(ticketsUpdate);

		final TicketUpdateDataResDto ticketUpdateDataResDto = new TicketUpdateDataResDto();
		ticketUpdateDataResDto.setVer(ticketsUpdate.getVer());

		final TicketUpdateResDto ticketUpdateResDto = new TicketUpdateResDto();
		ticketUpdateResDto.setData(ticketUpdateDataResDto);
		ticketUpdateResDto.setMessage(MessageEnum.UPDATED.toString());
		return ticketUpdateResDto;
	}

	@Override
	public TicketsDto getAllPicSides(final Long id) {
		final List<Tickets> tickets = ticketsDao.getAllPicSides(id);
		final List<TicketDataDto> ticketDataDtos = new ArrayList<>();
		final List<Long> fileId = new ArrayList<>();
		for (int i = 0; i < tickets.size(); i++) {
			final List<AttachmentsTickets> attachmentsTickets = attachmentTicketsDao.getAllByTicketsId(tickets.get(i).getId());
			final TicketDataDto ticketDataDto = new TicketDataDto();
			for(int j = 0; j<attachmentsTickets.size(); j++) {
				fileId.add(attachmentsTickets.get(j).getFileId().getId());
			}
			ticketDataDto.setFileId(fileId);
			ticketDataDto.setId(tickets.get(i).getId());
			ticketDataDto.setCustomerName(tickets.get(i).getCustomersId().getFullName());
			ticketDataDto.setFillInTickets(tickets.get(i).getFillInTickets());
			ticketDataDto.setTitle(tickets.get(i).getTitleTickets());
			ticketDataDto.setPrioritiesName(tickets.get(i).getPrioritiesId().getPrioritiesName());
			ticketDataDto.setTicketCode(tickets.get(i).getTicketsCode());
			ticketDataDto.setStatusName(tickets.get(i).getStatusId().getStatusName());
			ticketDataDto.setVer(tickets.get(i).getStatusId().getVer());
			ticketDataDto.setCreatedAt(tickets.get(i).getCreatedAt());
			ticketDataDto.setStatusCode(tickets.get(i).getStatusId().getStatusCode()); 	
			ticketDataDtos.add(ticketDataDto);
		}

		final TicketsDto ticketDto = new TicketsDto();
		ticketDto.setData(ticketDataDtos);
		return ticketDto;
	}

	@Override
	public TicketDto getById(Long id) {
		final Optional<Tickets> ticketsOptional = ticketsDao.getById(id);
		final TicketDataDto ticketDataDto = new TicketDataDto();
		final List<Long> fileId = new ArrayList<>();
		final List<AttachmentsTickets> attachmentsTickets = attachmentTicketsDao.getAllByTicketsId(ticketsOptional.get().getId());
		for(int i = 0; i<attachmentsTickets.size(); i++) {
			fileId.add(attachmentsTickets.get(i).getFileId().getId());
		}
		
		if(ticketsOptional.isPresent()) {
			ticketDataDto.setFileId(fileId);
			ticketDataDto.setId(ticketsOptional.get().getId());
			ticketDataDto.setCustomerName(ticketsOptional.get().getCustomersId().getFullName());
			ticketDataDto.setFillInTickets(ticketsOptional.get().getFillInTickets());
			ticketDataDto.setTitle(ticketsOptional.get().getTitleTickets());
			ticketDataDto.setPrioritiesName(ticketsOptional.get().getPrioritiesId().getPrioritiesName());
			ticketDataDto.setTicketCode(ticketsOptional.get().getTicketsCode());
			ticketDataDto.setStatusName(ticketsOptional.get().getStatusId().getStatusName());
			ticketDataDto.setVer(ticketsOptional.get().getStatusId().getVer());
			ticketDataDto.setCreatedAt(ticketsOptional.get().getCreatedAt());
			ticketDataDto.setStatusCode(ticketsOptional.get().getStatusId().getStatusCode());
		}
		final TicketDto ticketDto = new TicketDto();
		ticketDto.setData(ticketDataDto);
		return ticketDto;
	}

}
