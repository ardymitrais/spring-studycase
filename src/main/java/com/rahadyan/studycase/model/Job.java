package com.rahadyan.studycase.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "job")
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	@Column(name = "salary")
	@NotNull(message= "Please provide your locationy")
	private int salary;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private Type type;

	public enum Type {
		FULLTIME, PARTTIME
	}
}
