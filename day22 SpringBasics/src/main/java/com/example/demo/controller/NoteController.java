package com.example.demo.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class NoteController {
	NoteController(){
		System.out.println("initial");
	}

}
