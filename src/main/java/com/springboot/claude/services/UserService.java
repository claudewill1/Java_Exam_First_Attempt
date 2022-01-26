package com.springboot.claude.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;

import org.springframework.stereotype.Service;

import com.springboot.claude.models.*;
import com.springboot.claude.repositories.UserRepo;


@Service
public class UserService {
private final UserRepo userRepository;
	
	public UserService(UserRepo userRepository) {
		this.userRepository = userRepository;
	}
	
	public User registerUser(User user) {
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);
		return this.userRepository.save(user);		
	}
	
	// find user by email
	public User findByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}
	
	// find user by id
	public User findUserById(Long id) {
		return this.userRepository.findById(id).orElse(null);
		
	}
	
	// authenticate user
	public boolean authenticateUser(String email, String password) {
		// first find the user by email
		User user = this.userRepository.findByEmail(email);
		// if we can't find it by email, return false
		if(user==null) {
			return false;
		} else {
			// if the passwords match, return true, else, return false
			if(BCrypt.checkpw(password, user.getPassword())) {
				return true;
			} else {
				return false;
			}
		}
		
	}
}
