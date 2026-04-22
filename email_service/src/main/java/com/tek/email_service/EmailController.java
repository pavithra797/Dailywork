package com.tek.email_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.tek.email_service.request.Email;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public String sendEmail(@RequestBody Email email) {
        boolean status = emailService.sendEmail(email);

        if (status) {
            return "Email sent successfully";
        } else {
            return "Email failed to send";
        }
    }
}