package com.construction.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.construction.models.EmployeeData;
import com.construction.models.UpdatePassword;
import com.construction.models.User;
import com.construction.repository.EmployeeDataRepository;
import com.construction.repository.UserRepository;
import com.construction.responses.GlobalResponseData;
import com.construction.responses.GlobalResponseListData;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	EmployeeDataRepository employeeDataRepository;

	GlobalResponseData globalResponseData;

	GlobalResponseListData globalResponseListData;

	@Autowired
	PasswordEncoder encoder;

	// ====================GET USER DETAILS BY USERNAME==========================
	public ResponseEntity<GlobalResponseData> getUserByUsername() {
		String username = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			username = currentUserName;
		}
		try {
			if (username == null) {
				globalResponseData = new GlobalResponseData(false, 401, "Failure: Authentication Failed");
				return new ResponseEntity<>(globalResponseData, HttpStatus.UNAUTHORIZED);
			}

			Optional<User> user = userRepository.findByUsername(username);
			if (!user.isPresent()) {
				globalResponseData = new GlobalResponseData(false, 404, "Failure:Result Not Found");
				return new ResponseEntity<>(globalResponseData, HttpStatus.NOT_FOUND);
			} else {
				globalResponseData = new GlobalResponseData(true, 200, "success", user);
				return new ResponseEntity<>(globalResponseData, HttpStatus.OK);
			}
		} catch (Exception e) {
			globalResponseData = new GlobalResponseData(false, 500, "Failure:Internal Server Error");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// ====================GET ALL EMPLOYEE USER==========================
	public ResponseEntity<GlobalResponseListData> getAllEmployee() {
		try {

			List<String> user = userRepository.findAllEmployees();
			if (user.isEmpty()) {
				globalResponseListData = new GlobalResponseListData(false, 404, "Failure:Result Not Found");
				return new ResponseEntity<>(globalResponseListData, HttpStatus.NOT_FOUND);
			} else {
				globalResponseListData = new GlobalResponseListData(true, 200, "success", user);
				return new ResponseEntity<>(globalResponseListData, HttpStatus.OK);
			}
		} catch (Exception e) {
			globalResponseListData = new GlobalResponseListData(false, 500, "Failure:Internal Server Error");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// ====================UPDATE USER BASIC DETAILS BY USERNAME
	// ==========================

	public ResponseEntity<GlobalResponseData> updatedUserBasicDetails(User updatedUserBasicDetails) {
		// TODO Auto-generated method stub
		String username = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			username = currentUserName;
		}
		System.out.println("========================================" + username);
		Optional<User> existingUser = userRepository.findByUsername(username);
		if (existingUser.isPresent()) {
			existingUser.map(user -> {
				if (updatedUserBasicDetails.getName() != null)
					user.setName(updatedUserBasicDetails.getName());
				if (updatedUserBasicDetails.getAge() != null)
					user.setAge(updatedUserBasicDetails.getAge());
				if (updatedUserBasicDetails.getDob() != null)
					user.setDob(updatedUserBasicDetails.getDob());
				if (updatedUserBasicDetails.getEmail() != null)
					user.setEmail(updatedUserBasicDetails.getEmail());
				if (updatedUserBasicDetails.getPhone() != null)
					user.setPhone(updatedUserBasicDetails.getPhone());
				if (updatedUserBasicDetails.getPhone() != null)
					user.setUsername(updatedUserBasicDetails.getPhone().toString());
				if (updatedUserBasicDetails.getPassword() != null)
					user.setPassword(encoder.encode(updatedUserBasicDetails.getPassword()));
				if (updatedUserBasicDetails.getLocation() != null)
					user.setLocation(updatedUserBasicDetails.getLocation());
				if (updatedUserBasicDetails.getImage() != null)
					user.setImage(updatedUserBasicDetails.getImage());

				return userRepository.save(user);
			});

			globalResponseData = new GlobalResponseData(true, 201, "success", updatedUserBasicDetails);
			return new ResponseEntity<>(globalResponseData, HttpStatus.CREATED);
		} else {

			globalResponseData = new GlobalResponseData(false, 404, "Failure:Result Not Found");
			return new ResponseEntity<>(globalResponseData, HttpStatus.NOT_FOUND);
		}
	}

// ==================UPDATE USER PROFESSIONAL DETAILS BY USERNAME ========================

	public ResponseEntity<GlobalResponseData> updateUserProfessionalDetails(
			EmployeeData updatedUserProfessionalDetails) {
		String username = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			username = currentUserName;
		}
		System.out.println("========================================" + username);
		Optional<EmployeeData> existingUserEmpData = Optional
				.of(userRepository.findByUsername(username).get().getEmployeeData());
		if (existingUserEmpData.isPresent()) {
			existingUserEmpData.map(user -> {
				if (updatedUserProfessionalDetails.getAadharFront() != null)
					user.setAadharFront(updatedUserProfessionalDetails.getAadharFront());
				if (updatedUserProfessionalDetails.getAadharBack() != null)
					user.setAadharBack(updatedUserProfessionalDetails.getAadharBack());
				if (updatedUserProfessionalDetails.getExperience() != null)
					user.setExperience(updatedUserProfessionalDetails.getExperience());
				if (updatedUserProfessionalDetails.getOccupation() != null)
//					System.out.println("============-------------=========="+updatedUserProfessionalDetails.getOccupation().getOccupationId());
					user.setOccupation(updatedUserProfessionalDetails.getOccupation());
				if (updatedUserProfessionalDetails.getPerDayCharge() != null)
					user.setPerDayCharge(updatedUserProfessionalDetails.getPerDayCharge());
				return employeeDataRepository.save(user);
			});

			globalResponseData = new GlobalResponseData(true, 201, "success", updatedUserProfessionalDetails);
			return new ResponseEntity<>(globalResponseData, HttpStatus.CREATED);

		} else {
			globalResponseData = new GlobalResponseData(false, 404, "Failure:Result Not Found");
			return new ResponseEntity<>(globalResponseData, HttpStatus.NOT_FOUND);
		}
	}

//============================= GET EMPLOYEE BY OCCUPATION =============================

	public ResponseEntity<GlobalResponseListData> getByOccupation(String occupationName) {

		try {
			List<User> users = userRepository.getByOccupation(occupationName);

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

//====================UPDATE PASSWORD BY USER==========================

	public ResponseEntity<GlobalResponseData> updatePassword(UpdatePassword updatedPassword) {

		String username = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			username = currentUserName;
		}

		Optional<User> existingUser = userRepository.findByUsername(username);
		if (encoder.matches(updatedPassword.getOldPassword(), existingUser.get().getPassword())) {
			if (existingUser.isPresent()) {
				User user = existingUser.get();
				user.setPassword(encoder.encode(updatedPassword.getNewPassword()));
				userRepository.save(user);
				globalResponseData = new GlobalResponseData(true, 201, "success", user);
			}
			return new ResponseEntity<>(globalResponseData, HttpStatus.CREATED);
		}

		else {

			globalResponseData = new GlobalResponseData(false, 404, "Failure:Result Not Found");
			return new ResponseEntity<>(globalResponseData, HttpStatus.NOT_FOUND);
		}
	}

}