package com.springboot.claude.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.claude.models.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
	
	// retrieves all users from database
	List<User> findAll();
	
	User findByEmail(String email);
	
	Optional<User> findById(Long id);

	

	

	



	
}