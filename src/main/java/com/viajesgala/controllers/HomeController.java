package com.viajesgala.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("")
	public String home() {
		return "home";
	}
	
	@GetMapping("contacto")
	public String contacto() {
		return "contacto";
	}
	
}
