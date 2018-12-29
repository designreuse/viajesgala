package com.viajesgala.services;

import java.util.ArrayList;
import java.util.List;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.HtmlNode;
import org.htmlcleaner.SimpleHtmlSerializer;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.TagNodeVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.viajesgala.adapters.WPAdapter;
import com.viajesgala.wpjson.CategorieInfoV2;
import com.viajesgala.wpjson.Categories;
import com.viajesgala.wpjson.Post;
import com.viajesgala.wpjson.PostV2;
import com.viajesgala.wpjson.Posts;

@Service
public class WordPressService {
	
	@Value("${wpjson.posts}")
	private String postsUrl;
	
	@Value("${wp.rest.version}")
	private String restVersion;
	
	@Value("${wpjson.posts.V2}")
	private String postsUrlV2;
	
	@Value("${wpjson.categories.V2}")
	private String categoriesUrlV2;
	
	@Autowired
	private WPAdapter wpAdapter;

	public List<Post> getPosts () {
	
		RestTemplate restTemplate = new RestTemplate();
		
		List<Post> posts = null;
		
		if (restVersion.equals("2")) {			
			ResponseEntity<Posts> postsEntity = restTemplate.exchange(postsUrlV2,HttpMethod.GET,null,new ParameterizedTypeReference<Posts>(){});
			if (postsEntity != null) {
				List<PostV2> postsV2 = postsEntity.getBody().getPosts();
				posts = wpAdapter.postsToV1(postsV2);
			}	
		} else {		
			ResponseEntity<List<Post>> postsEntity = restTemplate.exchange(postsUrl,HttpMethod.GET,null,new ParameterizedTypeReference<List<Post>>(){});
			if (postsEntity != null) {
				posts = postsEntity.getBody();
			}			
		}
		
		if (posts != null) {
			
			HtmlCleaner htmlCleaner = new HtmlCleaner();
			
			for (Post post: posts) {
				
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
				                tag.removeFromTree();
				            }
				        }
				        // tells visitor to continue traversing the DOM tree
				        return true;
				    }
				});
				
				SimpleHtmlSerializer serializer = new SimpleHtmlSerializer(htmlCleaner.getProperties());
				post.setContent(serializer.getAsString(node));
				post.setImagesSrc(imagesSrc);
				
			}
			
		}
		
		return posts;
		
	}
	
	public List<CategorieInfoV2> getCategories () {
		
		RestTemplate restTemplate = new RestTemplate();
		
		List<CategorieInfoV2> categories = null;
		
		if (restVersion.equals("2")) {			
			ResponseEntity<Categories> categoriesEntity = restTemplate.exchange(categoriesUrlV2,HttpMethod.GET,null,new ParameterizedTypeReference<Categories>(){});
			if (categoriesEntity != null) {
				List<CategorieInfoV2> categoriesV2 = categoriesEntity.getBody().getCategories();
				categories = categoriesV2;				
			}	
		} 
		
		return categories;
		
	}	
	
}
