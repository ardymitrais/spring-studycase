package com.rahadyan.studycase.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int id;

	@Column(name = "email")
	@Email(message = "Please provide a valid email")
	@NotEmpty(message = "Please prove an email")
	private String email;

	@Column(name = "password")
	@Length(min = 5, message = "Your password must have at least 5 characters")
	@NotEmpty(message = "Please provide your password")
	private String password;

	@Column(name = "first_name")
	@NotEmpty(message = "Please provide your first_name")
	private String firstName;

	@Column(name = "last_name")
	@NotEmpty(message = "Please provide your last name")
	private String lastName;
	
	@Column(name = "education")
	@NotEmpty(message = "Please provide your education")
	private String education;
	
	@Column(name = "school")
	@NotEmpty(message = "Please provide your school")
	private String school;
	
	@Column(name = "address")
	@NotEmpty(message = "Please provide your address")
	private String address;

	@Column(name = "active")
	private int active;
	
	@OneToOne
	@JoinColumn(name = "role_id")
	private Role role;
	
	@OneToMany(
			mappedBy="user",
			fetch=FetchType.LAZY,
			cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@ToString.Exclude
	private List<Apply> applies; 

//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
//	private Set<Role> roles;
//	
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "user_job", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "job_id"))
//	private Set<Job> jobs;	
}
