package com.example.demo.controller;

import java.util.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.*;
import com.example.demo.payload.request.*;
import com.example.demo.payload.response.*;
import com.example.demo.repository.*;
import com.example.demo.security.JwtUtils;
import com.example.demo.security.service.UserDetailsImplementation;

@RestController
@RequestMapping("/api/auth")
//@CrossOrigin(origins = "*", maxAge = 3000)
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

		System.out.println("USERNAME = " + loginRequest.getUsername());
		System.out.println("PASSWORD = " + loginRequest.getPassword());

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImplementation userDetails = (UserDetailsImplementation) authentication.getPrincipal();

		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getEmail(), roles));

	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUp) {

	    if (userRepository.existsByUsername(signUp.getUsername()))
	        return ResponseEntity.badRequest().body(new MessageResponse("Username exists"));

	    User user = new User(
	            signUp.getUsername(),
	            signUp.getEmail(),
	            encoder.encode(signUp.getPassword())
	    );

	    List<Role> roles = roleRepository.findByName(ERole.ROLE_USER);

	    if (roles.isEmpty()) {
	        throw new RuntimeException("Role not found");
	    }

	    Role role = roles.get(0);  

	    user.setRoles(Set.of(role));

	    userRepository.save(user);

	    return ResponseEntity.ok(new MessageResponse("User registered"));
	}

}