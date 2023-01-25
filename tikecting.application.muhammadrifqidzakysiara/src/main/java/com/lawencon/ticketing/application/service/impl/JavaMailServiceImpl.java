package com.lawencon.ticketing.application.service.impl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.lawencon.ticketing.application.pojo.SendEmailPojo;
import com.lawencon.ticketing.application.service.JavaMailService;
@Service
public class JavaMailServiceImpl implements JavaMailService{
	private final JavaMailSender javaMailSender;

	public JavaMailServiceImpl(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	@Override
	public void sendEmail(SendEmailPojo createdAccountPojo) {
		final SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(createdAccountPojo.getEmail());

		msg.setSubject(createdAccountPojo.getSubject());
		msg.setText(createdAccountPojo.getBody());
		javaMailSender.send(msg);
	}


}
