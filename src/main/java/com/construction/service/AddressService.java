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

import com.construction.models.Address;
import com.construction.repository.AddressRepository;
import com.construction.repository.LocationsRepository;
import com.construction.responses.GlobalResponseData;

@Service
public class AddressService {

	@Autowired
	LocationsRepository locationRepository;
	@Autowired
	AddressRepository addressRepository;
	GlobalResponseData globalResponseData;
	
	public ResponseEntity<GlobalResponseData> updateAddress(Integer id, Address updateAddress) {
		// TODO Auto-generated method stub
		Optional<Address> existningAddress = addressRepository.findById(id);

		if (existningAddress.isPresent()) {
			updateAddress.setAddressId(id);
			addressRepository.save(updateAddress);
			globalResponseData =new GlobalResponseData(true, 201, "success",updateAddress);
			return new ResponseEntity<>(globalResponseData, HttpStatus.CREATED);
		}
		else {
			
			globalResponseData = new GlobalResponseData(false, 404, "Failure:Result Not Found");
			return new ResponseEntity<>(globalResponseData,HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<GlobalResponseData> getAddressByUsername() {
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
			
			List<Address> address = addressRepository.findAddressByUsername(username);

			if (address.isEmpty()) {
				globalResponseData = new GlobalResponseData(false, 404, "Failure:Result Not Found");
				return new ResponseEntity<>(globalResponseData, HttpStatus.NOT_FOUND);
			} else {
				globalResponseData = new GlobalResponseData(true, 200, "success", address);
				return new ResponseEntity<>(globalResponseData, HttpStatus.OK);
			}
		} catch (Exception e) {
			globalResponseData = new GlobalResponseData(false, 500, "Failure:Internal Server Error");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<GlobalResponseData> updateAddressByUsername(Address updateAddress) {
		// TODO Auto-generated method stub
		String username = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			username = currentUserName;
		}
		Optional<Address> existningAddress = addressRepository.findByUsername(username);

		if (existningAddress.isPresent()) {
			addressRepository.save(updateAddress);
			globalResponseData =new GlobalResponseData(true, 201, "success",updateAddress);
			return new ResponseEntity<>(globalResponseData, HttpStatus.CREATED);
		}
		else {
			
			globalResponseData = new GlobalResponseData(false, 404, "Failure:Result Not Found");
			return new ResponseEntity<>(globalResponseData,HttpStatus.NOT_FOUND);
		}
	}

}
