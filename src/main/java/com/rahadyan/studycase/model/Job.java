package com.rahadyan.studycase.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
@Table(name = "job")
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "job_id")
	private int id;

	@Column(name = "title")
	@NotEmpty(message = "Please provide your title")
	private String title;

	@Column(name = "company")
	@NotEmpty(message = "Please provide your company")
	private String company;

	@Column(name = "description", columnDefinition = "TEXT")
	@NotEmpty(message = "Please provide your description")
	private String description;

	@Column(name = "location")
	@NotEmpty(message = "Please provide your location")
	private String location;

	@Column(name = "industry")
	@NotNull(message= "Please provide your industry")
	private String industry;
	
	@Column(name = "salary")
	@NotNull(message= "Please provide your salary")
	private int salary;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private Type type;

	@OneToMany(
			mappedBy="job",
			fetch=FetchType.LAZY,
			cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@ToString.Exclude
	private List<Apply> applies; 
	
	public enum Type {
		FULLTIME, PARTTIME
	}
	
	
}
