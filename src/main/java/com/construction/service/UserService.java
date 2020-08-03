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
	
	
	
	
	public ResponseEntity<GlobalResponseData> updateUser(User newUser) {
		// TODO Auto-generated method stub
		String username = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			username = currentUserName;
		}
		Optional<User> existingUser = userRepository.findByUsername(username);
		if (existingUser.isPresent()) {
			
			
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
			newUser.setUsername(String.valueOf(newUser.getPhone()));
			userRepository.save(newUser);
			globalResponseData =new GlobalResponseData(true, 201, "success",newUser);
			return new ResponseEntity<>(globalResponseData, HttpStatus.CREATED);
		}
		else {
			
			globalResponseData = new GlobalResponseData(false, 404, "Failure:Result Not Found");
			return new ResponseEntity<>(globalResponseData,HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	
	
	public ResponseEntity<GlobalResponseData> updateUserById(Long id, User updatedUser) {
		// TODO Auto-generated method stub
		
		Optional<User> existingUser = userRepository.findById(id);
		updatedUser.setUsername(updatedUser.getPhone().toString());
		updatedUser.setPassword(encoder.encode(updatedUser.getPassword()));
		
		if (existingUser.isPresent()) {
			userRepository.save(updatedUser);
			globalResponseData =new GlobalResponseData(true, 201, "success",updatedUser);
			return new ResponseEntity<>(globalResponseData, HttpStatus.CREATED);
		}
		else {
			
			globalResponseData = new GlobalResponseData(false, 404, "Failure:Result Not Found");
			return new ResponseEntity<>(globalResponseData,HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<GlobalResponseListData> getByOccupation(Integer occupationId) {
		
		try {
			List<User> users = userRepository.getByOccupation(occupationId);

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

}