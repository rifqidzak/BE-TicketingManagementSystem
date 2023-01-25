package com.lawencon.ticketing.application.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.lawencon.ticketing.application.dao.UsersDao;
import com.lawencon.ticketing.application.model.Users;
import com.lawencon.ticketing.application.service.PrincipalService;

@Service
public class PrincipalServiceImpl implements PrincipalService {

	private UsersDao usersDao;
	
	@Autowired
	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	@Override
	public Users getPrincipal() {
		final Long id = Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		final Optional<Users> usersOptional = usersDao.getById(id);
		
		if (usersOptional.isPresent()) {
			return usersOptional.get();			
		}
		throw new RuntimeException("Invalid Login");
	}

}
