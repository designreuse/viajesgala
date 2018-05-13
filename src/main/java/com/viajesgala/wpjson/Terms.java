
package com.viajesgala.wpjson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Terms {

    private List<Category> category = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
