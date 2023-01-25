package com.lawencon.ticketing.application.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lawencon.ticketing.application.constant.MessageEnum;
import com.lawencon.ticketing.application.dao.CompaniesDao;
import com.lawencon.ticketing.application.dao.FilesDao;
import com.lawencon.ticketing.application.dao.RoleDao;
import com.lawencon.ticketing.application.dao.UsersDao;
import com.lawencon.ticketing.application.dto.users.UserDto;
import com.lawencon.ticketing.application.dto.users.UsersChangePasswordReqDataDto;
import com.lawencon.ticketing.application.dto.users.UsersChangePasswordResDataDto;
import com.lawencon.ticketing.application.dto.users.UsersChangePasswordResDto;
import com.lawencon.ticketing.application.dto.users.UsersDataDto;
import com.lawencon.ticketing.application.dto.users.UsersDeleteResDto;
import com.lawencon.ticketing.application.dto.users.UsersDto;
import com.lawencon.ticketing.application.dto.users.UsersInsertResDataDto;
import com.lawencon.ticketing.application.dto.users.UsersInsertReqDataDto;
import com.lawencon.ticketing.application.dto.users.UsersInsertResDto;
import com.lawencon.ticketing.application.dto.users.UsersUpdateDataReqDto;
import com.lawencon.ticketing.application.dto.users.UsersUpdateDataResDto;
import com.lawencon.ticketing.application.dto.users.UsersUpdateResDto;
import com.lawencon.ticketing.application.model.Companies;
import com.lawencon.ticketing.application.model.Files;
import com.lawencon.ticketing.application.model.RoleUsers;
import com.lawencon.ticketing.application.model.Users;
import com.lawencon.ticketing.application.pojo.SendEmailPojo;
import com.lawencon.ticketing.application.service.GenerateService;
import com.lawencon.ticketing.application.service.JavaMailService;
import com.lawencon.ticketing.application.service.PrincipalService;
import com.lawencon.ticketing.application.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {
	PrincipalService principalService;
	private final UsersDao usersDao;
	private final RoleDao roleDao;
	private final CompaniesDao companiesDao;
	private final GenerateService generateService;
	private final JavaMailService javaMailService;
	private final PasswordEncoder passwordEncoder;
	private final FilesDao filesDao;

	public UsersServiceImpl(UsersDao usersDao, RoleDao roleDao, CompaniesDao companiesDao,
			GenerateService generateService, JavaMailService javaMailService, PasswordEncoder passwordEncoder,
			PrincipalService principalService, FilesDao filesDao) {
		this.usersDao = usersDao;
		this.roleDao = roleDao;
		this.companiesDao = companiesDao;
		this.generateService = generateService;
		this.javaMailService = javaMailService;
		this.passwordEncoder = passwordEncoder;
		this.principalService = principalService;
		this.filesDao = filesDao;
	}

	@Transactional
	@Override
	public UsersInsertResDto insert(final UsersInsertReqDataDto data) {
		Users usersInsert = new Users();
		usersInsert.setEmail(data.getEmail());
		usersInsert.setPassword(generateService.generate(5));
		usersInsert.setFullName(data.getFullName());
		usersInsert.setCreatedBy(principalService.getPrincipal().getId());

		if (data.getRoleUserId() != null) {
			final Optional<RoleUsers> roleUsersOptional = roleDao.getById(data.getRoleUserId());
			usersInsert.setRoleUserId(roleUsersOptional.get());
		}

		if (data.getCompaniesId() != null) {
			final Optional<Companies> companiesOptional = companiesDao.getById(data.getCompaniesId());
			usersInsert.setCompaniesId(companiesOptional.get());
		}

		if (data.getPicId() != null) {
			final Optional<Users> usersOptional = usersDao.getById(data.getPicId());
			usersInsert.setPicId(usersOptional.get());
		}

		if (data.getPhoto() != null) {
			Files files = new Files();
			files.setFileName(data.getPhoto());
			files.setExtensions(data.getExtensionPhoto());
			files.setCreatedBy(principalService.getPrincipal().getId());
			files = filesDao.insert(files);
			usersInsert.setFilesId(files);
		}

		final String passwordEmail = usersInsert.getPassword();
		final String hashPassword = passwordEncoder.encode(usersInsert.getPassword());

		usersInsert.setPassword(hashPassword);
		usersInsert = usersDao.insert(usersInsert);

		final SendEmailPojo createdAccountPojo = new SendEmailPojo();
		createdAccountPojo.setEmail(usersInsert.getEmail());
		createdAccountPojo.setSubject("You Can Check Your Account Ticketing !");
		createdAccountPojo.setBody("Your Account : \n" + " " + " Email : " + usersInsert.getEmail() + "\n"
				+ "Password : " + passwordEmail);

		final Runnable r = () -> javaMailService.sendEmail(createdAccountPojo);
		final Thread thread = new Thread(r);
		thread.start();

		final UsersInsertResDataDto usersInsertDataResDto = new UsersInsertResDataDto();
		usersInsertDataResDto.setId(usersInsert.getId());

		final UsersInsertResDto usersInsertResDto = new UsersInsertResDto();
		usersInsertResDto.setData(usersInsertDataResDto);

		usersInsertResDto.setMessage("The data has been successfully input");
		return usersInsertResDto;
	}

	@Transactional
	@Override
	public UsersUpdateResDto update(final UsersUpdateDataReqDto data) {
		final Optional<Users> userOptional = usersDao.getById(data.getId());
		Users usersUpdate = new Users();

		if (userOptional.isPresent()) {
			usersUpdate = userOptional.get();

			if (data.getFullName() != null) {
				usersUpdate.setFullName(data.getFullName());
			}
			if (data.getPicId() != null) {
				final Optional<Users> picOptional = usersDao.getById(data.getPicId());
				usersUpdate.setPicId(picOptional.get());
			}
			if (data.getRoleUsersId() != null) {
				final Optional<RoleUsers> roleUsersOptional = roleDao.getById(data.getRoleUsersId());
				usersUpdate.setRoleUserId(roleUsersOptional.get());
			}
			if (data.getCompaniesId() != null) {
				final Optional<Companies> companiesOptional = companiesDao.getById(data.getCompaniesId());
				usersUpdate.setCompaniesId(companiesOptional.get());
			}
			if (data.getPhoto() != null) {
				Files files = new Files();
				files.setFileName(data.getPhoto());
				files.setExtensions(data.getExtensionPhoto());
				files.setCreatedBy(principalService.getPrincipal().getId());
				files = filesDao.insert(files);
				usersUpdate.setFilesId(files);
			}
			usersUpdate.setIsActive(data.getIsActive());
			usersUpdate.setVer(data.getVer());
			usersUpdate.setUpdatedBy(principalService.getPrincipal().getId());
		}

		usersUpdate = usersDao.update(usersUpdate);
		final UsersUpdateDataResDto userUpdateDataResDto = new UsersUpdateDataResDto();
		userUpdateDataResDto.setVer(usersUpdate.getVer());

		final UsersUpdateResDto usersUpdateResDto = new UsersUpdateResDto();
		usersUpdateResDto.setData(userUpdateDataResDto);
		usersUpdateResDto.setMessage("The data has been successfully update");
		return usersUpdateResDto;
	}

	@Override
	public UserDto getById(final Long id) {
		final Optional<Users> userOptional = usersDao.getById(id);
		final UsersDataDto usersDataDto = new UsersDataDto();
		usersDataDto.setFullName(userOptional.get().getFullName());
		usersDataDto.setId(userOptional.get().getId());
		usersDataDto.setVer(userOptional.get().getVer());
		usersDataDto.setRoleName(userOptional.get().getRoleUserId().getRoleUserName());
		usersDataDto.setEmail(userOptional.get().getEmail());
		usersDataDto.setRoleId(userOptional.get().getRoleUserId().getId());
		usersDataDto.setIsActive(userOptional.get().getIsActive());
		if (userOptional.get().getCompaniesId() != null) {
			usersDataDto.setCompaniesName(userOptional.get().getCompaniesId().getCompaniesName());
			usersDataDto.setCompaniesId(userOptional.get().getCompaniesId().getId());
		}

		if (userOptional.get().getPicId() != null) {
			usersDataDto.setPicName(userOptional.get().getPicId().getFullName());
			usersDataDto.setPicId(userOptional.get().getPicId().getId());
		}

		if (userOptional.get().getFilesId() != null) {
			usersDataDto.setPhotoId(userOptional.get().getFilesId().getId());
		}

		final UserDto userDto = new UserDto();
		userDto.setData(usersDataDto);

		return userDto;
	}

	@Override
	public UsersDto getAll() {
		final List<Users> users = usersDao.getAll();
		final List<UsersDataDto> usersDataDtos = new ArrayList<>();

		for (int i = 0; i < users.size(); i++) {
			final UsersDataDto usersDataDto = new UsersDataDto();
			usersDataDto.setFullName(users.get(i).getFullName());
			usersDataDto.setVer(users.get(i).getVer());
			usersDataDto.setRoleName(users.get(i).getRoleUserId().getRoleUserName());
			usersDataDto.setEmail(users.get(i).getEmail());
			usersDataDto.setIsActive(users.get(i).getIsActive());
			usersDataDto.setRoleId(users.get(i).getRoleUserId().getId());
			usersDataDto.setIsActive(users.get(i).getIsActive());
			if (users.get(i).getPicId() != null) {
				usersDataDto.setPicName(users.get(i).getPicId().getFullName());
				usersDataDto.setPicId(users.get(i).getPicId().getId());
			}

			if (users.get(i).getCompaniesId() != null) {
				usersDataDto.setCompaniesName(users.get(i).getCompaniesId().getCompaniesName());
				usersDataDto.setCompaniesId(users.get(i).getCompaniesId().getId());
			}

			if (users.get(i).getFilesId() != null) {
				usersDataDto.setPhotoId(users.get(i).getFilesId().getId());
			}

			usersDataDto.setId(users.get(i).getId());

			usersDataDtos.add(usersDataDto);
		}

		final UsersDto usersByRoleDto = new UsersDto();
		usersByRoleDto.setData(usersDataDtos);
		return usersByRoleDto;
	}

	@Transactional
	@Override
	public UsersDeleteResDto deleteById(final Long id) {
		Boolean usersDelete = false;
		usersDelete = usersDao.deleteById(id);

		final UsersDeleteResDto usersDeleteResDto = new UsersDeleteResDto();
		if (usersDelete) {
			usersDeleteResDto.setMessage("The data has been successfully delete");
		}

		return usersDeleteResDto;
	}

	@Override
	public Optional<Users> getUserByEmail(String email) {

		return usersDao.getUserByEmail(email);
	}

	@Override
	public UsersDto getUsersByRole(String roleCode) {
		final List<Users> users = usersDao.getUsersByRole(roleCode);
		final List<UsersDataDto> usersDataByRoleDtos = new ArrayList<>();

		for (int i = 0; i < users.size(); i++) {
			final UsersDataDto usersDataByRoleDto = new UsersDataDto();
			usersDataByRoleDto.setFullName(users.get(i).getFullName());
			usersDataByRoleDto.setVer(users.get(i).getVer());
			usersDataByRoleDto.setRoleName(users.get(i).getRoleUserId().getRoleUserName());
			usersDataByRoleDto.setEmail(users.get(i).getEmail());
			usersDataByRoleDto.setIsActive(users.get(i).getIsActive());
			usersDataByRoleDto.setIsActive(users.get(i).getIsActive());
			
			usersDataByRoleDto.setRoleId(users.get(i).getRoleUserId().getId());
			if (users.get(i).getPicId() != null) {
				usersDataByRoleDto.setPicName(users.get(i).getPicId().getFullName());
				usersDataByRoleDto.setPicId(users.get(i).getPicId().getId());
			}

			if (users.get(i).getCompaniesId() != null) {
				usersDataByRoleDto.setCompaniesName(users.get(i).getCompaniesId().getCompaniesName());
				usersDataByRoleDto.setCompaniesId(users.get(i).getCompaniesId().getId());
			}

			usersDataByRoleDto.setId(users.get(i).getId());

			usersDataByRoleDtos.add(usersDataByRoleDto);
		}

		final UsersDto usersByRoleDto = new UsersDto();
		usersByRoleDto.setData(usersDataByRoleDtos);
		return usersByRoleDto;
	}

	@Override
	public UsersDto getPicCustomers(Long id) {
		final List<Users> users = usersDao.getPicCustomers(id);
		final List<UsersDataDto> usersDataDtos = new ArrayList<>();

		for (int i = 0; i < users.size(); i++) {
			final UsersDataDto usersDataDto = new UsersDataDto();
			usersDataDto.setFullName(users.get(i).getFullName());
			usersDataDto.setVer(users.get(i).getVer());
			usersDataDto.setRoleName(users.get(i).getRoleUserId().getRoleUserName());
			usersDataDto.setEmail(users.get(i).getEmail());
			usersDataDto.setIsActive(users.get(i).getIsActive());
			usersDataDto.setRoleId(users.get(i).getRoleUserId().getId());
			usersDataDto.setIsActive(users.get(i).getIsActive());
			if (users.get(i).getPicId() != null) {
				usersDataDto.setPicName(users.get(i).getPicId().getFullName());
				usersDataDto.setPicId(users.get(i).getPicId().getId());
			}

			if (users.get(i).getCompaniesId() != null) {
				usersDataDto.setCompaniesName(users.get(i).getCompaniesId().getCompaniesName());
				usersDataDto.setCompaniesId(users.get(i).getCompaniesId().getId());
			}

			usersDataDto.setId(users.get(i).getId());

			usersDataDtos.add(usersDataDto);
		}

		final UsersDto usersDto = new UsersDto();
		usersDto.setData(usersDataDtos);
		return usersDto;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final Optional<Users> usersOptional = usersDao.getUserByEmail(username);

		if (usersOptional.isPresent()) {
			return new User(username, usersOptional.get().getPassword(), new ArrayList<>());
		}

		throw new UsernameNotFoundException("Email And Password Wrong");
	}

	@Transactional
	@Override
	public UsersChangePasswordResDto changePassword(UsersChangePasswordReqDataDto data) {
		final Optional<Users> usersOptional = usersDao.getById(principalService.getPrincipal().getId());
		final boolean checkPassword = passwordEncoder.matches(data.getOldPassword(), usersOptional.get().getPassword());
		Users usersUpdate = new Users();
		if (checkPassword) {
			usersUpdate = usersOptional.get();
			usersUpdate.setPassword(passwordEncoder.encode(data.getNewPassword()));
			usersUpdate.setUpdatedBy(principalService.getPrincipal().getId());
			usersUpdate = usersDao.update(usersUpdate);
			UsersChangePasswordResDataDto usersChangePasswordResDataDto = new UsersChangePasswordResDataDto();
			usersChangePasswordResDataDto.setVer(usersUpdate.getVer());

			UsersChangePasswordResDto usersChangePasswordResDto = new UsersChangePasswordResDto();
			usersChangePasswordResDto.setData(usersChangePasswordResDataDto);
			usersChangePasswordResDto.setMessage(MessageEnum.UPDATED.toString());
			return usersChangePasswordResDto;
		} else {
			throw new RuntimeException("Wrong Password!");
		}
	}

}
