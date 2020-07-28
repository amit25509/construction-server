package com.construction.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.construction.models.User;
import com.construction.responses.GlobalResponseData;
import com.construction.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	GlobalResponseData globalResponseData;

	@GetMapping("/getbyusername")
	public ResponseEntity<GlobalResponseData> getUserByUsername() {
		
		return userService.getUserByUsername();
	}
	
	@PutMapping("/updatebyusername")
	public ResponseEntity<GlobalResponseData> updateUser(@RequestBody User newUser) {
		
			return userService.updateUser(newUser);

	}	
	
}

