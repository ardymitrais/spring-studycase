package com.rahadyan.studycase.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.rahadyan.studycase.model.Job;
import com.rahadyan.studycase.service.JobService;

@Controller
public class JobController {

	@Autowired
	JobService jobService;

	@GetMapping("/job")
	public String readJob(Model model) {
		List<Job> jobs = jobService.findAll();
		model.addAttribute("jobs", jobs);
		return "/admin/job/lists";
	}
	
	@GetMapping("/job/create")
	public String createJob(Model model) {
		Job job = new Job();
		model.addAttribute("job", job);
		return "/admin/job/create";
	}
	
	@PostMapping("/job/submit")
	public String storeJob(@Valid Job job, BindingResult binding, Model model) {
		
		if (!binding.hasErrors()) {
			String resp = jobService.save(job);
            if(resp.equalsIgnoreCase("success")) {
        		return "redirect:/job";
            }
        } 
		
		return "/admin/job/create";
	}
	
	@GetMapping("/job/edit/{id}")
	public String editJob(@PathVariable String id, Model model) {
		Job job = jobService.findById(Integer.parseInt(id));
		
		if(job == null) {
			return "redirect:/job";
		}
		
		model.addAttribute("job", job);
		return "/admin/job/edit";
	}
	
	@GetMapping("/job/delete/{id}")
	public String deleteJob(@PathVariable String id,Model model) {
		String result = jobService.delete(Integer.parseInt(id));
		
		if(result.equalsIgnoreCase("success")) {
			model.addAttribute("message", "Job has been deleted");
		} else {
			model.addAttribute("message", "Job failed to delete");
		}
		
		return "redirect:/job";
	}
}
