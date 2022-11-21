package com.userserv.services;

import java.util.List;
import java.util.Optional;

import com.userserv.entities.User;
import com.userserv.exceptions.ResourceNotFoundException;

public interface UserService {

	public User saveUser(User user);
	public List<User> getAllUsers();
	public User getUser(String userId) throws ResourceNotFoundException;
}
