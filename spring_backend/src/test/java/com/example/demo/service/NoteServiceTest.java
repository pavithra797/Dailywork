package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.entity.Order1;
import com.example.demo.entity.OrderLine;
import com.example.demo.repository.Order1Repository;

@ExtendWith(MockitoExtension.class)
public class NoteServiceTest {

	@InjectMocks
	private NoteService noteService;

	@Mock
	private Order1Repository order1Repository;

	@Mock
	private PaymentService paymentService;

	@Mock
	private EmailService emailService;

	@Test
	void testMissingItemName() {
		Order1 order = new Order1();

		OrderLine line = new OrderLine();
		line.setItem("");
		line.setPrice(100);
		line.setQuantity(1);

		order.setOrderLines(List.of(line));

		Exception ex = assertThrows(IllegalArgumentException.class, () -> {
			noteService.addOrder(order);
		});

		assertEquals("Item name is missing", ex.getMessage());
	}

	@Test
	void testPriceLessThanZero() {
		Order1 order = new Order1();

		OrderLine line = new OrderLine();
		line.setItem("Pen");
		line.setPrice(-10);
		line.setQuantity(1);

		order.setOrderLines(List.of(line));

		Exception ex = assertThrows(IllegalArgumentException.class, () -> {
			noteService.addOrder(order);
		});

		assertEquals("Price must be >= 0", ex.getMessage());
	}

	@Test
	void testQuantityLessThanOne() {
		Order1 order = new Order1();

		OrderLine line = new OrderLine();
		line.setItem("Book");
		line.setPrice(100);
		line.setQuantity(0);

		order.setOrderLines(List.of(line));

		Exception ex = assertThrows(IllegalArgumentException.class, () -> {
			noteService.addOrder(order);
		});

		assertEquals("Quantity must be >= 1", ex.getMessage());
	}

	@Test
	void testAddOrder_success() {

		OrderLine line = new OrderLine();
		line.setItem("Pen");
		line.setPrice(10);
		line.setQuantity(1);

		Order1 order = new Order1();
		order.setOrderLines(List.of(line));

		Order1 savedOrder = new Order1();
		savedOrder.setId(1L);

		when(order1Repository.save(any(Order1.class))).thenReturn(savedOrder);

		Long result = noteService.addOrder(order);

		assertNotNull(result);
		assertEquals(1L, result);
	}

	@Test
	void testGetOrderById() {

		Order1 order = new Order1();
		order.setId(1L);

		when(order1Repository.findById(1L)).thenReturn(java.util.Optional.of(order));

		var result = noteService.getOrderById(1L);

		assertTrue(result.isPresent());
		assertEquals(1L, result.get().getId());
	}

	@Test

	void testAddOrder_emailFailure() {

		OrderLine line = new OrderLine();
		line.setItem("pen");
		line.setPrice(10);
		line.setQuantity(1);

		Order1 order = new Order1();
		order.setOrderLines(List.of(line));

		Order1 savedOrder = new Order1();
		savedOrder.setId(1L);

		when(order1Repository.save(any(Order1.class))).thenReturn(savedOrder);

		doThrow(new RuntimeException("Email failed")).when(emailService).send(1L);

		long result = noteService.addOrder(order);
		assertEquals(1L, result);
	}

	@Test
	void testOrderWithZeroOrderLines() {

		Order1 order = new Order1();
		order.setOrderLines(List.of()); 
		Exception ex = assertThrows(IllegalArgumentException.class, () -> {
			noteService.addOrder(order);
		});

		assertEquals("Order must have at least one order line", ex.getMessage());
	}

}