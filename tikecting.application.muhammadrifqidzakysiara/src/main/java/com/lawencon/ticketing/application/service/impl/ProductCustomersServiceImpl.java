package com.lawencon.ticketing.application.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.ticketing.application.dao.ProductCustomersDao;
import com.lawencon.ticketing.application.dao.ProductDao;
import com.lawencon.ticketing.application.dao.UsersDao;
import com.lawencon.ticketing.application.dto.productcustomers.ProductCustomerDataDto;
import com.lawencon.ticketing.application.dto.productcustomers.ProductCustomerDeleteDataResDto;
import com.lawencon.ticketing.application.dto.productcustomers.ProductCustomerInsertDataResDto;
import com.lawencon.ticketing.application.dto.productcustomers.ProductCustomerInsertDatasReqDto;
import com.lawencon.ticketing.application.dto.productcustomers.ProductCustomerInsertResDto;
import com.lawencon.ticketing.application.dto.productcustomers.ProductCustomersDto;
import com.lawencon.ticketing.application.model.Product;
import com.lawencon.ticketing.application.model.ProductCustomers;
import com.lawencon.ticketing.application.model.Users;
import com.lawencon.ticketing.application.service.PrincipalService;
import com.lawencon.ticketing.application.service.ProductCustomersService;

@Service
public class ProductCustomersServiceImpl implements ProductCustomersService {

	private final ProductCustomersDao productCustomersDao;
	private final ProductDao productDao;
	private final UsersDao usersDao;
	private final PrincipalService principalService;
	public ProductCustomersServiceImpl(ProductCustomersDao productCustomersDao, ProductDao productDao,
			UsersDao usersDao, PrincipalService principalService) {
		this.productCustomersDao = productCustomersDao;
		this.productDao = productDao;
		this.usersDao = usersDao;
		this.principalService = principalService;
	}

	@Override
	public ProductCustomersDto getAll() {
		final List<ProductCustomers>productCustomers = productCustomersDao.getAll();
		final List<ProductCustomerDataDto> productCustomerDataDtos = new ArrayList<>();
		
		for(int i = 0 ; i< productCustomers.size(); i ++) {
			final ProductCustomerDataDto productCustomerDataDto = new ProductCustomerDataDto(); 
			productCustomerDataDto.setCustomerName(productCustomers.get(i).getCustomersId().getFullName());
			productCustomerDataDto.setProductName(productCustomers.get(i).getProductId().getProductName());
			productCustomerDataDto.setCustomerId(productCustomers.get(i).getCustomersId().getId());
			productCustomerDataDto.setProductId(productCustomers.get(i).getProductId().getId());
			productCustomerDataDto.setProductCode(productCustomers.get(i).getProductId().getProductCode());
			productCustomerDataDto.setId(productCustomers.get(i).getId());
			productCustomerDataDto.setVer(productCustomers.get(i).getVer());
			productCustomerDataDtos.add(productCustomerDataDto);
		}
		
		final ProductCustomersDto productCustomersDto = new ProductCustomersDto();
		productCustomersDto.setData(productCustomerDataDtos);
		return productCustomersDto;
	}

	@Transactional
	@Override
	public ProductCustomerInsertResDto insert(ProductCustomerInsertDatasReqDto data) {
		if(data.getProductCustomerInsertDataReqDtos()!=null) {
			for (int i = 0; i < data.getProductCustomerInsertDataReqDtos().size(); i++) {
				ProductCustomers productCustomersInsert = new ProductCustomers();
				final Optional<Product> productOptional = productDao.getById(data.getProductCustomerInsertDataReqDtos().get(i).getProductId());
				productCustomersInsert.setProductId(productOptional.get());
				
				final Optional<Users> userOptional = usersDao.getById(data.getProductCustomerInsertDataReqDtos().get(i).getCustomerId());
				productCustomersInsert.setCustomersId(userOptional.get());
				productCustomersInsert.setCreatedBy(principalService.getPrincipal().getId());
				productCustomersInsert = productCustomersDao.insert(productCustomersInsert);
			}			
		}
		final ProductCustomerInsertDataResDto productCustomerInsertDataResDto = new ProductCustomerInsertDataResDto();
		
		final ProductCustomerInsertResDto productCustomerInsertResDto = new ProductCustomerInsertResDto();
		productCustomerInsertResDto.setMessage("The data has been successfully input");
		productCustomerInsertResDto.setData(productCustomerInsertDataResDto);
		return productCustomerInsertResDto;
	}

	@Transactional
	@Override
	public ProductCustomerDeleteDataResDto deleteById(final Long id) {
		boolean productCustomersDelete = true;
		productCustomersDelete = productCustomersDao.deleteById(id);
		final ProductCustomerDeleteDataResDto productCustomerDeleteDataResDto = new ProductCustomerDeleteDataResDto();		
		if(productCustomersDelete) {
			productCustomerDeleteDataResDto.setMessage("The data has been successfully delete");
		}
		return productCustomerDeleteDataResDto;
	}

	@Override
	public ProductCustomersDto getByCustomers(Long id) {
		final List<ProductCustomers>productCustomers = productCustomersDao.getByCustomers(id);
		final List<ProductCustomerDataDto> productCustomerDataDtos = new ArrayList<>();
		
		for(int i = 0 ; i< productCustomers.size(); i ++) {
			final ProductCustomerDataDto productCustomerDataDto = new ProductCustomerDataDto(); 
			productCustomerDataDto.setProductName(productCustomers.get(i).getProductId().getProductName());
			productCustomerDataDto.setId(productCustomers.get(i).getId());
			productCustomerDataDto.setCustomerName(productCustomers.get(i).getCustomersId().getFullName());
			productCustomerDataDto.setVer(productCustomers.get(i).getCustomersId().getVer());
			productCustomerDataDto.setCustomerId(productCustomers.get(i).getCustomersId().getId());
			productCustomerDataDto.setProductId(productCustomers.get(i).getProductId().getId());
			productCustomerDataDto.setProductCode(productCustomers.get(i).getProductId().getProductCode());
			productCustomerDataDtos.add(productCustomerDataDto);
		}
		
		final ProductCustomersDto productCustomersDto = new ProductCustomersDto();
		productCustomersDto.setData(productCustomerDataDtos);
		return productCustomersDto;
	}

}
