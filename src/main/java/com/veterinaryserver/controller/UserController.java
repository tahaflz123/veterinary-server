package com.veterinaryserver.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.veterinaryserver.entity.user.User;
import com.veterinaryserver.model.request.LoginRequest;
import com.veterinaryserver.model.request.RegistrationRequest;
import com.veterinaryserver.model.request.UserUpdateRequest;
import com.veterinaryserver.model.response.UserAndAnimalsResponse;
import com.veterinaryserver.service.UserService;

@Controller
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	

	@GetMapping("/register")
	public String registerForm(Model model) {
		model.addAttribute("register", new RegistrationRequest());
		return "user/register";
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute RegistrationRequest request) {
		this.userService.register(request);
		return "redirect:/api/user/login";
	}
	
	@GetMapping("/login")
	public String loginForm(Model model) {
		model.addAttribute("login", new LoginRequest());
		return "user/login";
	}
	
	@GetMapping("/profile")
	public ModelAndView profile() {
		UserAndAnimalsResponse user = this.userService.userProfile();
		ModelAndView mav = new ModelAndView("user/userview");
		mav.addObject("user", user);
		return mav;
	}
	
	@GetMapping("/update")
	public String updateUserForm(@PathParam("id")Long id, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("updateuser", new UserUpdateRequest());
		return "user/updateuser";
	}
	
	@PostMapping("/update")
	public String updateUser(@PathParam("id")Long id, @ModelAttribute UserUpdateRequest request) {
		this.userService.updateUser(id, request);
		return "redirect:/api/user?id=" + id;
	}
	
	@GetMapping
	public ModelAndView findByIdWithAnimals(@PathParam("id") Long id) {
		UserAndAnimalsResponse user = this.userService.findUserAndUserAnimalsWithUserId(id);
		ModelAndView mav = new ModelAndView("user/userview");
		mav.addObject("user", user);
		return mav;
	}
	
	@GetMapping("/search")
	public ModelAndView searchUsers(@PathParam("q")String q) {
		List<User> users = this.userService.searchUser(q);
		ModelAndView mav = new ModelAndView("search/searchuser");
		mav.addObject("users", users);
		return mav;
	}
	
	
	
}
