package com.example.TaskManager.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String getLoginPage(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model) {
		String notice = null;
		if (error != null) notice = "You have entered an invalid username or password.";
		if (logout != null) notice = "You have been successfully logged out!";
		model.addAttribute("notice", (notice != null) ? notice : "");
		return "login";
	}

	@PostMapping("/login")
	public String postLoginPage() {
		return "login";
	}

	@GetMapping("/logout")
	public String getLogoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) new SecurityContextLogoutHandler().logout(request, response, auth);
		return "redirect:/login?logout=true";
	}
}