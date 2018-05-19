package com.viajesgala.services;

import java.util.ArrayList;
import java.util.List;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.HtmlNode;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.TagNodeVisitor;
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
				
				final List<String> imagesSrc = new ArrayList<String>();
				TagNode node = htmlCleaner.clean(post.getContent());
				
				node.traverse(new TagNodeVisitor() {
					public boolean visit(TagNode tagNode, HtmlNode htmlNode) {
				        if (htmlNode instanceof TagNode) {
				            TagNode tag = (TagNode) htmlNode;
				            String tagName = tag.getName();
				            if ("img".equals(tagName)) {
				                String src = tag.getAttributeByName("src");
				                if (src != null) {
				                	imagesSrc.add(src);
				                }
				            }
				        }
				        // tells visitor to continue traversing the DOM tree
				        return true;
				    }
				});
				
				post.setContent(htmlCleaner.clean(post.getContent()).getText().toString());
				post.setImagesSrc(imagesSrc);
				
			}
			
		}
		
		return posts.getBody();
		
	}	
	
}
