package com.construction.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.construction.models.ERole;
import com.construction.models.Roles;
import com.construction.models.User;
import com.construction.payload.request.LoginRequest;
import com.construction.payload.request.SignupRequest;
import com.construction.payload.response.JwtResponse;
import com.construction.repository.AddressRepository;
import com.construction.repository.LocationsRepository;
import com.construction.repository.RoleRepository;
import com.construction.repository.UserRepository;
import com.construction.responses.GlobalResponseData;
import com.construction.responses.GlobalResponseListData;
import com.construction.security.jwt.JwtUtils;
import com.construction.security.services.UserDetailsImpl;

@Service
public class AuthService {

	@Autowired
	UserRepository userRepository;

	GlobalResponseData globalResponseData;

	GlobalResponseListData globalResponseListData;
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	LocationsRepository locationRepository;
	
	@Autowired
	AddressRepository addressRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	
//	======================== LOGIN ========================
	
	public ResponseEntity<GlobalResponseData> authenticateUser(LoginRequest loginRequest) {
		try {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();	
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		globalResponseData= new GlobalResponseData(true, 201, "success",new JwtResponse(jwt, 
				 userDetails.getId(),
				 userDetails.getUsername(), 
				 userDetails.getEmail(),
				 roles,
				 userDetails.getName(),
				 userDetails.getIsVerified(),
				 userDetails.isEnabled()));
		return new ResponseEntity<>(globalResponseData, HttpStatus.CREATED);
		
		} catch (Exception e) {
			globalResponseData= new GlobalResponseData(false, 400, "Bad Credentials",null);
			return new ResponseEntity<>(globalResponseData, HttpStatus.BAD_REQUEST);
		}
	}
		
		
		
		
//	======================== REGISTER ========================	
	
	public ResponseEntity<GlobalResponseData> registerUser(SignupRequest signUpRequest) {
		String userName = String.valueOf(signUpRequest.getPhone());
		if (userRepository.existsByUsername(userName)) {
			globalResponseData = new GlobalResponseData(false, 400, "Error: Phone is already in use!");
			return new ResponseEntity<>(globalResponseData, HttpStatus.BAD_REQUEST);
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			globalResponseData = new GlobalResponseData(false, 400, "Error: Email is already in use!");
			return new ResponseEntity<>(globalResponseData, HttpStatus.BAD_REQUEST);
		}

		signUpRequest.setIsEnabled(true);
//		signUpRequest.getEmployeeData().setVerified(false);
		Date createdDate= new Date();
		Date lastModifiedDate=new Date();
		
		System.out.println("Adress-------------------"+signUpRequest.getAddress());
		// Create new user's account
		User user = new User(signUpRequest.getName(),
							 userName, 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()),
							 signUpRequest.getPhone(), 
							 signUpRequest.getAge(),
							 signUpRequest.getImage(), 
							 signUpRequest.getLocation(),
							 signUpRequest.getAddress(),
							 signUpRequest.getEmployeeData(),
							 signUpRequest.getDob(),
							 signUpRequest.getIsEnabled(),
							 createdDate,
							 lastModifiedDate,
							 signUpRequest.getName()
							 );

		Set<String> strRoles = signUpRequest.getRoles();
		Set<Roles> roles = new HashSet<>();

		if (strRoles == null) {
			Roles userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Roles adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "emp":
					Roles modRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Roles userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}
		

		user.setRoles(roles);
		user.getAddress();
		userRepository.save(user);
		globalResponseData= new GlobalResponseData(true, 201, "success",user);
		return new ResponseEntity<>(globalResponseData, HttpStatus.CREATED);
	}
}
