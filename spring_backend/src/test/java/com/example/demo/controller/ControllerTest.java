package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.boot.test.mock.mockito.MockBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.entity.Order1;
import com.example.demo.entity.OrderLine;
import com.example.demo.service.NoteService;

@WebMvcTest(NoteController.class)
public class ControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private NoteService noteService;

	@Autowired
	private ObjectMapper objectMapper;

	private Order1 order;
	private OrderLine line;

	@BeforeEach
	void setup() {
		line = new OrderLine();
		line.setId(1L);
		line.setItem("Pen");
		line.setPrice(10.50);
		line.setQuantity(1);

		order = new Order1();
		order.setId(1L);
		order.setOrderLines(Arrays.asList(line));

		line.setOrder(order);
	}

	@Test
	void testGetOrders() throws Exception {
		when(noteService.getOrder()).thenReturn(Arrays.asList(order));

		mockMvc.perform(get("/order"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].id").value(1));
		verify(noteService, times(1)).getOrder();
	}

	@Test
	void testGetOrderById_success() throws Exception {
		when(noteService.getOrderById(1L)).thenReturn(Optional.of(order));

		mockMvc.perform(get("/order/1"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(1));
		verify(noteService, times(1)).getOrderById(1L);
	}

	@Test
	void testGetOrderById_notFound() throws Exception {
		when(noteService.getOrderById(999L)).thenReturn(Optional.empty());

		mockMvc.perform(get("/order/999"))
		.andExpect(status().isOk());

		verify(noteService, times(1)).getOrderById(999L);
	}

	@Test
	void testCreateOrder_success() throws Exception {
		when(noteService.addOrder(any(Order1.class))).thenReturn(1L);

		mockMvc.perform(
				post("/order").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(order)))
				.andExpect(status().isOk())
				.andExpect(content().string("1"));

		verify(noteService, times(1)).addOrder(any(Order1.class));
	}

	@Test
	void testCreateOrder_emptyOrderLines() throws Exception {

		when(noteService.addOrder(any(Order1.class)))
				.thenThrow(new IllegalArgumentException("Order must have at least one order line"));

		Order1 invalidOrder = new Order1();
		invalidOrder.setOrderLines(Arrays.asList());

		mockMvc.perform(post("/order").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(invalidOrder)))
		.andExpect(status().isBadRequest());
	}

	@Test
	void testCreateOrder_missingItem() throws Exception {

		when(noteService.addOrder(any(Order1.class))).thenThrow(new IllegalArgumentException("Item name is missing"));

		OrderLine invalidLine = new OrderLine();
		invalidLine.setPrice(10);
		invalidLine.setQuantity(1);

		Order1 invalidOrder = new Order1();
		invalidOrder.setOrderLines(Arrays.asList(invalidLine));

		mockMvc.perform(post("/order").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(invalidOrder)))
		.andExpect(status().isBadRequest());
	}

	@Test
	void testCreateOrder_invalidPrice() throws Exception {

		when(noteService.addOrder(any(Order1.class))).thenThrow(new IllegalArgumentException("Price must be >= 0"));

		OrderLine invalidLine = new OrderLine();
		invalidLine.setItem("Pen");
		invalidLine.setPrice(-10);
		invalidLine.setQuantity(1);

		Order1 invalidOrder = new Order1();
		invalidOrder.setOrderLines(Arrays.asList(invalidLine));

		mockMvc.perform(post("/order").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(invalidOrder)))
		.andExpect(status().isBadRequest());
	}

	@Test
	void testDeleteOrder() throws Exception {
		doNothing().when(noteService).deleteOrderById(1L);

		mockMvc.perform(delete("/order/1"))
		.andExpect(status().isOk());

		verify(noteService, times(1)).deleteOrderById(1L);
	}
}