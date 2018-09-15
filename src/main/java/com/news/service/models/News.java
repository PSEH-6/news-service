package com.news.service.models;

import java.io.Serializable;
import java.util.List;

public class News implements Serializable{

	private static final long serialVersionUID = 3307195711753541155L;
	
	private String country;
	private String category;
	private String title;
	private List<String> filterKeywords;
	private String description;
	private String sourceUrl;
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getFilterKeywords() {
		return filterKeywords;
	}
	public void setFilterKeywords(List<String> filterKeywords) {
		this.filterKeywords = filterKeywords;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSourceUrl() {
		return sourceUrl;
	}
	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

}
