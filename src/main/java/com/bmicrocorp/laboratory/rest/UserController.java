package com.bmicrocorp.laboratory.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bmicrocorp.laboratory.model.entities.User;

@RestController
public class UserController {

    @GetMapping("/user/hello")
	public User hello(@RequestParam(value = "id", defaultValue = "0") long id) {
		return new User(id, "test@email.com", "Original User");
	}

    
    @GetMapping("/user/hello/{id}")
	public User hello(@PathVariable Long id) {
		return new User(id, id + "test.com", "User " + id);
	}
}
