package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    void send(Long id) {
        System.out.println("Email sent successfully for id: " + id);
    }
}