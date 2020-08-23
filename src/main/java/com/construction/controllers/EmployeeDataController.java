package com.construction.controllers;
import java.util.Map;

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
import com.construction.responses.GlobalResponseData;
import com.construction.service.EmployeeDataService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/employeedata")
public class EmployeeDataController {
	
	
	@Autowired
	private EmployeeDataService employeeDataService;
	
	GlobalResponseData globalResponseData;

	@PutMapping("/updatebyid/{id}")
	public ResponseEntity<GlobalResponseData> updateEmployeeData(@PathVariable("id") Integer id, @RequestBody EmployeeData updateEmployeeData) {
		
			return employeeDataService.updateEmployeeData(id, updateEmployeeData);
	}
	
	@GetMapping("/getbyusername")
	public ResponseEntity<GlobalResponseData> getEmployeeDataByByUsername() {
		
		return employeeDataService.getEmployeeDataByUsername();
	}

	@PutMapping("/updateavailability")
	public ResponseEntity<GlobalResponseData> updateAvailability(@RequestBody Map<String,Boolean> newAvailability)
	{
		return employeeDataService.updateAvailability(newAvailability.get("availability"));
	}
}

