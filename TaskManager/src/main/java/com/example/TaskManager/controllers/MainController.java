package com.example.TaskManager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping(value = "/")
	public String Default() {
		return "index";
	}
	
	@GetMapping(value = "/index")
	public String Index() {
		return "redirect:/";
	}
}