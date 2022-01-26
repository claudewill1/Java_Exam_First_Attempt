package com.springboot.claude.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.claude.models.Idea;
import com.springboot.claude.models.Like;
import com.springboot.claude.models.User;
import com.springboot.claude.repositories.IdeaRepo;
import com.springboot.claude.repositories.LikeRepo;
import com.springboot.claude.repositories.UserRepo;

@Service
public class LikeIdeaService {
	@Autowired
	private LikeRepo likeRepo;
	@Autowired
	private IdeaRepo ideaRepo;
	@Autowired
	private UserRepo userRepo;
	// create methods
	
	
	public Idea createIdea(Idea idea) {
		return this.ideaRepo.save(idea);
	}
	
	public Like createLike(Like like) {
		return this.likeRepo.save(like);
	}
	
	// READ Methods
	
	// retrieve all ideas
	public List<Idea> allIdeas(){
		return this.ideaRepo.findAll();
	}
	
	// retrieve user by id 
	public Idea getIdeaById(Long id) {
		return this.ideaRepo.findById(id).orElse(null);
	}
	
	// retrieves all ideas with likes
	public List<Like> allLikes(){
		return this.likeRepo.findAll();
	}
	
	public Like getLikeById(Long id) {
		return this.likeRepo.findById(id).orElse(null);
	}
	
	public List<Like> getIdeaLikesByLikeByAscOrder(Like like){
		return this.likeRepo.findByIdeaLikeOrderByLikesAsc(like);
	}
	
	
	
	// UPDATE methods
	
	public void editIdea(Like like) {
		likeRepo.save(like);
	}
	
	public void addLikeToIdea(User liker, Idea idea) {
		idea.getUsersAlreadyLike().add(liker);
	}
	
	// DELETE Methods
	public void deleteIdeaById(Long id) {
		this.ideaRepo.deleteById(id);
	}
	
	public void deleteLikeById(Long id) {
		this.likeRepo.deleteById(id);
	}
	
	
}
