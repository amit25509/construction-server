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
import com.construction.models.User;
import com.construction.repository.AddressRepository;
import com.construction.repository.LocationsRepository;
import com.construction.repository.UserRepository;
import com.construction.responses.GlobalResponseData;

@Service
public class AddressService {

	@Autowired
	LocationsRepository locationRepository;
	@Autowired
	AddressRepository addressRepository;
	GlobalResponseData globalResponseData;
	@Autowired
	UserRepository userRepository;
	
	
	//========================UPDATE BY ADDRESS BY USERNAME========================
	
	public ResponseEntity<GlobalResponseData> addAddressByUsername(Address address) {
		String username = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			username = currentUserName;
		}
		Optional<User> user=userRepository.findByUsername(username);
		
		if (user.isPresent()) {
			User existingUser=user.get();
			address.setUser(existingUser);
			addressRepository.save(address);
			globalResponseData =new GlobalResponseData(true, 201, "success",address);
			return new ResponseEntity<>(globalResponseData, HttpStatus.CREATED);
		}
		else {
			
			globalResponseData = new GlobalResponseData(false, 404, "Failure:Result Not Found");
			return new ResponseEntity<>(globalResponseData,HttpStatus.NOT_FOUND);
		}
	}
	
	//========================UPDATE BY ADDRESS BY ADDRESS_ID========================	
	public ResponseEntity<GlobalResponseData> updateAddress(Integer id, Address updateAddress) {
		String username = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			username = currentUserName;
		}
		
		Optional<Address> existningAddress = addressRepository.findById(id);
		
		if(userRepository.findByUsername(username).get().getId()==existningAddress.get().getUser().getId()) {
			if (existningAddress.isPresent()) {
				existningAddress.map(add -> {
					add.setBuildingName(updateAddress.getBuildingName());
					add.setStreet(updateAddress.getStreet());
					add.setCity(updateAddress.getCity());
					add.setState(updateAddress.getState());
					add.setPostalCode(updateAddress.getPostalCode());
					add.setIsPrimary(updateAddress.getIsPrimary());
		            return addressRepository.save(add);
		        });
			}
			globalResponseData =new GlobalResponseData(true, 201, "success",updateAddress);
			return new ResponseEntity<>(globalResponseData, HttpStatus.CREATED);
		}
		else {
			globalResponseData = new GlobalResponseData(false, 404, "Failure:Result Not Found");
			return new ResponseEntity<>(globalResponseData,HttpStatus.NOT_FOUND);
		}
	}
	
	
	//========================GET BY ADDRESS BY USERNAME========================
	
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
	
	
	//========================UPDATE BY ADDRESS BY USERNAME========================
	
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


	public ResponseEntity<GlobalResponseData> deleteAddress(Integer id) {
		try {
			Optional<Address> address = addressRepository.findById(id);
			if (address.isPresent()) {
				addressRepository.deleteById(id);;
				globalResponseData = new GlobalResponseData(true, 200, "success", null);
				return new ResponseEntity<>(globalResponseData, HttpStatus.OK);
			} else {
				System.out.println("else");
				globalResponseData = new GlobalResponseData(false, 404, "Failure:Result Not Found");
				return new ResponseEntity<>(globalResponseData, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			globalResponseData = new GlobalResponseData(false, 500, "Failure:Internal Server Error");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
