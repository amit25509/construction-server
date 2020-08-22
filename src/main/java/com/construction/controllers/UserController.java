package com.construction.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.construction.models.EmployeeData;
import com.construction.models.UpdatePassword;
import com.construction.models.User;
import com.construction.responses.GlobalResponseData;
import com.construction.responses.GlobalResponseListData;
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
	public ResponseEntity<GlobalResponseData> updateUser(@RequestBody User updatedUserBasicDetails) {
			return userService.updatedUserBasicDetails(updatedUserBasicDetails);
	}
	
	@PutMapping("/updateuserprofessionaldetails")
	public ResponseEntity<GlobalResponseData> updateUserProfessionalDetails(@RequestBody EmployeeData updatedUserProfessionalDetails) {
			return userService.updateUserProfessionalDetails(updatedUserProfessionalDetails);
	}
	
	@GetMapping("/getallbyoccupation/{occupationName}")
	public ResponseEntity<GlobalResponseListData> getAllUsers(@PathVariable("occupationName") String occupationName) {
		return userService.getByOccupation(occupationName);
	}
	
	@PutMapping("/updatepassword")
	public ResponseEntity<GlobalResponseData> updatePassword(@RequestBody UpdatePassword updatedPassword) {
		return userService.updatePassword(updatedPassword);
	}
	
}

