package com.lawencon.ticketing.application.service;

import com.lawencon.ticketing.application.pojo.SendEmailPojo;

public interface JavaMailService {
	void sendEmail(SendEmailPojo createdAccountPojo);
}
