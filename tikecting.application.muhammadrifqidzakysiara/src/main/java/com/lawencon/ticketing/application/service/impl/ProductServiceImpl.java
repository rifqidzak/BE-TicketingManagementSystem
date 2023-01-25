package com.lawencon.ticketing.application.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lawencon.ticketing.application.dao.ProductDao;
import com.lawencon.ticketing.application.dto.products.ProductDeleteResDto;
import com.lawencon.ticketing.application.dto.products.ProductDto;
import com.lawencon.ticketing.application.dto.products.ProductInsertDataResDto;
import com.lawencon.ticketing.application.dto.products.ProductInsertReqDto;
import com.lawencon.ticketing.application.dto.products.ProductInsertResDto;
import com.lawencon.ticketing.application.dto.products.ProductUpdateDataResDto;
import com.lawencon.ticketing.application.dto.products.ProductUpdateReqDto;
import com.lawencon.ticketing.application.dto.products.ProductUpdateResDto;
import com.lawencon.ticketing.application.dto.products.ProductsDataDto;
import com.lawencon.ticketing.application.dto.products.ProductsDto;
import com.lawencon.ticketing.application.model.Product;
import com.lawencon.ticketing.application.service.PrincipalService;
import com.lawencon.ticketing.application.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	private final ProductDao productDao;
	private final PrincipalService principalService;
	public ProductServiceImpl(ProductDao productDao, PrincipalService principalService) {
		this.productDao = productDao;
		this.principalService = principalService;
	}

	@Transactional
	@Override
	public ProductInsertResDto insert(final ProductInsertReqDto data) {
		Product productInsert = new Product();
		productInsert.setProductCode(data.getProductCode());
		productInsert.setProductName(data.getProductName());
		productInsert.setCreatedBy(principalService.getPrincipal().getId());
		productInsert = productDao.insert(productInsert);

		final ProductInsertDataResDto productInsertDataResDto = new ProductInsertDataResDto();
		productInsertDataResDto.setId(productInsert.getId());
		final ProductInsertResDto productInsertResDto = new ProductInsertResDto();
		productInsertResDto.setData(productInsertDataResDto);
		productInsertResDto.setMessage("The data has been successfully input");
		return productInsertResDto;
	}

	@Transactional
	@Override
	public ProductUpdateResDto update(final ProductUpdateReqDto data) {
		final Optional<Product> productOptional = productDao.getById(data.getId());
		Product productUpdate = new Product();

		if (productOptional.isPresent()) {
			productUpdate = productOptional.get();
			productUpdate.setProductName(data.getProductName());
			productUpdate.setVer(data.getVer());
			productUpdate.setUpdatedBy(principalService.getPrincipal().getId());
			productUpdate.setIsActive(data.getIsActive());
		}
		productUpdate = productDao.update(productUpdate);
		final ProductUpdateDataResDto productUpdateDataResDto = new ProductUpdateDataResDto();
		productUpdateDataResDto.setVer(productUpdate.getVer());

		final ProductUpdateResDto productUpdateResDto = new ProductUpdateResDto();
		productUpdateResDto.setData(productUpdateDataResDto);
		productUpdateResDto.setMessage("The data has been successfully update");
		return productUpdateResDto;
	}

	@Override
	public ProductDto getById(final Long id) {
		final Optional<Product> productOptional = productDao.getById(id);
		final ProductsDataDto productsDataDto = new ProductsDataDto();
		productsDataDto.setId(productOptional.get().getId());
		productsDataDto.setProductCode(productOptional.get().getProductCode());
		productsDataDto.setProductName(productOptional.get().getProductName());
		productsDataDto.setIsActive(productOptional.get().getIsActive());
		productsDataDto.setVer(productOptional.get().getVer());
		final ProductDto productDto = new ProductDto();
		productDto.setData(productsDataDto);
		return productDto;
	}

	@Override
	public ProductsDto getAll() {
		final List<ProductsDataDto> productsDataDtos = new ArrayList<>();
		final List<Product> products = productDao.getAll();
		for (int i = 0; i < products.size(); i++) {
			final ProductsDataDto productsDataDto = new ProductsDataDto();
			productsDataDto.setId(products.get(i).getId());
			productsDataDto.setProductCode(products.get(i).getProductCode());
			productsDataDto.setProductName(products.get(i).getProductName());
			productsDataDto.setVer(products.get(i).getVer());
			productsDataDto.setIsActive(products.get(i).getIsActive());
			productsDataDtos.add(productsDataDto);
		}
		final ProductsDto productsDto = new ProductsDto();
		productsDto.setData(productsDataDtos);

		return productsDto;
	}

	@Transactional
	@Override
	public ProductDeleteResDto deleteById(final Long id) {
		Boolean productDelete = false;
		productDelete = productDao.deleteById(id);
		final ProductDeleteResDto productDeleteResDto = new ProductDeleteResDto();
		if (productDelete) {
			productDeleteResDto.setMessage("The data has been successfully delete");
		}
		return productDeleteResDto;
	}
}
