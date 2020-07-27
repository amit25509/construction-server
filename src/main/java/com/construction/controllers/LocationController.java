package com.construction.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.construction.repository.LocationsRepository;
import com.construction.responses.GlobalResponseListData;
import com.construction.service.LocationService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/location")
public class LocationController {
	@Autowired
	LocationsRepository locationRepository;
	
	@Autowired
	private LocationService locationService;
	
	GlobalResponseListData globalResponseListData;

	@GetMapping("/getall")
	public ResponseEntity<?> getAlllocation() {
		
			return locationService.getAllLocation();

	}	
	}
