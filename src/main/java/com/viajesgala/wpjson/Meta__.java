
package com.viajesgala.wpjson;

import java.util.HashMap;
import java.util.Map;

public class Meta__ {

    private Links__ links;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Links__ getLinks() {
        return links;
    }

    public void setLinks(Links__ links) {
        this.links = links;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
