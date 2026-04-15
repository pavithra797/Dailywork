package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Note;
import com.example.demo.repositories.NotesRepository;

import jakarta.validation.Valid;

@Service
public class NotesService {

    @Autowired
    private NotesRepository notesRepository;

    public Iterable<Note> getNotes() {
        System.out.println("inside service");   
        return notesRepository.findAll();
    }

    public void createNote(Note note) {
        System.out.println("inside createNote");   
        notesRepository.save(note);
    }

    public Integer addNote(@Valid Note note) {
        System.out.println("inside addNote");   
        return 1;
    }
}