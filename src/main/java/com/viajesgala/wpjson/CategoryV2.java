
package com.viajesgala.wpjson;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class CategoryV2 {
	
	@JsonIgnore
	private List<Category> categories;	

	public CategoryV2() {
		super();
		this.categories = new ArrayList<Category>();
	}

	@JsonAnySetter
    public void setCategory (String key, Object value)
    {
        /*System.out.println("variable key = '" + key + "'");
        System.out.println("value is of type = " + value.getClass());
        System.out.println("value toString = '" + value.toString() + "'");*/
        
        Category cat = new Category();
        cat.setName((String)((LinkedHashMap)value).get("name"));
        this.categories.add(cat);
        
    }

	public List<Category> getCategories() {
		return categories;
	}

}
