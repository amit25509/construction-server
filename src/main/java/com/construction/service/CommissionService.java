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

import com.construction.models.Commissions;
import com.construction.repository.CommissionsRepository;
import com.construction.responses.GlobalResponseData;
import com.construction.responses.GlobalResponseListData;

@Service
public class CommissionService {

	@Autowired
	CommissionsRepository commissionRepository;

	GlobalResponseData globalResponseData;

	GlobalResponseListData globalResponseListData;
	
	
//	======================== GET COMMISSION BY COMMISSION ID ========================
	
	public ResponseEntity<GlobalResponseData> getCommissionById(int id) {
			
			try {
	
				Optional<Commissions> commission = commissionRepository.findById(id);
	
				if (commission.isPresent()) {
					globalResponseData = new GlobalResponseData(true, 200, "success", commission.get());
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
	
//	======================== GET ALL COMMISSION ========================
	
	public ResponseEntity<GlobalResponseListData> getAllCommissions() {
		
		try {
			List<Commissions> commission = commissionRepository.findAll();

			if (commission.isEmpty()) {
				globalResponseListData = new GlobalResponseListData(false, 404, "Failure:Result Not Found");
				return new ResponseEntity<>(globalResponseListData, HttpStatus.NOT_FOUND);
			} else {
				globalResponseListData = new GlobalResponseListData(true, 200, "success", commission);
				return new ResponseEntity<>(globalResponseListData, HttpStatus.OK);

			}
		} catch (Exception e) {
			globalResponseListData = new GlobalResponseListData(false, 500, "Failure:Internal Server Error");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

//	======================== GET COMMISSION BY USERNAME ========================
	
	public ResponseEntity<GlobalResponseListData> getCommissionsByUsername() {
		String username = null;
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (!(authentication instanceof AnonymousAuthenticationToken)) {
				String currentUserName = authentication.getName();
				username = currentUserName;
			}
		try {
			if(username==null)
			{
				globalResponseListData = new GlobalResponseListData(false,401, "Failure: Authentication Failed");
				return new ResponseEntity<>(globalResponseListData, HttpStatus.UNAUTHORIZED);
			}
			
			List<Commissions> commission = commissionRepository.findCommissionsByUsername(username);

			if (commission.isEmpty()) {
				globalResponseListData = new GlobalResponseListData(false, 404, "Failure:Result Not Found");
				return new ResponseEntity<>(globalResponseListData, HttpStatus.NOT_FOUND);
			} else {
				globalResponseListData = new GlobalResponseListData(true, 200, "success", commission);
				return new ResponseEntity<>(globalResponseListData, HttpStatus.OK);
			}
		} catch (Exception e) {
			globalResponseListData = new GlobalResponseListData(false, 500, "Failure:Internal Server Error");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
//	public ResponseEntity<GlobalResponseListData> getCommissionTotals() {
//		String username = null;
//			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//			if (!(authentication instanceof AnonymousAuthenticationToken)) {
//				String currentUserName = authentication.getName();
//				username = currentUserName;
//			}
//		try {
//			if(username==null)
//			{
//				globalResponseListData = new GlobalResponseListData(false,401, "Failure: Authentication Failed");
//				return new ResponseEntity<>(globalResponseListData, HttpStatus.UNAUTHORIZED);
//			}
//			
//			List<Integer> totalData = commissionRepository.findcommissionsTotal(username);
//			for(int total: totalData)
//				System.out.println(total);
//			
//
//			if (totalData.isEmpty()) {
//				globalResponseListData = new GlobalResponseListData(false, 404, "Failure:Result Not Found");
//				return new ResponseEntity<>(globalResponseListData, HttpStatus.NOT_FOUND);
//			} else {
//				globalResponseListData = new GlobalResponseListData(true, 200, "success", null);
//				return new ResponseEntity<>(globalResponseListData, HttpStatus.OK);
//			}
//		} catch (Exception e) {
//			globalResponseListData = new GlobalResponseListData(false, 500, "Failure:Internal Server Error");
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}


//	======================== ADD COMMISSION ========================
	
	public ResponseEntity<GlobalResponseData> addCommission(Commissions add) {
		try {
			Commissions commission = commissionRepository.save(new Commissions(
					add.getBookingId(),
					add.getTotalCommissionAmount(),
					add.getDueCommissionAmount(),
					add.getCommissionStatus(),
					add.getPaidCommissionAmount()
					)); 
			globalResponseData= new GlobalResponseData(true, 200, "success",commission);
			return new ResponseEntity<>(globalResponseData, HttpStatus.OK);
		} catch (Exception e) {
			globalResponseData= new GlobalResponseData(false, 417, "Failure:Data Expectation Failed");
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	public ResponseEntity<GlobalResponseData> updateCommission(Integer id, Commissions newCommission) {
		// TODO Auto-generated method stub
		
		Optional<Commissions> existingCommission = commissionRepository.findById(id);

		if (existingCommission.isPresent()) {
			commissionRepository.save(newCommission);
			globalResponseData =new GlobalResponseData(true, 201, "success",newCommission);
			return new ResponseEntity<>(globalResponseData, HttpStatus.CREATED);
		}
		else {
			
			globalResponseData = new GlobalResponseData(false, 404, "Failure:Result Not Found");
			return new ResponseEntity<>(globalResponseData,HttpStatus.NOT_FOUND);
		}
	}

}
