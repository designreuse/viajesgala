package com.viajesgala.wpjson;

import java.util.List;

public class Posts {
	
	private Integer found;
	List<PostV2> posts;
	
	public List<PostV2> getPosts() {
		return posts;
	}
	public void setPosts(List<PostV2> posts) {
		this.posts = posts;
	}	

}
