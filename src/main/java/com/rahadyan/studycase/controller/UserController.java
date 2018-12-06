package com.rahadyan.studycase.controller;

import java.util.List;

import javax.validation.Valid;

import com.rahadyan.studycase.model.Job;
import com.rahadyan.studycase.model.Role;
import com.rahadyan.studycase.model.User;
import com.rahadyan.studycase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value= "/login", method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
    
    @GetMapping("/registration")
	public String registration(Model model) {
    	User user = new User();
		model.addAttribute("user", user);
		return "/public/registration";
	}
    
    @PostMapping("/registration")
	public String createMember(@Valid User user, BindingResult bindingResult, Model model) {
    	User userExists = userService.findUserByEmail(user.getEmail());
    	 if (userExists != null) {
             bindingResult
                     .rejectValue("email", "error.user",
                             "There is already a user registered with the email provided");
         }
         if (bindingResult.hasErrors()) {
        	 return "redirect:/registration";
         } else {
             userService.save(user);
             model.addAttribute("successMessage", "User has been registered successfully");
             model.addAttribute("user", new User());
             return "/public/registration";
         }
		
	}

    @RequestMapping(value="/dashboard", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getFirstName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }

    @GetMapping("/jobseeker")
	public String getALlJobseeker(Model model) {
		List<User> users = userService.findAllByRoleId(2);
		model.addAttribute("users", users);
		return "/admin/jobseeker/lists";
	}
}