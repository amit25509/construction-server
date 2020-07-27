package com.construction.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.construction.models.Bookings;
import com.construction.models.Commissions;
import com.construction.models.User;
import com.construction.repository.BookingsRepository;
import com.construction.repository.CommissionsRepository;
import com.construction.repository.EmployeeDataRepository;
import com.construction.repository.UserRepository;
import com.construction.responses.GlobalResponseData;
import com.construction.responses.GlobalResponseListData;

@Service
public class BookingService {

	@Autowired
	BookingsRepository bookingRepository;
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CommissionsRepository commissionRepository;
	
	@Autowired
	CommissionService commissionService;
	
	@Autowired
	EmployeeDataRepository employeeDataRepository;
	
	@Autowired
	EntityManager entityManager;

	GlobalResponseData globalResponseData;

	GlobalResponseListData globalResponseListData;
	
	public ResponseEntity<GlobalResponseListData> getAllBookings() {
		
		try {
			List<Bookings> bookings = bookingRepository.findAll();

			if (bookings.isEmpty()) {
				globalResponseListData = new GlobalResponseListData(false, 404, "Failure:Result Not Found");
				return new ResponseEntity<>(globalResponseListData, HttpStatus.NOT_FOUND);
			} else {
				globalResponseListData = new GlobalResponseListData(true, 200, "success", bookings);
				return new ResponseEntity<>(globalResponseListData, HttpStatus.OK);

			}
		} catch (Exception e) {
			globalResponseListData = new GlobalResponseListData(false, 500, "Failure:Internal Server Error");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	public ResponseEntity<GlobalResponseListData> getBookingsByUsername() {
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
			
			List<Bookings> bookings = bookingRepository.findBookingsByUsername(username);
			
			
			if (bookings.isEmpty()) {
				globalResponseListData = new GlobalResponseListData(false, 404, "Failure:Result Not Found");
				return new ResponseEntity<>(globalResponseListData, HttpStatus.NOT_FOUND);
			} else {
				globalResponseListData = new GlobalResponseListData(true, 200, "success", bookings);
				return new ResponseEntity<>(globalResponseListData, HttpStatus.OK);

			}

			
		} catch (Exception e) {
			globalResponseListData = new GlobalResponseListData(false, 500, "Failure:Internal Server Error");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	
	

	public ResponseEntity<GlobalResponseData> addBooking(Bookings addBooking) {
		try {
			
			
			System.out.println("inside try booking-1");
			int daysWorked = (int) ((addBooking.getBookingTo().getTime() - addBooking.getBookingFrom().getTime())
					/ (24 * 60 * 60 * 1000));
			String status = "Pending";

			Bookings bookings = bookingRepository.save(new Bookings(
					addBooking.getBookingFrom(), 
					addBooking.getBookingTo(), 
					status,
					daysWorked, 
					addBooking.getUser(), 
					addBooking.getEmployee(),
					addBooking.getRating()));

			Optional<User> user = userRepository.findById(addBooking.getEmployee().getId());

			int totalCommissionAmount = daysWorked * user.get().getEmployeeData().getCommissionRate();
			commissionRepository.save(new Commissions(
					bookings, 
					totalCommissionAmount, 
					totalCommissionAmount, 
					"Pending"));
			globalResponseData= new GlobalResponseData(true, 200, "success",bookings);
			return new ResponseEntity<>(globalResponseData, HttpStatus.CREATED);
		} catch (Exception e) {
			globalResponseData = new GlobalResponseData(false, 417, "Failure:Data Expectation Failed");
			return new ResponseEntity<>(globalResponseData, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	public ResponseEntity<GlobalResponseData> getBookingById(int id) {
		
		
		try {
			
			
			Optional<Bookings> bookings = bookingRepository.findById(id);

			if (bookings.isPresent()) {

				globalResponseData = new GlobalResponseData(true, 200, "success", bookings.get());
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
	
	
	
	@Transactional
	@Modifying
	public ResponseEntity<GlobalResponseData> updateBooking(Integer id, Bookings newBooking) {
		// TODO Auto-generated method stub
		
		Optional<Bookings> existingBooking = bookingRepository.findById(id);

		if (existingBooking.isPresent()) {
			double newRating;
			double sum=0;
			
			List<Integer> empRating= bookingRepository.findEmployeeRating(existingBooking.get().getEmployee().getId());
			
			for(Integer empr: empRating) {
				sum=sum+empr;
				System.out.println("empRating:"+empr);
			}
			
			System.out.println("sum is:"+sum);
			newRating=(sum+newBooking.getRating().getRating())/(empRating.size()+1);
			System.out.println(newRating);
			employeeDataRepository.updateEmployeeRating(newRating, existingBooking.get().getEmployee().getEmployeeData().getId());
			
			entityManager.merge(newBooking);
			
			globalResponseData =new GlobalResponseData(true, 201, "success",newBooking);
			return new ResponseEntity<>(globalResponseData, HttpStatus.CREATED);
		}
		else {
			
			globalResponseData = new GlobalResponseData(false, 404, "Failure:Result Not Found");
			return new ResponseEntity<>(globalResponseData,HttpStatus.NOT_FOUND);
		}
	}
	
}
