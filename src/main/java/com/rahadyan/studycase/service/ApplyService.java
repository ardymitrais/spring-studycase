package com.rahadyan.studycase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rahadyan.studycase.model.Apply;
import com.rahadyan.studycase.model.Job;
import com.rahadyan.studycase.model.User;
import com.rahadyan.studycase.repository.ApplyRepository;
import com.rahadyan.studycase.repository.JobRepository;

@Service
public class ApplyService implements IApplyService {

	private String response;
	
	@Autowired
	JobRepository jobRepository;
	
	@Autowired
	ApplyRepository applyRepository;

	@Override
	public String save(Apply apply) {
		Apply res = applyRepository.save(apply);
		
		if(res != null) {
			response = "success";			
		} else {
			response = "fail";
		}
		
		return response;
	}

	@Override
	public List<Apply> findAppliesByJobId(Integer id) {
		Job job = jobRepository.findById(id).orElse(null);
		return job.getApplies();
	}

	

}
