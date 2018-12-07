package com.rahadyan.studycase.controller;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rahadyan.studycase.model.Apply;
import com.rahadyan.studycase.model.Job;
import com.rahadyan.studycase.model.User;
import com.rahadyan.studycase.service.ApplyService;
import com.rahadyan.studycase.service.JobService;
import com.rahadyan.studycase.service.UserService;


@Controller
public class HireController {

	@Autowired
	JobService jobService;
	
	@Autowired
    private UserService userService;
	
	@Autowired
	private ApplyService applyService;
	
	@GetMapping("/")
	public String hire(Model model) {
		List<Job> jobs = jobService.findAll();
		model.addAttribute("jobs", jobs);
		return "/public/home";
	}
	
	@GetMapping("/detail-job/{id}")
	public String detailJob(@PathVariable String id, Model model) {
		Job job = jobService.findById(Integer.parseInt(id));
		
		if(job == null) {
			return "redirect:/";
		}
		model.addAttribute("job", job);
		model.addAttribute("id", id);
		return "/public/detail";
	}
	
	@GetMapping("/apply/{id}")
	public String apply(@PathVariable String id, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        Job job = jobService.findById(Integer.parseInt(id));
        Apply apply = new Apply();
        apply.setJob(job);
        apply.setUser(user);
        apply.setCreatedAt(Instant.now());
        applyService.save(apply);
        return "redirect:/";
	}
	

}
