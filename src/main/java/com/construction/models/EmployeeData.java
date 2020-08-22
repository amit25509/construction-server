package com.construction.models;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(	name = "employee_data")
public class EmployeeData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "experience")
	private String experience;
	
	@Column(name = "is_Verified")
	private boolean isVerified;
	
//	@Column(name = "occupation")
//	private Occu occupation;
		
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
	
	@Column(name= "per_day_charge")
	private Integer perDayCharge;
	
	@OneToOne(targetEntity = Occupation.class)
	@JoinColumn(name="occupation", referencedColumnName = "occupationId")
	private Occupation occupation;

	public EmployeeData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeData(String experience, boolean isVerified, Occupation occupation,
			boolean availability, Date jobStartDate, String aadharFront, String aadharBack, Double rating,Integer perDayCharge ) {
		super();
		this.experience = experience;
		this.isVerified = isVerified;
		this.occupation = occupation;
		this.availability = availability;
		this.jobStartDate = jobStartDate;
		this.aadharFront = aadharFront;
		this.aadharBack = aadharBack;
		this.rating=rating;
		this.perDayCharge=perDayCharge;
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

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	public Occupation getOccupation() {
		return occupation;
	}

	public void setOccupation(Occupation occupation) {
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

	public Integer getPerDayCharge() {
		return perDayCharge;
	}

	public void setPerDayCharge(Integer perDayCharge) {
		this.perDayCharge = perDayCharge;
	}
}