package com.tek.email_service;

import org.springframework.stereotype.Service;

import com.tek.email_service.request.Email;

@Service
public class EmailService {

	public boolean sendEmail(Email email) {

		System.out.println("Sending Email...");
		System.out.println("To: " + email.getTo());
		System.out.println("Subject: " + email.getSubject());
		System.out.println("Body: " + email.getBody());

		return true;
	}
}