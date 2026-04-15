package com.example.demo;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.controllers.NotesController;
import com.example.demo.entity.Note;
import com.example.demo.services.NotesService;

public class NotesControllerTest {

	@InjectMocks
	NotesController noteController;

	@Mock
	NotesService noteService;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetNotes() {
		Iterable<Note> notes = new ArrayList<>();
		when(noteController.getNotes()).thenReturn(notes);
		Iterable<Note> result = noteController.getNotes();
		assertNotNull(result);
	}

	@Test
	void testCreateNote() {
		Note note = new Note();
		when(noteService.addNote(note)).thenReturn(1);
		Integer result = noteController.createNote(note);
		assertNotNull(result);
		assertEquals(result, 1);
	}
}