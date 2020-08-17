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

import com.construction.models.UpdatePassword;
import com.construction.models.User;
import com.construction.repository.UserRepository;
import com.construction.responses.GlobalResponseData;
import com.construction.responses.GlobalResponseListData;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	GlobalResponseData globalResponseData;
	
	GlobalResponseListData globalResponseListData;
	
	@Autowired
	PasswordEncoder encoder;
	
	//====================GET USER DETAILS BY USERNAME==========================
	public ResponseEntity<GlobalResponseData> getUserByUsername() {
		String username = null;
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (!(authentication instanceof AnonymousAuthenticationToken)) {
				String currentUserName = authentication.getName();
				username = currentUserName;
			}
		try {
			if(username==null)
			{
				globalResponseData = new GlobalResponseData(false,401, "Failure: Authentication Failed");
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
	
	
	
	//====================GET ALL EMPLOYEE USER==========================
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
	

	
	//====================UPDATE USER DETAILS BY USERNAME==========================
	
	public ResponseEntity<GlobalResponseData> updateUser(User updatedUser) {
		// TODO Auto-generated method stub
		String username = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			username = currentUserName;
		}
		System.out.println("========================================"+username);
		Optional<User> existingUser = userRepository.findByUsername(username);
		if (existingUser.isPresent()) {
			existingUser.map(user -> {
				user.setName(updatedUser.getName());
				user.setAge(updatedUser.getAge());
				user.setDob(updatedUser.getDob());
				user.setEmail(updatedUser.getEmail());
				if(updatedUser.getPhone() != null)
					user.setPhone(updatedUser.getPhone());
				if(updatedUser.getPhone() != null)
					user.setUsername(updatedUser.getPhone().toString());
				if(updatedUser.getPassword() != null)
					user.setPassword(encoder.encode(updatedUser.getPassword()));
				if(updatedUser.getLocation() != null)
					user.setLocation(updatedUser.getLocation());
				if(updatedUser.getEmployeeData() != null) {
					user.setEmployeeData(updatedUser.getEmployeeData());
				}
	            return userRepository.save(user);
	        });
			
			/*
			 * FOR AVOIDING NULL VALUE FOR SPECIFIC FIELDS
			User tempUser= existingUser.get();
			if(newUser.getUsername()==null) {
				newUser.setUsername(tempUser.getUsername());
			}
			if(newUser.getAddressId()==null) {
				newUser.setAddressId((tempUser.getAddressId()));
			}
			if(newUser.getEmployeeData()==null) {
				newUser.setEmployeeData(tempUser.getEmployeeData());
			}
			if(newUser.getEmployeeData().getId()==null) {
				newUser.setEmployeeData(tempUser.getEmployeeData());
			}
			*/
//			newUser.setUsername(String.valueOf(newUser.getPhone()));
//			userRepository.save(newUser);
			globalResponseData =new GlobalResponseData(true, 201, "success",updatedUser);
			return new ResponseEntity<>(globalResponseData, HttpStatus.CREATED);
		}
		else {
			
			globalResponseData = new GlobalResponseData(false, 404, "Failure:Result Not Found");
			return new ResponseEntity<>(globalResponseData,HttpStatus.NOT_FOUND);
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

//			userRepository.save(updatedUser);
			globalResponseData =new GlobalResponseData(true, 201, "success",updatedUser);
			return new ResponseEntity<>(globalResponseData, HttpStatus.CREATED);
		}
		else {
			
			globalResponseData = new GlobalResponseData(false, 404, "Failure:Result Not Found");
			return new ResponseEntity<>(globalResponseData,HttpStatus.NOT_FOUND);
		}
	}
	
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
		if(encoder.matches(updatedPassword.getOldPassword(), existingUser.get().getPassword())) {
			if (existingUser.isPresent()) {
				User user=existingUser.get();
				user.setPassword(encoder.encode(updatedPassword.getNewPassword()));
				userRepository.save(user);
				globalResponseData =new GlobalResponseData(true, 201, "success",user);
			}
			return new ResponseEntity<>(globalResponseData, HttpStatus.CREATED);
		}
		
		else {
			
			globalResponseData = new GlobalResponseData(false, 404, "Failure:Result Not Found");
			return new ResponseEntity<>(globalResponseData,HttpStatus.NOT_FOUND);
		}
	}




}