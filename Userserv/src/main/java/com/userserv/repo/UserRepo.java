package com.userserv.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userserv.entities.User;

public interface UserRepo extends JpaRepository<User, String> {

}
