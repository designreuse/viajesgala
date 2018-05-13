package com.viajesgala.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.viajesgala.services.WordPressService;
import com.viajesgala.wpjson.Post;

@Controller
public class HomeController {
	
	@Autowired
	private WordPressService wordPressService;
	
	@GetMapping("")
	public String home(Model model) {
		
		List<Post> posts = wordPressService.getPosts();
		model.addAttribute("posts",posts);
		
		return "home";
	}
	
	@GetMapping("contacto")
	public String contacto() {
		return "contacto";
	}
	
}
