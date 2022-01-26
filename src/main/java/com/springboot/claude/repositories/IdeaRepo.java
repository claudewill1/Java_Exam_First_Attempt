package com.springboot.claude.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.claude.models.Idea;

@Repository
public interface IdeaRepo extends CrudRepository<Idea, Long> {
	// retrieve list of ideas
	List<Idea> findAll();
}
