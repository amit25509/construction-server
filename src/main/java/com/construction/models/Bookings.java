package com.construction.models;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "bookings")
public class Bookings 
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOOKING_SEQ")
	@SequenceGenerator(name = "BOOKING_SEQ", sequenceName = "BOOKING_SEQ", allocationSize = 1 )
	private Integer bookingId;
	
	@Column(name = "bookingFrom",nullable=false)
	private Date bookingFrom;
	
	@Column(name = "bookingTo",nullable=false)
	private Date bookingTo;
	
	@Column(name="status")
	private String status;
	
	@Column(name="days_worked")
	private Integer daysWorked;
	
	@OneToOne(targetEntity = User.class)
	@JoinColumn(name="user", referencedColumnName = "Id")
	private User user;
	
	@OneToOne(targetEntity = User.class)
	@JoinColumn(name="employee", referencedColumnName = "Id")
	private User employee;
	
	@OneToOne(targetEntity = Rating.class, cascade = CascadeType.ALL)
	@JoinColumn(name="rating", referencedColumnName = "Id")
	private Rating rating;

	public Bookings() {
	}

	public Bookings(Date bookingFrom, Date bookingTo, String status, Integer daysWorked, User user,
			User employee,Rating rating) {
		super();
		this.bookingFrom = bookingFrom;
		this.bookingTo = bookingTo;
		this.status = status;
		this.daysWorked = daysWorked;
		this.user = user;
		this.employee = employee;
		this.rating=rating;
	}

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public Date getBookingFrom() {
		return bookingFrom;
	}

	public void setBookingFrom(Date bookingFrom) {
		this.bookingFrom = bookingFrom;
	}

	public Date getBookingTo() {
		return bookingTo;
	}

	public void setBookingTo(Date bookingTo) {
		this.bookingTo = bookingTo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getDaysWorked() {
		return daysWorked;
	}

	public void setDaysWorked(Integer daysWorked) {
		this.daysWorked = daysWorked;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getEmployee() {
		return employee;
	}

	public void setEmployee(User employee) {
		this.employee = employee;
	}
	public Rating getRating() {
		return rating;
	}
	public void setRating(Rating rating) {
		this.rating = rating;
	}
	
	
//	@OneToOne(mappedBy="addressId")
//	private Employees employees;	
//	@OneToOne(mappedBy="addressId")
//	private Users users;
}