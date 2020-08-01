package com.construction.models;
import javax.persistence.*;

@Entity
@Table(name = "occupation")
public class Occupation {
	@Id
	private Integer occupationId;

	@Column(length = 20)
	private String occupationName;
	
	@Column
	private Integer commissionRate;

	public Occupation() {	}

	public Occupation(String occupationName, Integer commissionRate) {
		super();
		this.occupationName = occupationName;
		this.commissionRate = commissionRate;
	}

	public Integer getOccupationId() {
		return occupationId;
	}

	public void setOccupationId(Integer occupationId) {
		this.occupationId = occupationId;
	}

	public String getOccupationName() {
		return occupationName;
	}

	public void setOccupationName(String occupationName) {
		this.occupationName = occupationName;
	}

	public Integer getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(Integer commissionRate) {
		this.commissionRate = commissionRate;
	}

}