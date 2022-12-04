package com.userserv.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.userserv.entities.Hotel;
import com.userserv.entities.Rating;
import com.userserv.entities.User;
import com.userserv.exceptions.ResourceNotFoundException;
import com.userserv.external.services.Hotelserv;
import com.userserv.repo.UserRepo;
import com.userserv.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private Hotelserv hotelserv;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

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
		
		//get user from db 
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with id "+userId+" not found"));
		
		//api call to fetch ratings given by the above user from ratingserv
		Rating[] ratingsByUser = restTemplate.getForObject("http://RATINGSERV/ratings/user/"+user.getUserId(), Rating[].class);
		logger.info("{}",ratingsByUser);
		List<Rating> ratings = Arrays.stream(ratingsByUser).toList();
		
		List<Rating> ratingList = ratings.stream().map(rating -> {
			
			//api call to hotelserv to get the hotel
			//ResponseEntity<Hotel> hotelEntity = restTemplate.getForEntity("http://HOTELSERV/hotels/"+rating.getHotelId(), Hotel.class);
			Hotel hotel = hotelserv.getHotel(rating.getHotelId());
			
			//set the hotel to rating
			rating.setHotel(hotel);
			
			//return the rating
			return rating;
		}).collect(Collectors.toList());
		
		user.setRatings(ratingList);
		
		return user;
	} 

}
