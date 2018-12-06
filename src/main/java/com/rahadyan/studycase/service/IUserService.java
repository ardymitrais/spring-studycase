package com.rahadyan.studycase.service;

import java.util.List;

import com.rahadyan.studycase.model.User;

public interface IUserService {
	
	User findUserByEmail(String email);
	
	User save(User user);
	
	List<User> findAllByRoleId(Integer role);
}
