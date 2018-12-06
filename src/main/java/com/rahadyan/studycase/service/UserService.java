package com.rahadyan.studycase.service;

import java.util.List;

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
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder; 
	
	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		Role userRole = roleRepository.findByRole("MEMBER");
		user.setRole(userRole);
		return userRepository.save(user);
	}

	@Override
	public List<User> findAllByRoleId(Integer role) {
		List<User> users = userRepository.findAllUserByRoleId(role);
		return users;
	}

	

}
