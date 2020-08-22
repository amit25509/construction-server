package com.construction.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.construction.models.EmployeeData;
import com.construction.repository.EmployeeDataRepository;
import com.construction.responses.GlobalResponseData;

@Service
public class EmployeeDataService {

	@Autowired
	EmployeeDataRepository employeeDataRepository;
	GlobalResponseData globalResponseData;

//	======================== UPDATE EMPLOYEE DATA ========================

	public ResponseEntity<GlobalResponseData> updateEmployeeData(Integer id, EmployeeData updateEmployeeData) {
		// TODO Auto-generated method stub
		Optional<EmployeeData> existningEmployeeData = employeeDataRepository.findById(id);

		if (existningEmployeeData.isPresent()) {
			employeeDataRepository.save(updateEmployeeData);
			globalResponseData = new GlobalResponseData(true, 201, "success", updateEmployeeData);
			return new ResponseEntity<>(globalResponseData, HttpStatus.CREATED);
		} else {

			globalResponseData = new GlobalResponseData(false, 404, "Failure:Result Not Found");
			return new ResponseEntity<>(globalResponseData, HttpStatus.NOT_FOUND);
		}
	}

//	======================== GET EMPLOYEE DATA BY USERNAME ========================

	public ResponseEntity<GlobalResponseData> getEmployeeDataByUsername() {
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

			List<EmployeeData> employeeData = employeeDataRepository.findEmployeeDataByUsername(username);

			if (employeeData.isEmpty()) {
				globalResponseData = new GlobalResponseData(false, 404, "Failure:Result Not Found");
				return new ResponseEntity<>(globalResponseData, HttpStatus.NOT_FOUND);
			} else {
				globalResponseData = new GlobalResponseData(true, 200, "success", employeeData);
				return new ResponseEntity<>(globalResponseData, HttpStatus.OK);
			}
		} catch (Exception e) {
			globalResponseData = new GlobalResponseData(false, 500, "Failure:Internal Server Error");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	======================== UPDATE EMPLOYEE AVAILABILITY STATUS ========================

	public ResponseEntity<GlobalResponseData> updateAvailability(Boolean newAvailability) {
		// TODO Auto-generated method stub
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
			if (newAvailability) {
				employeeDataRepository.updateEmployeeAvailability(username, false);
				globalResponseData = new GlobalResponseData(true, 201, "success", null);
				return new ResponseEntity<>(globalResponseData, HttpStatus.OK);
			}
			employeeDataRepository.updateEmployeeAvailability(username, true);
			globalResponseData = new GlobalResponseData(true, 201, "success", null);
			return new ResponseEntity<>(globalResponseData, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			globalResponseData = new GlobalResponseData(false, 500, "Failure:Internal Server Error");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
