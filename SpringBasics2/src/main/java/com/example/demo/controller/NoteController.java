package com.example.demo.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Order1;
import com.example.demo.repository.Order1Repository;
import com.example.demo.service.NoteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/order")
public class NoteController {

	private final Order1Repository order1Repository;

	@Autowired
	private NoteService noteService;

	NoteController(Order1Repository order1Repository) {
		this.order1Repository = order1Repository;
	}

	@GetMapping
	public Iterable<Order1> getOrder() {
		return noteService.getOrder();
	}

	@GetMapping("/{id}")
	public Optional<Order1> getOrderById(@PathVariable Integer id) {
		return noteService.getOrderById(id);
	}

	@PostMapping
	public Integer createOrder(@RequestBody @Valid Order1 order1) throws IOException {
		return noteService.addOrder(order1);
	}

	@DeleteMapping("/{id}")
	public void deleteOrderById(@PathVariable Integer id) {
		noteService.deleteOrderById(id);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleBadRequest(Exception ex) {
		return "Invalid request body";
	}
}