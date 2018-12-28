
package com.viajesgala.wpjson;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.LinkedHashMap;

public class CategoryV2 {
	
	@JsonIgnore
	private String name;

	@JsonAnySetter
    public void setCategory (String key, Object value)
    {
        System.out.println("variable key = '" + key + "'");
        System.out.println("value is of type = " + value.getClass());
        System.out.println("value toString = '" + value.toString() + "'");
        
        this.name = (String)((LinkedHashMap)value).get("name");
        
        System.out.println(name);
        
    }

}
