package com.construction.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.construction.models.Bookings;
import com.construction.models.User;
import com.construction.repository.BookingsRepository;
import com.construction.repository.CommissionsRepository;
import com.construction.repository.EmployeeDataRepository;
import com.construction.repository.UserRepository;
import com.construction.responses.GlobalResponseData;
import com.construction.responses.GlobalResponseListData;

@Service
public class AdminService {

	@Autowired
	BookingsRepository bookingRepository;
	@Autowired
	UserRepository userRepository;

	@Autowired
	CommissionsRepository commissionRepository;

	@Autowired
	CommissionService commissionService;

	@Autowired
	EmployeeDataRepository employeeDataRepository;

	@Autowired
	EntityManager entityManager;
	
	@Autowired
	PasswordEncoder encoder;

	GlobalResponseData globalResponseData;

	GlobalResponseListData globalResponseListData;


//	========================GET ALL USERS(CUSTOEMRS & EMPLOYEE)========================

	public ResponseEntity<GlobalResponseListData> getAllUsers() {

		try {
			List<User> users = userRepository.findAll();

			if (users.isEmpty()) {
				globalResponseListData = new GlobalResponseListData(false, 404, "Failure:Result Not Found");
				return new ResponseEntity<>(globalResponseListData, HttpStatus.NOT_FOUND);
			} else {
				globalResponseListData = new GlobalResponseListData(true, 200, "success", users);
				return new ResponseEntity<>(globalResponseListData, HttpStatus.OK);
			}
		} catch (Exception e) {
			globalResponseListData = new GlobalResponseListData(false, 500, "Failure:Internal Server Error");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

//	======================== GET USER BY ID ========================
	public ResponseEntity<GlobalResponseData> getUserById(Long id) {
		try {
			Optional<User> users = userRepository.findById(id);

			if (users.isPresent()) {

				globalResponseData = new GlobalResponseData(true, 200, "success", users.get());
				return new ResponseEntity<>(globalResponseData, HttpStatus.OK);
			} else {
				System.out.println("else");
				globalResponseData = new GlobalResponseData(false, 404, "Failure:Result Not Found");
				return new ResponseEntity<>(globalResponseData, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			globalResponseData = new GlobalResponseData(false, 500, "Failure:Internal Server Error");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//====================UPDATE USER DETAILS BY USER ID==========================
	
		public ResponseEntity<GlobalResponseData> updateUserById(Long id, User updatedUser) {
			// TODO Auto-generated method stub
			
			Optional<User> existingUser = userRepository.findById(id);
			
			if (existingUser.isPresent()) {
				existingUser.map(user -> {
					user.setName(updatedUser.getName());
					user.setAge(updatedUser.getAge());
					user.setDob(updatedUser.getDob());
					user.setEmail(updatedUser.getEmail());
					if(updatedUser.getIsEnabled() != null)
						user.setIsEnabled(updatedUser.getIsEnabled());
					user.setPhone(updatedUser.getPhone());
					user.setUsername(updatedUser.getPhone().toString());
					if(updatedUser.getPassword() != null)
						user.setPassword(encoder.encode(updatedUser.getPassword()));
					user.setLocation(updatedUser.getLocation());
					if(updatedUser.getRoles() != null)
						user.setRoles(updatedUser.getRoles());
		            return userRepository.save(user);
		        });

//				userRepository.save(updatedUser);
				globalResponseData =new GlobalResponseData(true, 201, "success",updatedUser);
				return new ResponseEntity<>(globalResponseData, HttpStatus.CREATED);
			}
			else {
				
				globalResponseData = new GlobalResponseData(false, 404, "Failure:Result Not Found");
				return new ResponseEntity<>(globalResponseData,HttpStatus.NOT_FOUND);
			}
		}

//====================DELETE USER DETAILS BY USER ID==========================

	public ResponseEntity<GlobalResponseData> deleteUserById(Long id) {
		try {
			Optional<User> user = userRepository.findById(id);
			if (user.isPresent()) {
				userRepository.deleteById(id);
				;
				globalResponseData = new GlobalResponseData(true, 200, "success", null);
				return new ResponseEntity<>(globalResponseData, HttpStatus.OK);
			} else {
				System.out.println("else");
				globalResponseData = new GlobalResponseData(false, 404, "Failure:Result Not Found");
				return new ResponseEntity<>(globalResponseData, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			globalResponseData = new GlobalResponseData(false, 500, "Failure:Internal Server Error");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	======================== UPDATE USER ENABLE/DISABLE STATUS ========================
	public ResponseEntity<GlobalResponseData> userEnableDisable(Long id, Boolean newEnable) {
		// TODO Auto-generated method stub

		try {
			System.out.println("ID----------------" + id + newEnable);
			Optional<User> user = userRepository.findById(id);
			if (user.isPresent()) {
				if (newEnable) {
					userRepository.userEnableDisable(id, false);
					globalResponseData = new GlobalResponseData(true, 201, "success", null);
					return new ResponseEntity<>(globalResponseData, HttpStatus.OK);
				}
				userRepository.userEnableDisable(id, true);
				globalResponseData = new GlobalResponseData(true, 201, "success", null);
				return new ResponseEntity<>(globalResponseData, HttpStatus.OK);
			}
			globalResponseData = new GlobalResponseData(false, 404, "Failure:Result Not Found");
			return new ResponseEntity<>(globalResponseData, HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			System.out.println(e);
			globalResponseData = new GlobalResponseData(false, 500, "Failure:Internal Server Error");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	========================GET ALL BOOKINGS OF ALL CUSTOMERS========================
	public ResponseEntity<GlobalResponseListData> getAllBookings() {

		try {
			List<Bookings> bookings = bookingRepository.findAll();

			if (bookings.isEmpty()) {
				globalResponseListData = new GlobalResponseListData(false, 404, "Failure:Result Not Found");
				return new ResponseEntity<>(globalResponseListData, HttpStatus.NOT_FOUND);
			} else {
				globalResponseListData = new GlobalResponseListData(true, 200, "success", bookings);
				return new ResponseEntity<>(globalResponseListData, HttpStatus.OK);

			}
		} catch (Exception e) {
			globalResponseListData = new GlobalResponseListData(false, 500, "Failure:Internal Server Error");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
