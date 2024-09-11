package com.bmicrocorp.laboratory.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {


	@GetMapping("/login")
	public ResponseEntity<Void> login() {
		return null;
	}


}
