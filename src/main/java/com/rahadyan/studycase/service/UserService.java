package com.rahadyan.studycase.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rahadyan.studycase.model.Role;
import com.rahadyan.studycase.model.User;
import com.rahadyan.studycase.repository.RoleRepository;
import com.rahadyan.studycase.repository.UserRepository;

@Service("userService")
public class UserService implements IUserService {

	@Autowired
	private UserRepository useRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder; 
	
	@Override
	public User findUserByEmail(String email) {
		return useRepository.findByEmail(email);
	}

	@Override
	public User saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		Role userRole = roleRepository.findByRole("ADMIN");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		return useRepository.save(user);
	}

}
