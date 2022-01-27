package com.springboot.claude.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springboot.claude.models.Idea;
import com.springboot.claude.repositories.IdeaRepo;

@Service
public class LikeIdeaService {
	
	private final IdeaRepo ideaRepo;
	
	
	public LikeIdeaService(IdeaRepo ideaRepo) {
		
		this.ideaRepo = ideaRepo;
		
	}
	
	
	public Idea createIdea(Idea idea) {
		
		return ideaRepo.save(idea);
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
	
	
	
	
	
	
	
	
	
	// UPDATE methods
	
	public Idea updateIdea(Idea ideaUpdate) {
		Optional<Idea> optionalIdea = ideaRepo.findById(ideaUpdate.getId());
		if(optionalIdea.isPresent()) {
			Idea idea = optionalIdea.get();
			idea.setTitle(ideaUpdate.getTitle());
			idea.setCreator(ideaUpdate.getCreator());
			idea.setLikes(ideaUpdate.getLikes());
			idea.setUsers(ideaUpdate.getUsers());
			return this.ideaRepo.save(idea);
		} else {
			return null;
		}
	}
	/*
	public void likeIdea(Idea idea, User u) {
		
		int liker = idea.getLikes();
		
		idea.setLikes(liker);
		ideaRepo.save(idea);
	} 
	
	public void unLikeIdea(Long ideaId) {
		Idea idea = this.ideaRepo.findById(ideaId).orElse(null);
		int liker = idea.getLikes();
		liker--;
		idea.setLikes(liker);
		ideaRepo.save(idea);
	}
	*/
	// DELETE Methods
	public void deleteIdeaById(Long id) {
		this.ideaRepo.deleteById(id);
	}
	
	
	
	
}
