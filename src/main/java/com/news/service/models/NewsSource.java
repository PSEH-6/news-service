package com.news.service.models;

import java.io.Serializable;

public class NewsSource implements Serializable{

	private static final long serialVersionUID = -7893742976729042997L;
	
	private String id;
	private String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
