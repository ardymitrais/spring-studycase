package com.rahadyan.studycase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rahadyan.studycase.model.Job;
import com.rahadyan.studycase.repository.JobRepository;

@Service("jobSerice")
public class JobService implements IJobService {
	private String response;
	
	@Autowired
	JobRepository jobRepository;
	
	@Override
	public Job findById(Integer id) {
		return jobRepository.findById(id).orElse(null);
	}

	@Override
	public List<Job> findAll() {
		List<Job> jobs = jobRepository.findAll();
		return jobs;
	}
	
	@Override
	public String save(Job job) {		
		Job res = jobRepository.save(job);
		
		if(res != null) {
			response = "success";			
		} else {
			response = "fail";
		}
		
		return response;
	}

	@Override
	public String delete(Integer id) {
		Job currentJob = findById(id);
		
		if(currentJob != null) {
			jobRepository.delete(currentJob);
			response = "success";
		} else {
			response = "fail";
		}
			
		return response;
	}
	

	
	
}
