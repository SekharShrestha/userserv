package com.userserv.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.userserv.entities.Rating;

@FeignClient(name = "RATINGSERV")
public interface Ratingserv {
		
	@PostMapping("/ratings")
	public Rating createRating(Rating values);
	
//	@PutMapping("/ratings/{ratingId}")
//	public Rating updateRating(@PathVariable String ratingId, Rating rating);

}
