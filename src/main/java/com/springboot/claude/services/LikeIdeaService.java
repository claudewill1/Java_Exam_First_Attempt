package com.springboot.claude.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;

import com.springboot.claude.repositories.IdeaRepo;
import com.springboot.claude.repositories.LikeRepo;

@Service
public class LikeIdeaService {
	@Autowired
	private LikeRepo likeRepo;
	@Autowired
	private IdeaRepo ideaRepo;
	// create methods
	
	
	public Idea createIdea(Idea idea) {
		return this.ideaRepo.save(idea);
	}
	
	// get list of liked ideas
	public List<Like> allLikes(){
		return this.likeRepo.findAll();
	}
	
	public Like getLikeById(Long id) {
		return this.likeRepo.findById(id).orElse(null);
	}
	
	public List<Like> getIdeaLikesByLikeByAscOrder(Like like){
		return this.likeRepo.findByIdeaLikeOrderByLikesAsc(like);
	}
	
	public Like create(Like like) {
		return this.likeRepo.save(like);
	}
	
	public void editShow(Like like) {
		likeRepo.save(like);
	}
	
	public void
	
	
}
