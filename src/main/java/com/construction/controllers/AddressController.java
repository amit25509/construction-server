package com.construction.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.construction.models.Address;
import com.construction.repository.LocationsRepository;
import com.construction.responses.GlobalResponseData;
import com.construction.service.AddressService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/address")
public class AddressController {
	@Autowired
	LocationsRepository locationRepository;

	@Autowired
	private AddressService addressService;

	GlobalResponseData globalResponseData;
	
	@PostMapping("/addadressbyusername")
	public ResponseEntity<GlobalResponseData> addAddressByUsername(@RequestBody Address address) {
		return addressService.addAddressByUsername(address);
	}

	@GetMapping("/getbyusername")
	public ResponseEntity<GlobalResponseData> getCommissionsByUsername() {
		return addressService.getAddressByUsername();
	}

	@PutMapping("/updatebyid/{id}")
	public ResponseEntity<GlobalResponseData> updateAddress(@PathVariable("id") Integer id,
			@RequestBody Address updateAddress) {
		return addressService.updateAddress(id, updateAddress);
	}

//	@PutMapping("/updatebyusername")
//	public ResponseEntity<GlobalResponseData> updateAddressByUsername(@RequestBody Address updateAddress) {
//		return addressService.updateAddressByUsername(updateAddress);
//	}

	@DeleteMapping("/deletebyid/{id}")
	public ResponseEntity<GlobalResponseData> deleteAddress(@PathVariable("id") Integer id) {
		return addressService.deleteAddress(id);
	}
}
