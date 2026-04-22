package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Order1;
import com.example.demo.entity.OrderLine;
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
	public Long addOrder(Order1 order1) {

		if (order1 == null || order1.getOrderLines() == null || order1.getOrderLines().isEmpty()) {
			throw new IllegalArgumentException("Order must have at least one order line");
		}

		for (OrderLine line : order1.getOrderLines()) {

			if (line.getItem() == null || line.getItem().trim().isEmpty()) {
				throw new IllegalArgumentException("Item name is missing");
			}

			if (line.getPrice() < 0) {
				throw new IllegalArgumentException("Price must be >= 0");
			}

			if (line.getQuantity() < 1) {
				throw new IllegalArgumentException("Quantity must be >= 1");
			}

			line.setOrder(order1);
		}

		paymentService.processPayment();

		Order1 savedOrder = order1Repository.save(order1);

		try {
			emailService.send(savedOrder.getId());
		} catch (Exception e) {
			System.out.println("Email failed");
		}

		return savedOrder.getId();
	}

	public Optional<Order1> getOrderById(Long id) {
		return order1Repository.findById(id);
	}

	public void deleteOrderById(Long id) {
		order1Repository.deleteById(id);
	}
}