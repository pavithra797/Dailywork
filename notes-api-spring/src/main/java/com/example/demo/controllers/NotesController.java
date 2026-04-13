package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
public class NotesController {
	
	@Autowired
	NotesService noteService;
	
	@GetMapping
	Note getNotes() {
		return noteService.getNotes();
	}
	
	@PostMapping
	void setNote(@RequestBody @Valid Note note) {
		noteService.setNote(note);
	}

}
