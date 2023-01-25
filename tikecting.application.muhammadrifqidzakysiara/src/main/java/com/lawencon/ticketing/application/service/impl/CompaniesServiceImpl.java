package com.lawencon.ticketing.application.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.ticketing.application.dao.CompaniesDao;
import com.lawencon.ticketing.application.dto.companies.CompaniesDataDto;
import com.lawencon.ticketing.application.dto.companies.CompaniesDeleteResDto;
import com.lawencon.ticketing.application.dto.companies.CompaniesDto;
import com.lawencon.ticketing.application.dto.companies.CompaniesInsertDataResDto;
import com.lawencon.ticketing.application.dto.companies.CompaniesInsertReqDto;
import com.lawencon.ticketing.application.dto.companies.CompaniesInsertResDto;
import com.lawencon.ticketing.application.dto.companies.CompaniesUpdateDataResDto;
import com.lawencon.ticketing.application.dto.companies.CompaniesUpdateReqDto;
import com.lawencon.ticketing.application.dto.companies.CompaniesUpdateResDto;
import com.lawencon.ticketing.application.dto.companies.CompanyDto;
import com.lawencon.ticketing.application.model.Companies;
import com.lawencon.ticketing.application.service.CompaniesService;
import com.lawencon.ticketing.application.service.PrincipalService;

@Service
public class CompaniesServiceImpl implements CompaniesService {

	private final CompaniesDao companiesDao;
	private final PrincipalService principalService;
	public CompaniesServiceImpl(CompaniesDao companiesDao, PrincipalService principalService) {
		this.companiesDao = companiesDao;
		this.principalService = principalService;
	}

	@Transactional
	@Override
	public CompaniesInsertResDto insert(final CompaniesInsertReqDto data) {
		Companies companiesInsert = new Companies();
		companiesInsert.setCompaniesCode(data.getCompaniesCode());
		companiesInsert.setCompaniesName(data.getCompaniesName());
		companiesInsert.setCreatedBy(principalService.getPrincipal().getId());
		
		companiesInsert = companiesDao.insert(companiesInsert);
		
		final CompaniesInsertDataResDto companiesInsertDataResDto = new CompaniesInsertDataResDto();
		companiesInsertDataResDto.setId(companiesInsert.getId());
		
		final CompaniesInsertResDto companiesInsertResDto = new CompaniesInsertResDto();
		companiesInsertResDto.setData(companiesInsertDataResDto);
		companiesInsertResDto.setMessage("The data has been successfully input");
		return companiesInsertResDto;
	}

	@Transactional
	@Override
	public CompaniesUpdateResDto update(final CompaniesUpdateReqDto data) {
		final Optional<Companies> companiesOptional = companiesDao.getById(data.getId());
		Companies companiesUpdate = new Companies();

		if (companiesOptional.isPresent()) {
			companiesUpdate = companiesOptional.get();
			companiesUpdate.setCompaniesName(data.getCompaniesName());
			companiesUpdate.setUpdatedBy(principalService.getPrincipal().getId());
			companiesUpdate.setUpdatedAt(LocalDateTime.now());
			companiesUpdate.setVer(data.getVer());
			companiesUpdate.setIsActive(data.getIsActive());
		}
		companiesUpdate = companiesDao.update(companiesUpdate);
		CompaniesUpdateDataResDto companiesUpdateDataResDto = new CompaniesUpdateDataResDto();
		companiesUpdateDataResDto.setVer(companiesUpdate.getVer());
		
		CompaniesUpdateResDto companiesUpdateResDto = new CompaniesUpdateResDto();
		companiesUpdateResDto.setData(companiesUpdateDataResDto);
		companiesUpdateResDto.setMessage("The data has been successfully update");
		return companiesUpdateResDto;
	}

	@Override
	public CompanyDto getById(final Long id) {
		final Optional<Companies> companiesOptional = companiesDao.getById(id);
		final CompaniesDataDto companiesDataDto = new CompaniesDataDto();
		companiesDataDto.setCompaniesCode(companiesOptional.get().getCompaniesCode());
		companiesDataDto.setId(companiesOptional.get().getId());
		companiesDataDto.setCompaniesName(companiesOptional.get().getCompaniesName());
		companiesDataDto.setVer(companiesOptional.get().getVer());
		companiesDataDto.setIsActive(companiesOptional.get().getIsActive());
		CompanyDto companyDto = new CompanyDto();
		companyDto.setData(companiesDataDto);
		return companyDto;
	}

	@Override
	public CompaniesDto getAll() {
		final List <Companies> companies = companiesDao.getAll();
		final CompaniesDto companiesDto = new CompaniesDto();
		final List<CompaniesDataDto>companiesDataDtos = new ArrayList<>();
		for(int i = 0; i<companies.size();i++) {
			CompaniesDataDto companiesDataDto = new CompaniesDataDto();
			companiesDataDto.setId(companies.get(i).getId());
			companiesDataDto.setCompaniesCode(companies.get(i).getCompaniesCode());
			companiesDataDto.setCompaniesName(companies.get(i).getCompaniesName());
			companiesDataDto.setVer(companies.get(i).getVer());
			companiesDataDto.setIsActive(companies.get(i).getIsActive());
			companiesDataDtos.add(companiesDataDto);
		}
		companiesDto.setData(companiesDataDtos);
		return companiesDto;
	}

	@Transactional
	@Override
	public CompaniesDeleteResDto deleteById(final Long id) {
		Boolean companiesDelete = false;
		companiesDelete = companiesDao.deleteById(id);
		final CompaniesDeleteResDto companiesDeleteDto = new CompaniesDeleteResDto();
		if(companiesDelete) {
			companiesDeleteDto.setMessage("The data has been successfully delete");
		}
		return companiesDeleteDto;
	}

}
