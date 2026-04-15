package com.example.demo.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Order1;
import com.example.demo.repository.Order1Repository;

@Service
public class NoteService {

	@Autowired
	private Order1Repository order1Repository;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private EmailService emailService;

	public Iterable<Order1> getOrder() {
		return order1Repository.findAll();
	}

	@Transactional(rollbackFor = Exception.class)
	public Integer addOrder(Order1 order1) throws IOException {

		if (order1.getOrderLines() != null) {
			order1.getOrderLines().forEach(line -> {
				line.setOrder(order1);
			});
		}

		paymentService.processPayment();
		order1Repository.save(order1);

		try {
			emailService.send(order1.getId());
		} catch (Exception e) {
			System.out.println("Email failed");
		}

		return order1.getId();
	}

	public Optional<Order1> getOrderById(Integer id) {
		return order1Repository.findById(id);
	}

	public void deleteOrderById(Integer id) {
		order1Repository.deleteById(id);
	}
}