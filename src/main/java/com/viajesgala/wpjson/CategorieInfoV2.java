
package com.viajesgala.wpjson;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "ID", "name", "slug", "description", "post_count", "feed_url", "parent", "meta" })
public class CategorieInfoV2 {

	@JsonProperty("ID")
	private Integer iD;
	@JsonProperty("name")
	private String name;
	@JsonProperty("slug")
	private String slug;
	@JsonProperty("description")
	private String description;
	@JsonProperty("post_count")
	private Integer postCount;
	@JsonProperty("feed_url")
	private String feedUrl;
	@JsonProperty("parent")
	private Integer parent;
	@JsonProperty("meta")
	private Meta meta;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("ID")
	public Integer getID() {
		return iD;
	}

	@JsonProperty("ID")
	public void setID(Integer iD) {
		this.iD = iD;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("slug")
	public String getSlug() {
		return slug;
	}

	@JsonProperty("slug")
	public void setSlug(String slug) {
		this.slug = slug;
	}

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	@JsonProperty("post_count")
	public Integer getPostCount() {
		return postCount;
	}

	@JsonProperty("post_count")
	public void setPostCount(Integer postCount) {
		this.postCount = postCount;
	}

	@JsonProperty("feed_url")
	public String getFeedUrl() {
		return feedUrl;
	}

	@JsonProperty("feed_url")
	public void setFeedUrl(String feedUrl) {
		this.feedUrl = feedUrl;
	}

	@JsonProperty("parent")
	public Integer getParent() {
		return parent;
	}

	@JsonProperty("parent")
	public void setParent(Integer parent) {
		this.parent = parent;
	}

	@JsonProperty("meta")
	public Meta getMeta() {
		return meta;
	}

	@JsonProperty("meta")
	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}