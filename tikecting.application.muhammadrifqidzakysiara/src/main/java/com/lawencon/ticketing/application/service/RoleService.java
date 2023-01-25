package com.lawencon.ticketing.application.service;

import com.lawencon.ticketing.application.dto.roles.RoleDto;
import com.lawencon.ticketing.application.dto.roles.RolesDto;

public interface RoleService {

	RoleDto getById(final Long id);

	RolesDto getAll();

}
