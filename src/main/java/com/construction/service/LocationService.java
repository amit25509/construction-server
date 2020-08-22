package com.construction.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.construction.models.Locations;
import com.construction.repository.LocationsRepository;
import com.construction.responses.GlobalResponseListData;

@Service
public class LocationService {

	@Autowired
	LocationsRepository locationRepository;
	GlobalResponseListData globalResponseListData;
	
//	======================== GET ALL LOCATIONS ========================
	
	public ResponseEntity<?> getAllLocation(){
		try
		{
		List<Locations> location=locationRepository.findAll();
	
		if (location.isEmpty()) {
			globalResponseListData = new GlobalResponseListData(false, 404, "Failure:Result Not Found");
			return new ResponseEntity<>(globalResponseListData,HttpStatus.NOT_FOUND);
		}

		globalResponseListData =new GlobalResponseListData(true, 200, "success",location);
		return new ResponseEntity<>(globalResponseListData, HttpStatus.OK);
		} 
		catch (Exception e) {
		globalResponseListData= new GlobalResponseListData(false, 500, "Failure:Internal Server Error");
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}
}
