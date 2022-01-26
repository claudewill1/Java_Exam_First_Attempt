package com.springboot.claude.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.claude.models.Like;

@Repository
public interface LikeRepo extends CrudRepository<Like, Long> {
	// retrieve list of liked ideas
	List<Like> findAll();
	
	// retrieves all liked ideas in ascending order
	List<Like> findByIdeaLikeOrderByLikesAsc(Like like);
}
