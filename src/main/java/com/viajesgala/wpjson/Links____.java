
package com.viajesgala.wpjson;

import java.util.HashMap;
import java.util.Map;

public class Links____ {

    private String collection;
    private String self;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
