package com.construction.models;
import javax.persistence.*;

@Entity
@Table(name = "rating")
public class Rating {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADDRESS_SEQ")
	@SequenceGenerator(name = "ADDRESS_SEQ", sequenceName = "ADDRESS_SEQ", allocationSize = 1)
	private Integer id;
	
	private Integer rating;
	
	@OneToOne(targetEntity = User.class)
	@JoinColumn(name="user", referencedColumnName = "Id")
	private User user;
	
	@OneToOne(targetEntity = User.class)
	@JoinColumn(name="employee", referencedColumnName = "Id")
	private User employee;

	public Rating() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Rating(Integer rating, User user, User employee) {
		super();
		this.rating = rating;
		this.user = user;
		this.employee = employee;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
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
	
	
}