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
import org.springframework.web.bind.annotation.PostMapping;

import com.springboot.claude.models.Idea;
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
		idea.setUser(user);
		likeIdeaService.createIdea(idea);
		return "redirect:/dashboard";
	}
	
	@GetMapping("/idea/{idea_id}")
	public String viewIdea()
}
