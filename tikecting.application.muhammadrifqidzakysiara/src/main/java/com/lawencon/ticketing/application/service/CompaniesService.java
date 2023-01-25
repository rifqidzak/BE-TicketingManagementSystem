package com.lawencon.ticketing.application.service;

import com.lawencon.ticketing.application.dto.companies.CompaniesDeleteResDto;
import com.lawencon.ticketing.application.dto.companies.CompaniesDto;
import com.lawencon.ticketing.application.dto.companies.CompaniesInsertReqDto;
import com.lawencon.ticketing.application.dto.companies.CompaniesInsertResDto;
import com.lawencon.ticketing.application.dto.companies.CompaniesUpdateReqDto;
import com.lawencon.ticketing.application.dto.companies.CompaniesUpdateResDto;
import com.lawencon.ticketing.application.dto.companies.CompanyDto;

public interface CompaniesService {
	CompaniesInsertResDto insert(final CompaniesInsertReqDto data);

	CompaniesUpdateResDto update(final CompaniesUpdateReqDto data);

	CompanyDto getById(final Long id);

	CompaniesDto getAll();

	CompaniesDeleteResDto deleteById(final Long id);
}
