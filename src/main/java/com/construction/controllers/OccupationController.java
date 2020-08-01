package com.construction.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.construction.repository.LocationsRepository;
import com.construction.responses.GlobalResponseListData;
import com.construction.service.OccupationService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/occupation")
public class OccupationController {
	@Autowired
	LocationsRepository locationRepository;
	
	@Autowired
	private OccupationService occupationService;
	
	GlobalResponseListData globalResponseListData;

	@GetMapping("/getall")
	public ResponseEntity<?> getAllOccupations() {
		
			return occupationService.getAllOccupations();

	}	
	}
