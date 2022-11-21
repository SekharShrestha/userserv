package com.userserv.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userserv.entities.User;
import com.userserv.exceptions.ResourceNotFoundException;
import com.userserv.repo.UserRepo;
import com.userserv.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public User saveUser(User user) {
		String randomId = UUID.randomUUID().toString();
		user.setUserId(randomId);
		return userRepo.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public User getUser(String userId) throws ResourceNotFoundException {
		return userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with id "+userId+" not found"));
	}

}
