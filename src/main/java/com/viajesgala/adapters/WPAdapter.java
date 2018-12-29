package com.viajesgala.adapters;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import com.viajesgala.wpjson.Post;
import com.viajesgala.wpjson.PostV2;
import com.viajesgala.wpjson.Terms;

@Component
public class WPAdapter {
	
	public Post postToV1 (PostV2 postV2) {
		
		Post post = new Post();
		
		if (postV2.getTerms() != null && postV2.getTerms().getCategory() != null && !CollectionUtils.isEmpty(postV2.getTerms().getCategory().getCategories())) {
			Terms terms = new Terms();
			terms.setCategory(postV2.getTerms().getCategory().getCategories());
			post.setTerms(terms);
		}
		
		post.setContent(postV2.getContent());
		post.setTitle(postV2.getTitle());
		post.setID(postV2.getID());
		
		return post;
		
	}
	
	public List<Post> postsToV1 (List<PostV2> postsV2) {
		
		return postsV2.stream().map(p -> postToV1(p)).collect(Collectors.toList());
		
	}

}
