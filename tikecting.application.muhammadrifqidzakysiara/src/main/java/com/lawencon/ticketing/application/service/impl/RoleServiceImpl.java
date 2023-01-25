package com.lawencon.ticketing.application.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lawencon.ticketing.application.dao.RoleDao;
import com.lawencon.ticketing.application.dto.roles.RoleDto;
import com.lawencon.ticketing.application.dto.roles.RolesDataDto;
import com.lawencon.ticketing.application.dto.roles.RolesDto;
import com.lawencon.ticketing.application.model.RoleUsers;
import com.lawencon.ticketing.application.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	private final RoleDao roleDao;

	public RoleServiceImpl(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public RoleDto getById(final Long id) {
		final Optional<RoleUsers> roleUsersOptional = roleDao.getById(id);
		final RolesDataDto rolesDataDto = new RolesDataDto();
		rolesDataDto.setId(roleUsersOptional.get().getId());
		rolesDataDto.setRoleCode(roleUsersOptional.get().getRoleUserCode());
		rolesDataDto.setRoleName(roleUsersOptional.get().getRoleUserName());
		rolesDataDto.setVer(roleUsersOptional.get().getVer());
		
		RoleDto roleDto = new RoleDto();
		roleDto.setData(rolesDataDto);
		return roleDto;
	}

	@Override
	public RolesDto getAll() {
		final List<RoleUsers> roleUsers = roleDao.getAll();
		final List<RolesDataDto>rolesDataDtos = new ArrayList<>();
		
		for(int i = 0; i<roleUsers.size();i++) {
			final RolesDataDto rolesDataDto = new RolesDataDto();
			rolesDataDto.setId(roleUsers.get(i).getId());
			rolesDataDto.setRoleCode(roleUsers.get(i).getRoleUserCode());
			rolesDataDto.setRoleName(roleUsers.get(i).getRoleUserName());
			rolesDataDto.setVer(roleUsers.get(i).getVer());
			rolesDataDtos.add(rolesDataDto);
		}
		
		RolesDto rolesDto = new RolesDto();
		rolesDto.setData(rolesDataDtos);
		return rolesDto;
	}

}
