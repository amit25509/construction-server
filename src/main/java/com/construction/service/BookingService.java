package com.construction.service;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.construction.models.Bookings;
import com.construction.models.Commissions;
import com.construction.models.User;
import com.construction.repository.BookingsRepository;
import com.construction.repository.CommissionsRepository;
import com.construction.repository.EmployeeDataRepository;
import com.construction.repository.RatingRepository;
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
	RatingRepository ratingRepository;

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
		GrantedAuthority userRoles = null;
		List<Bookings> bookings;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			username = currentUserName;
			Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();

			for (Iterator<? extends GrantedAuthority> it = roles.iterator(); it.hasNext();) {
				userRoles = it.next();
				System.out.println("=================================================" + userRoles);
			}
		}

		try {
			if (username == null) {
				globalResponseListData = new GlobalResponseListData(false, 401, "Failure: Authentication Failed");
				return new ResponseEntity<>(globalResponseListData, HttpStatus.UNAUTHORIZED);
			}
			if (userRoles.toString().equals("ROLE_EMPLOYEE")) {
				bookings = bookingRepository.findEmployeeBookingsByUsername(username);
				
			} else {
				bookings = bookingRepository.findUserBookingsByUsername(username);
			}

			if (bookings.isEmpty()) {
				globalResponseListData = new GlobalResponseListData(false, 204, "Failure:Result Not Found");
				return new ResponseEntity<>(globalResponseListData, HttpStatus.NO_CONTENT);
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

			int daysWorked = (int) ((addBooking.getBookingTo().getTime() - addBooking.getBookingFrom().getTime())
					/ (24 * 60 * 60 * 1000));
			String status = "Pending";

			String username = null;
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (!(authentication instanceof AnonymousAuthenticationToken)) {
				String currentUserName = authentication.getName();
				username = currentUserName;
			}

			if (userRepository.findByUsername(username).isPresent()) {
				User existingUser = userRepository.findByUsername(username).get();
				addBooking.getAddress().setUser(existingUser);
			}

			System.out.println(
					"-----------------------------------------------------------------------------------------------------");

			Bookings bookings = bookingRepository.save(new Bookings(addBooking.getBookingFrom(),
					addBooking.getBookingTo(), status, daysWorked, addBooking.getUser(), addBooking.getEmployee(),
					addBooking.getRating(), addBooking.getAddress(), addBooking.getService(),
					addBooking.getSubService(), addBooking.getDescription()));

			Optional<User> user = userRepository.findById(addBooking.getEmployee().getId());

			int totalCommissionAmount = daysWorked * user.get().getEmployeeData().getOccupation().getCommissionRate();
			commissionRepository.save(new Commissions(bookings, 0, 0, "pending", 0));

			globalResponseData = new GlobalResponseData(true, 200, "success", bookings);
			return new ResponseEntity<>(globalResponseData, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
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

//	=============================UPDATE BOOKING =================================

	@Transactional
	@Modifying
	public ResponseEntity<GlobalResponseData> updateBooking(Integer id, Bookings newBooking) {
		// TODO Auto-generated method stub

		Optional<Bookings> existingBooking = bookingRepository.findById(id);

		if (existingBooking.isPresent()) {
			double newRating;
			double sum = 0;

			List<Integer> empRating = bookingRepository.findEmployeeRating(existingBooking.get().getEmployee().getId());

			for (Integer empr : empRating) {
				sum = sum + empr;
				System.out.println("empRating:" + empr);
			}

			System.out.println("sum is:" + sum + newBooking.getEmployee().getName());
			newRating = (sum + newBooking.getRating().getRating()) / (empRating.size() + 1);
			System.out.println(newRating);
			employeeDataRepository.updateEmployeeRating(newRating,
					existingBooking.get().getEmployee().getEmployeeData().getId());

			entityManager.merge(newBooking);

			globalResponseData = new GlobalResponseData(true, 201, "success", newBooking);
			return new ResponseEntity<>(globalResponseData, HttpStatus.CREATED);
		} else {

			globalResponseData = new GlobalResponseData(false, 404, "Failure:Result Not Found");
			return new ResponseEntity<>(globalResponseData, HttpStatus.NOT_FOUND);
		}
	}

//	=============================UPDATE RATING =================================
	@Transactional
	public ResponseEntity<GlobalResponseData> updateRating(Integer id, Bookings bookingRating) {
		Optional<Bookings> existingBooking = bookingRepository.findById(id);
		
		
		if (existingBooking.isPresent()) {
			
			if(existingBooking.get().getRating()==null) {
				double newRating;
				double sum = 0;
				System.out.println("================================="+existingBooking.get().getEmployee().getEmployeeData().getRating());
				if (existingBooking.get().getEmployee().getEmployeeData().getRating() != null) {
					// To update new rating by fetching previous ratings
					System.out.println("---------------------------------------------------------"+existingBooking.get().getEmployee().getId());
					List<Integer> empRating = bookingRepository.findEmployeeRating(existingBooking.get().getEmployee().getId());
					for (Integer empr : empRating) {
						System.out.println("empRating:" + empr);
						sum = sum + empr;
					}
					newRating = (sum + bookingRating.getRating().getRating()) / (empRating.size() + 1);
				} else {
					newRating = bookingRating.getRating().getRating();
				}

				bookingRating.getRating().setEmployee(existingBooking.get().getEmployee());
				bookingRating.getRating().setUser(existingBooking.get().getUser());
				existingBooking.map(add -> {
						add.setRating(bookingRating.getRating());
						return bookingRepository.save(add);
					});

				employeeDataRepository.updateEmployeeRating(newRating,
						existingBooking.get().getEmployee().getEmployeeData().getId());
//				entityManager.merge(bookingRating);
				globalResponseData = new GlobalResponseData(true, 201, "success", bookingRating);
				return new ResponseEntity<>(globalResponseData, HttpStatus.CREATED);
			}
			else {
				globalResponseData = new GlobalResponseData(true, 404, "Already Rated!!", null);
				return new ResponseEntity<>(globalResponseData, HttpStatus.NOT_FOUND);
			}
				
			}
			 else {

			globalResponseData = new GlobalResponseData(false, 404, "Failure:Result Not Found");
			return new ResponseEntity<>(globalResponseData, HttpStatus.NOT_FOUND);
		}
	}

}
