package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Note;
import com.example.demo.services.NotesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/notes")
@CrossOrigin(origins = "http://localhost:3001")
public class NotesController {

	@Autowired
	NotesService noteService;

	@GetMapping
	public Iterable<Note> getNotes() {
	    return noteService.getNotes();
	}

	@PostMapping
	public Integer createNote(@RequestBody @Valid Note note) {
	    return noteService.addNote(note);
	}

}
