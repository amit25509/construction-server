package com.construction.models;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(	name = "employee_data")
public class EmployeeData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "experience")
	private String experience;
	
	@Column(name = "commission_rate")
	private Integer commissionRate;
	
	@Column(name = "is_Verified")
	private boolean isVerified;
	
	@Column(name = "occupation")
	private String occupation;
		
	@Column(name = "availability")
	private boolean availability;
	
	@Column(name = "job_start_date",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date jobStartDate;
	
	@Column(name = "aadhar_front")
	private String aadharFront;
	
	@Column(name = "aadhar_back")
	private String aadharBack;
	
	@Column(name = "rating")
	private Double rating;

	public EmployeeData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeData(String experience, Integer commissionRate, boolean isVerified, String occupation,
			boolean availability, Date jobStartDate, String aadharFront, String aadharBack, Double rating) {
		super();
		this.experience = experience;
		this.commissionRate = commissionRate;
		this.isVerified = isVerified;
		this.occupation = occupation;
		this.availability = availability;
		this.jobStartDate = jobStartDate;
		this.aadharFront = aadharFront;
		this.aadharBack = aadharBack;
		this.rating=rating;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public Integer getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(Integer commissionRate) {
		this.commissionRate = commissionRate;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public Date getJobStartDate() {
		return jobStartDate;
	}

	public void setJobStartDate(Date jobStartDate) {
		this.jobStartDate = jobStartDate;
	}

	public String getAadharFront() {
		return aadharFront;
	}

	public void setAadharFront(String aadharFront) {
		this.aadharFront = aadharFront;
	}

	public String getAadharBack() {
		return aadharBack;
	}

	public void setAadharBack(String aadharBack) {
		this.aadharBack = aadharBack;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}
	
	
}