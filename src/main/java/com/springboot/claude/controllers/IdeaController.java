package com.springboot.claude.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.claude.models.Idea;
import com.springboot.claude.models.Like;
import com.springboot.claude.models.User;
import com.springboot.claude.services.LikeIdeaService;
import com.springboot.claude.services.UserService;

@Controller
public class IdeaController {
	private final UserService userService;
	
	private final LikeIdeaService likeIdeaService;
	
	public IdeaController(UserService userService, LikeIdeaService likeIdeaService) {
		this.userService = userService;
		this.likeIdeaService = likeIdeaService;
	}
	
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session, Model model) {
		Long uID = (Long) session.getAttribute("user_id");
		User user = userService.findUserById(uID);
		Iterable<Idea> ideas = likeIdeaService.allIdeas();
		model.addAttribute("ideas", ideas);
		model.addAttribute("user", user);
		return "dashboard.jsp";
	}
	@GetMapping("/idea/new")
	public String createIdea(HttpSession session, @ModelAttribute("idea") Idea idea, RedirectAttributes redirect) {
		if(session.getAttribute("user_id") != null) {
			return "createIdea.jsp";
		} else {
			redirect.addFlashAttribute("loginError","You must be logged into to use this site");
			return "redirect:/";
		}
	}
	
	@PostMapping(value="/ideas/newIdea")
	public String createIdea(@Valid @ModelAttribute("idea") Idea idea, BindingResult result, HttpSession session, Model model, RedirectAttributes redirect) {
		
		Long userId = (Long)session.getAttribute("user_id");
		if(result.hasErrors()) {
			redirect.addFlashAttribute("errors",result.getAllErrors());
			return "redirect:/idea/new";
		} else {
			String userName = userService.findUserById(userId).getName();
			idea.setCreator(userName);
			idea.setLikes(0);
			likeIdeaService.createIdea(idea);
			return "redirect:/dashboard";
		}
		
	}
	
	@GetMapping("/idea/{idea_id}")
	public String viewIdea(@PathVariable("idea_id") Long idea_id, Model model, HttpSession session) {
		Idea idea = likeIdeaService.getIdeaById(idea_id);
		Long userId = (Long) session.getAttribute("user_id");
		User user = userService.findUserById(userId);
		
		model.addAttribute("idea",idea);
		model.addAttribute("user",user);
		
		return "showIdea.jsp";
		
	}
	
	@GetMapping("/idea/edit/{idea_id}")
	public String editIdea(@PathVariable("idea_id") Long id, Model model) {
		
		Idea idea = likeIdeaService.getIdeaById(id);
		model.addAttribute("idea",idea);
		return "edit.jsp";
	}
	
	@PostMapping(value="/idea/update/{idea_id}")
	public String updateIdea(@Valid @ModelAttribute("idea") Idea idea, @PathVariable("idea_id") Long id, BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			return "edit.jsp";
		} else {
			Long userId = (Long) session.getAttribute("user_id");
			String user = userService.findUserById(userId).getName();
			idea.setCreator(user);
			likeIdeaService.updateIdea(idea);
			return "redirect:/dashboard";
		}
	}
	
	@GetMapping("/idea/delete/{idea_id}")
	public String deleteIdea(@PathVariable("idea_id") Long idea_id, HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("user_id");
		Idea idea = likeIdeaService.getIdeaById(idea_id);
		if(userId == null) {
			return "redirect:/";
		}
		else if(idea == null || !idea.getCreator().equals(userId)) {
			return "redirect:/dashboard";
		}
		else {
			likeIdeaService.deleteIdeaById(idea_id);
			return "redirect:/dashboard";
		}
	}
	/*
	@GetMapping("/idea/like/{idea_id}")
	public String likeIdea(@PathVariable("idea_id") Long ideaId, @Valid Idea idea, BindingResult result, HttpSession session) {
		Long userId = (Long) session.getAttribute("user_id");
		Idea ideaToLike = likeIdeaService.getIdeaById(ideaId);
		User user = userService.findUserById(userId);
		Like like = new Like();
		like.setIdeas(ideaToLike);
		like.setUsers(user);
		likeIdeaService.likeIdea(ideaToLike, user);
		
		return "redirect:/dashboard";
	}
	
	@GetMapping("/idea/unlike/{idea_id}")
	public String unlikeIdea(@PathVariable("idea_id") Long ideaId, HttpSession session) {
		Idea ideaToUnlike = likeIdeaService.getIdeaById(ideaId);
		Long userId = (Long) session.getAttribute("user_id");
		User user = userService.findUserById(userId);
		List<User> ideasLikedByUser = ideaToUnlike.getUsers();
		ideasLikedByUser.remove(user);
		likeIdeaService.unlikeIdea(ideaId);
		return "redirect:/dashboard";
	}*/
}
