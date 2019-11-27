package com.viajesgala.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.viajesgala.mail.EmailServiceImpl;
import com.viajesgala.services.RecaptchaService;
import com.viajesgala.services.WordPressService;
import com.viajesgala.utilidades.Utilidades;
import com.viajesgala.wpjson.CategorieInfoV2;
import com.viajesgala.wpjson.Category;
import com.viajesgala.wpjson.Post;

@Controller
public class HomeController {
	
	@Value("${spring.mail.username}")
    private String springMailUsername;
	
	@Value("${asunto.mail.formulario}")
    private String asuntoMailFormulario;
	
	@Autowired
	private WordPressService wordPressService;
	
	@Autowired
	private EmailServiceImpl emailService;
	
	@Autowired
	private RecaptchaService captchaService;
	
	@Autowired
    private CacheManager cacheManager;
	
	private List<Post> getPosts () {
		List<Post> posts = wordPressService.getPosts();
		return posts;
	}
	
	private List<String> getCategories () {
		
		List<String> categories = null;
		
		List<Post> posts = getPosts();
		Set<String> categoriesSet = new HashSet<>();
		if (!CollectionUtils.isEmpty(posts)) {
			for (Post post: posts) {
				for (Category category: post.getTerms().getCategory()) {
					categoriesSet.add(Utilidades.initCap(category.getName()));
				}
			}
			categories = new ArrayList<String>(categoriesSet);
		}
		return categories;
		
	}
	
	private List<CategorieInfoV2> getCategoriesInfo () {
		List<CategorieInfoV2> categories = wordPressService.getCategoriesInfo();
		return categories;
	}
	
	private String descripcionCategoria(List<CategorieInfoV2> categories, String categoria) {
		String descripcionCategoria = "";
		//
		if(categories!=null) {
			//		
			Optional<CategorieInfoV2> matchingObject = categories.stream().
				    filter(p -> p.getName().equals(categoria)).
				    findFirst();
			//
			if(matchingObject.isPresent()) {
				descripcionCategoria = matchingObject.get().getDescription(); 
			}
		}
		//
		return descripcionCategoria;
	}
	
	@GetMapping("")
	public String home(Model model) {		
		List<Post> posts = getPosts();
		List<String> categories = getCategories();
		
		model.addAttribute("posts",posts);
		model.addAttribute("categories",categories);
		
		return "home";
	}
	
	@GetMapping("/posts/{category}")
	public String posts(Model model,@PathVariable String category) {
		List<Post> posts = getPosts();
		List<Post> postsFiltered = new ArrayList<Post>();
		
		if (posts != null) {			
			for (Post post:posts) {				
				List<Category> categories = post.getTerms().getCategory().stream().filter(c->c.getName().toUpperCase().equals(category.toUpperCase())).collect(Collectors.toList());
				if (categories!=null && categories.size()>0) {
					postsFiltered.add(post);
				}
				
			}
		}
		
		List<String> categories = getCategories();
		
		model.addAttribute("posts",postsFiltered);
		model.addAttribute("category",category);
		model.addAttribute("descripcionCategoria", descripcionCategoria(getCategoriesInfo(),category));
		model.addAttribute("categories",categories);
		
		return "categoria";
		
	}
	
	@GetMapping("/post/{category}/{ID}")
	public String post(Model model, @PathVariable String category, @PathVariable String ID) {
		List<Post> posts = getPosts();
		List<String> categories = getCategories();
		Post post = posts.stream().filter(p -> p.getID() == Integer.parseInt(ID)).collect(Collectors.toList()).get(0);
		model.addAttribute("post",post);
		model.addAttribute("categories",categories);
		model.addAttribute("category",category);
		model.addAttribute("descripcionCategoria", descripcionCategoria(getCategoriesInfo(),category));
		return "post";		
	}
	
	@GetMapping("contacto")
	public String contacto(Model model, HttpSession session) {
		List<String> categories = getCategories();
		model.addAttribute("categories",categories);
		return "contacto";
	}
	
	@PostMapping("mail")
	@ResponseBody
	public String mail(Model model,
					   @RequestParam("inputName") String name,
			           @RequestParam("inputEmail") String email,
			           @RequestParam("inputMessage") String message,
			           @RequestParam(name="g-recaptcha-response") String recaptchaResponse,
			           HttpServletRequest request
			          ) {
		
		String ip = request.getRemoteAddr();
		System.out.println("mail ==> ip: "+ip);
		String captchaVerifyMessage = captchaService.verifyRecaptcha(ip, recaptchaResponse);
		System.out.println("mail ==> captchaVerifyMessage: "+captchaVerifyMessage); 
		if ( StringUtils.isNotEmpty(captchaVerifyMessage)) {
			Map<String, Object> response = new HashMap<>();
			response.put("message", captchaVerifyMessage);
			return "KO";
		}
		
		String mensaje = "Nombre: "+ name + "\n";
		mensaje += "email: "+ email + "\n\n";
		mensaje += message;		
		emailService.sendSimpleMessage(springMailUsername, 
									   asuntoMailFormulario, 
									   mensaje
									  );   			
		return "OK";				
	}
	
	@GetMapping("/posts/clear")
	@ResponseBody
	public String clearCache () {
		cacheManager.getCacheNames().parallelStream().forEach(name -> cacheManager.getCache(name).clear());
		return "clear ok";		
	}
	
}
