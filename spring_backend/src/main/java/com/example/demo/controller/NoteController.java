package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Order1;
import com.example.demo.service.NoteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:3000")
public class NoteController {
	@Autowired
	private NoteService noteService;

	@GetMapping
	public Iterable<Order1> getOrder() {
		return noteService.getOrder();
	}

	@GetMapping("/{id}")
	public Optional<Order1> getOrderById(@PathVariable Long id) {
		return noteService.getOrderById(id);
	}

	@PostMapping
	public Long createOrder(@RequestBody @Valid Order1 order1) {
	    return noteService.addOrder(order1);
	}

	@DeleteMapping("/{id}")
	public void deleteOrderById(@PathVariable Long id) {
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

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleIllegalArgumentException(IllegalArgumentException ex) {
	    return ex.getMessage();
	}
}