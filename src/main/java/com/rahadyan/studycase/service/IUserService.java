package com.rahadyan.studycase.service;

import com.rahadyan.studycase.model.User;

public interface IUserService {
	User findUserByEmail(String email);
	User saveUser(User user);
}
