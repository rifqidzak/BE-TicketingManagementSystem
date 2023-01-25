package com.lawencon.ticketing.application.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.ticketing.application.dto.comments.CommentDto;
import com.lawencon.ticketing.application.dto.comments.CommentInsertDataReqDto;
import com.lawencon.ticketing.application.dto.comments.CommentInsertResDto;
import com.lawencon.ticketing.application.service.CommentsService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("comments")
public class CommentsController {
	private final CommentsService commentsService;

	public CommentsController(CommentsService commentsService) {
		this.commentsService = commentsService;
	}
	
	@PostMapping
	public ResponseEntity<CommentInsertResDto> insert(@RequestBody @Valid CommentInsertDataReqDto data){
		final CommentInsertResDto commentInsertResDto = commentsService.insert(data);
		return new ResponseEntity<>(commentInsertResDto, HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<CommentDto> getComment(@PathVariable("id")Long id){
		final CommentDto commentDto = commentsService.getAllComment(id);
		return new ResponseEntity<>(commentDto, HttpStatus.OK);
	}
}
