package com.springboot.claude.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.claude.models.User;
import com.springboot.claude.services.UserService;

@Controller
public class LoginAndRegistrationController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String index(@ModelAttribute("user") User user, @ModelAttribute("error") String error, Model model) {
		return "index.jsp";
	}
	
	@PostMapping("/register")
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session, Model model) {
		if(result.hasErrors()) return "redirect:/";
		user = this.userService.registerUser(user);
		session.setAttribute("userId", user.getId());
		return "redirect:/dashboard";
	}
	
	@PostMapping("/login")
	public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, RedirectAttributes redirect, HttpSession session) {
		if(this.userService.authenticateUser(email, password)) {
			User loggedInUser = this.userService.findByEmail(email);
			session.setAttribute("user_id", loggedInUser.getId());
			return "redirect:/dashboard";
		}
		else {
			redirect.addFlashAttribute("error","Invalid credentials. Please try again!");
			return "redirect:/";
		}
	}
}
