package com.construction.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.construction.models.Bookings;
import com.construction.models.User;
import com.construction.repository.CommissionsRepository;
import com.construction.repository.UserRepository;
import com.construction.responses.GlobalResponseData;
import com.construction.responses.GlobalResponseListData;
import com.construction.service.AdminService;
import com.construction.service.BookingService;
import com.construction.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	BookingService bookingService;

	@Autowired
	CommissionsRepository commissionRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	private UserService userService;

	@Autowired
	ObjectMapper mapper;

	GlobalResponseData globalResponseData;

	GlobalResponseListData globalResponseListData;

	
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<GlobalResponseData> getUserById(@PathVariable("id") Long id) {
		return adminService.getUserById(id);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<GlobalResponseListData> getAllUsers() {
		return adminService.getAllUsers();
	}
	
	@GetMapping("/getallemployee")
	public ResponseEntity<GlobalResponseListData> getAllEmployee() {
		return userService.getAllEmployee();
	}
	
	@PutMapping("/updatebyid/{id}")
	public ResponseEntity<GlobalResponseData> updateUserById(@PathVariable("id") Long id, @RequestBody User updatedUser) {
		return adminService.updateUserById(id, updatedUser);
	}	
	
	@DeleteMapping("/deletebyid/{id}")
	public ResponseEntity<GlobalResponseData> deleteUserById(@PathVariable("id") Long id) {
		return adminService.deleteUserById(id);
	}
	
	@PutMapping("/userenabledisable/{id}")
	public ResponseEntity<GlobalResponseData> userEnableDisable(@PathVariable("id") Long id, @RequestBody Map<String,Boolean> newEnable)
	{
		return adminService.userEnableDisable(id,newEnable.get("isEnable"));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 1. GET ALL BOOKING

	
	@GetMapping("/booking/getall")
	public ResponseEntity<GlobalResponseListData> getAllBookings() {
		return bookingService.getAllBookings();
	}
	@GetMapping("/booking/getbyid/{id}")
	public ResponseEntity<GlobalResponseData> getBookingById(@PathVariable("id") Integer id) {
		return bookingService.getBookingById(id);

	}
	@PutMapping("/booking/updatebyid/{id}")
	public ResponseEntity<GlobalResponseData> updateBooking(@PathVariable("id") Integer id, @RequestBody Bookings newBooking) {
		
			return bookingService.updateBooking(id, newBooking);
	}	
	
	

//	//4. UPDATE BOOKINGS BY ID
//	
//	@PutMapping("/updatebookings/{id}")
//	public ResponseEntity<Bookings> updateBookings(@PathVariable("id") Integer id, @RequestBody Bookings updateBookings) {
//		
//		return bookingsService.updateBookings(id, updateBookings);
//		
//	}
//	
//
//	
//	//5. DELETE BOOKINGS BY ID
//	
//	@DeleteMapping("/deletebooking/{id}")
//	private ResponseEntity<HttpStatus> deleteBooking(@PathVariable("id") int id) {
//		try {
//			bookingsService.deleteBookings(id);
//			return new ResponseEntity<>(HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
//		}
//	}

}
