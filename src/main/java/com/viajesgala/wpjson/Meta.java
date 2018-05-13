
package com.viajesgala.wpjson;

import java.util.HashMap;
import java.util.Map;

public class Meta {

    private Links links;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
