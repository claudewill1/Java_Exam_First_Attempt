package com.springboot.claude.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import com.springboot.claude.validators.UserValidator;

@Controller
public class LoginAndRegistrationController {
	
	private final UserService userService;
	private final UserValidator userValidator;
	public LoginAndRegistrationController(UserService userService,UserValidator userValidator) {
		this.userService = userService;
		this.userValidator = userValidator;
	}
	
	@GetMapping("/")
	public String index(@ModelAttribute("user") User user, @ModelAttribute("error") String error, Model model, HttpSession session) {
		
		return "index.jsp";
	}
	
	
	
	@PostMapping("/register")
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session, Model model) {
		userValidator.validate(user, result);
		if(result.hasErrors()){
			
			return "index.jsp";
		} else {
			user = this.userService.registerUser(user);
			session.setAttribute("user_id", user.getId());
			return "redirect:/dashboard";
		}
		
	}
	
	@PostMapping("/login")
	public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, RedirectAttributes redirect, HttpSession session,Model model) {
		
		if(userService.authenticateUser(email, password)) {
			User user = userService.findByEmail(email);
			session.setAttribute("user_id", user.getId());
			return "redirect:/dashboard";
		} else
		{
			redirect.addFlashAttribute("LoginError", "Login Credentials Invalid");
			return "redirect:/";
		}
	}
	
	@GetMapping("/logout")
	public String logoutUser(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
