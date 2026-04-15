package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.example.demo.controller.NoteController;
import com.example.demo.entity.Order1;
import com.example.demo.service.NoteService;

class NoteControllerTest {
	@InjectMocks
	NoteController noteController;
	@Mock
	NoteService notesService;

	@Test
	void testGetOrder() {
		Iterable<Order1> orders1 = new ArrayList<>();
		when(noteController.getOrder()).thenReturn(orders1);
		Iterable<Order1> result = noteController.getOrder();
		assertNotNull(result);
	}

}
