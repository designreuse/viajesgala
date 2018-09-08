package com.viajesgala.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.viajesgala.services.WordPressService;
import com.viajesgala.wpjson.Category;
import com.viajesgala.wpjson.Post;

@Controller
public class HomeController {
	
	@Autowired
	private WordPressService wordPressService;
	
	@GetMapping("")
	public String home(Model model, HttpSession session) {
		
		List<Post> posts = wordPressService.getPosts();
		Set<String> categoriesSet = new HashSet<>();
		if (posts != null) {
			for (Post post: posts) {
				for (Category category: post.getTerms().getCategory()) {
					categoriesSet.add(category.getName());
				}
			}
		}
		List<String> categories = new ArrayList<String>(categoriesSet);
		model.addAttribute("posts",posts);
		model.addAttribute("categories",categories);
		session.setAttribute("categories",categories);
		
		return "home";
	}
	
	@GetMapping("/home_old")
	public String home_old(Model model, HttpSession session) {
		
		List<Post> posts = wordPressService.getPosts();
		Set<String> categoriesSet = new HashSet<>();
		if (posts != null) {
			for (Post post: posts) {
				for (Category category: post.getTerms().getCategory()) {
					categoriesSet.add(category.getName());
				}
			}
		}
		List<String> categories = new ArrayList<String>(categoriesSet);
		model.addAttribute("posts",posts);
		model.addAttribute("categories",categories);
		session.setAttribute("categories",categories);
		
		return "home_old";
	}
	
	@GetMapping("/posts/{category}")
	public String posts(Model model,@PathVariable String category, HttpSession session) {
		List<Post> posts = wordPressService.getPosts();
		List<Post> postsFiltered = new ArrayList<Post>();
		
		if (posts != null) {			
			for (Post post:posts) {				
				List<Category> categories = post.getTerms().getCategory().stream().filter(c->c.getName().equals(category)).collect(Collectors.toList());
				if (categories!=null && categories.size()>0) {
					postsFiltered.add(post);
				}
				
			}
		}
		
		model.addAttribute("posts",postsFiltered);
		model.addAttribute("categories",session.getAttribute("categories"));
		
		return "posts";
		
	}	
	
	@GetMapping("contacto")
	public String contacto(Model model, HttpSession session) {
		model.addAttribute("categories",session.getAttribute("categories"));
		return "contacto";
	}
	
}
