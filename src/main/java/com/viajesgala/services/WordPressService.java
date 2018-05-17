package com.viajesgala.services;

import java.util.List;

import org.htmlcleaner.HtmlCleaner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.viajesgala.wpjson.Post;

@Service
public class WordPressService {
	
	@Value("${wpjson.posts}")
	private String postsUrl;

	public List<Post> getPosts () {
	
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<List<Post>> posts = restTemplate.exchange(postsUrl,HttpMethod.GET,null,new ParameterizedTypeReference<List<Post>>(){});
		
		if (posts != null) {
			
			HtmlCleaner htmlCleaner = new HtmlCleaner();
			
			for (Post post: posts.getBody()) {
				
				post.setContent(htmlCleaner.clean(post.getContent()).getText().toString());
				
			}
			
		}
		
		return posts.getBody();
		
	}	
	
}
