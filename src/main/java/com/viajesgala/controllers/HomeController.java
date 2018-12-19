package com.viajesgala.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.viajesgala.services.WordPressService;
import com.viajesgala.utilidades.Utilidades;
import com.viajesgala.wpjson.Category;
import com.viajesgala.wpjson.Post;

@Controller
public class HomeController {
	
	@Autowired
	private WordPressService wordPressService;
	
	private List<Post> getPosts (HttpSession session) {
		List<Post> posts = (List<Post>)session.getAttribute("posts");
		if (CollectionUtils.isEmpty(posts)) {
			posts = wordPressService.getPosts();
			session.setAttribute("posts",posts);
		}		
		return posts;
	}
	
	private List<String> getCategories (HttpSession session) {
		List<String> categories = (List<String>)session.getAttribute("categories");
		if (CollectionUtils.isEmpty(categories)) {
			List<Post> posts = getPosts(session);
			Set<String> categoriesSet = new HashSet<>();
			if (!CollectionUtils.isEmpty(posts)) {
				for (Post post: posts) {
					for (Category category: post.getTerms().getCategory()) {
						categoriesSet.add(Utilidades.initCap(category.getName()));
					}
				}
				categories = new ArrayList<String>(categoriesSet);
				session.setAttribute("categories",categories);
			}
		}		
		return categories;
	}
	
	@GetMapping("")
	public String home(Model model, HttpSession session) {		
		List<Post> posts = getPosts(session);
		List<String> categories = getCategories(session);
		
		model.addAttribute("posts",posts);
		model.addAttribute("categories",categories);
		
		return "home";
	}
	
	@GetMapping("/posts/{category}")
	public String posts(Model model,@PathVariable String category, HttpSession session) {
		List<Post> posts = getPosts(session);
		List<Post> postsFiltered = new ArrayList<Post>();
		
		if (posts != null) {			
			for (Post post:posts) {				
				List<Category> categories = post.getTerms().getCategory().stream().filter(c->c.getName().toUpperCase().equals(category.toUpperCase())).collect(Collectors.toList());
				if (categories!=null && categories.size()>0) {
					postsFiltered.add(post);
				}
				
			}
		}
		
		List<String> categories = getCategories(session);
		
		model.addAttribute("posts",postsFiltered);
		model.addAttribute("category",category);
		model.addAttribute("categories",categories);
		
		return "categoria";
		
	}
	
	@GetMapping("/post/{category}/{ID}")
	public String post(Model model, @PathVariable String category, @PathVariable String ID, HttpSession session) {
		List<Post> posts = getPosts(session);
		List<String> categories = getCategories(session);
		Post post = posts.stream().filter(p -> p.getID() == Integer.parseInt(ID)).collect(Collectors.toList()).get(0);
		model.addAttribute("post",post);
		model.addAttribute("categories",categories);
		model.addAttribute("category",category);
		return "post";		
	}
	
	@GetMapping("contacto")
	public String contacto(Model model, HttpSession session) {
		List<String> categories = getCategories(session);
		model.addAttribute("categories",categories);
		return "contacto";
	}
	
}
