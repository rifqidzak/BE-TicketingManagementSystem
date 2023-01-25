package com.lawencon.ticketing.application.controller;

import java.util.Base64;
import java.util.Optional;

import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.ticketing.application.model.Files;
import com.lawencon.ticketing.application.service.FilesService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("files")
public class FilesController {
	private final FilesService filesService;

	public FilesController(FilesService filesService) {
		this.filesService = filesService;
	}

	@GetMapping("download/{id}")
	public ResponseEntity<?> download(@PathVariable("id") Long id) {
		final Optional<Files> file = filesService.getById(id);
		final byte[] fileBytes = Base64.getDecoder().decode(file.get().getFileName());
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=attachment." + file.get().getExtensions()).body(fileBytes);
	}
	
	@GetMapping("download/{id}/{d}")
	public ResponseEntity<?> download(@PathVariable("id") Long id, @PathVariable("d") Long d) {
		final Optional<Files> file = filesService.getById(id);
		final byte[] fileBytes = Base64.getDecoder().decode(file.get().getFileName());
		return ResponseEntity.ok().cacheControl(CacheControl.noCache()).header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=attachment." + file.get().getExtensions()).body(fileBytes);
	}
}
