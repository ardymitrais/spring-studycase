package com.rahadyan.studycase.service;

import java.util.List;

import com.rahadyan.studycase.model.Job;

public interface IJobService {
	Job findById(Integer id);

	List<Job> findAll();
	
	String save(Job job);
	
	String delete(Integer id);
	
}
