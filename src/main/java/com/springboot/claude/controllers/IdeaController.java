package com.springboot.claude.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.springboot.claude.models.Idea;
import com.springboot.claude.models.Like;
import com.springboot.claude.models.User;
import com.springboot.claude.services.LikeIdeaService;
import com.springboot.claude.services.UserService;

@Controller
public class IdeaController {
	@Autowired
	private UserService userService;
	@Autowired
	private LikeIdeaService likeIdeaService;
	
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session, Model model) {
		Long userId = (Long)session.getAttribute("user_id");
		User user = userService.findUserById(userId);
		model.addAttribute("user",user);
		List<Idea> ideas = likeIdeaService.allIdeas();
		model.addAttribute("ideas",ideas);
		return "dashboard.jsp";
	}
	
	@PostMapping(value="/ideas/newIdea")
	public String createIdea(@Valid @ModelAttribute("idea") Idea idea, BindingResult result, HttpSession session) {
		Long userId = (Long)session.getAttribute("user_id");
		User user = userService.findUserById(userId);
		if(result.hasErrors()) return "createIdea.jsp";
		idea.setCreator(user);
		likeIdeaService.createIdea(idea);
		return "redirect:/dashboard";
	}
	
	@GetMapping("/idea/{idea_id}")
	public String viewIdea(@PathVariable("idea_id") Long idea_id, Model model, HttpSession session) {
		Idea idea = likeIdeaService.getIdeaById(idea_id);
		Long userId = (Long) session.getAttribute("user_id");
		User user = userService.findUserById(userId);
		List<Like> likes = idea.getLikes();
		model.addAttribute("idea",idea);
		model.addAttribute("user",user);
		model.addAttribute("likes",likes);
		return "showIdea.jsp";
		
	}
	
	@GetMapping("/idea/edit/{idea_id}")
	public String editIdea(@PathVariable("idea_id") Long id, Model model, HttpSession session, @Valid @ModelAttribute("editIdea") Idea editIdea) {
		Long userId = (Long) session.getAttribute("user_id");
		Idea idea = likeIdeaService.getIdeaById(id);
		if(userId == null) {
			return "redirect:/";
		}
		else if(idea == null || !idea.getCreator().equals(userId)) {
			return "redirect:/dashboard";
		}
		else {
			User currentUser = this.userService.findUserById(userId);
			model.addAttribute("user",currentUser);
			model.addAttribute("idea",idea);
			return "edit.jsp";
			
		}
		
	}
	
	@PostMapping(value="/idea/update/{idea_id}")
	public String updateIdea(@Valid @ModelAttribute("idea") Idea idea, @PathVariable("idea_id") Long id, BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			return "redirect:/idea/edit/"+id;
		} else {
			Long userId = (Long) session.getAttribute("user_id");
			User user = userService.findUserById(userId);
			idea.setCreator(user);
			likeIdeaService.updateIdea(id,idea);
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
	
	@GetMapping("/idea/like/{idea_id}")
	public String likeIdea(@PathVariable("idea_id") Long ideaId, @Valid Idea idea, BindingResult result, HttpSession session) {
		Long userId = (Long) session.getAttribute("user_id");
		Idea ideaToLike = likeIdeaService.getIdeaById(ideaId);
		User user = userService.findUserById(userId);
		Like like = new Like();
		like.setIdeas(ideaToLike);
		like.setUsers(user);
		likeIdeaService.addLikeToIdea(user, ideaToLike);
		
		return "redirect:/dashboard";
	}
	
	@GetMapping("/idea/unlike/{idea_id}")
	public String unlikeIdea(@PathVariable("idea_id") Long ideaId, HttpSession session) {
		Idea ideaToUnlike = likeIdeaService.getIdeaById(ideaId);
		Long userId = (Long) session.getAttribute("user_id");
		User user = userService.findUserById(userId);
		List<User> ideasLikedByUser = ideaToUnlike.getUsersAlreadyLike();
		ideasLikedByUser.remove(user);
		likeIdeaService.deleteIdeaById(ideaId);
		return "redirect:/dashboard";
	}
}
