package com.lawencon.ticketing.application.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.lawencon.ticketing.application.dto.users.UserDto;
import com.lawencon.ticketing.application.dto.users.UsersChangePasswordReqDataDto;
import com.lawencon.ticketing.application.dto.users.UsersChangePasswordResDto;
import com.lawencon.ticketing.application.dto.users.UsersDeleteResDto;
import com.lawencon.ticketing.application.dto.users.UsersDto;
import com.lawencon.ticketing.application.dto.users.UsersInsertReqDataDto;
import com.lawencon.ticketing.application.dto.users.UsersInsertResDto;
import com.lawencon.ticketing.application.dto.users.UsersUpdateDataReqDto;
import com.lawencon.ticketing.application.dto.users.UsersUpdateResDto;
import com.lawencon.ticketing.application.model.Users;

public interface UsersService extends UserDetailsService {
	UsersInsertResDto insert(final UsersInsertReqDataDto data);

	UsersUpdateResDto update(final UsersUpdateDataReqDto data);

	UserDto getById(final Long id);

	UsersDto getAll();

	UsersDeleteResDto deleteById(final Long id);

	Optional<Users> getUserByEmail(final String email);

	UsersDto getUsersByRole(final String roleCode);

	UsersDto getPicCustomers(final Long id);
	
	UsersChangePasswordResDto changePassword(UsersChangePasswordReqDataDto data);
}
