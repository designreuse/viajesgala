
package com.viajesgala.wpjson;

import java.util.HashMap;
import java.util.Map;

public class Meta____ {

    private Links____ links;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Links____ getLinks() {
        return links;
    }

    public void setLinks(Links____ links) {
        this.links = links;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
