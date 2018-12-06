package com.rahadyan.studycase.service;

import java.util.List;

import com.rahadyan.studycase.model.Apply;

public interface IApplyService {
	String save(Apply apply);
	
	List<Apply> findAppliesByJobId(Integer id);
}
