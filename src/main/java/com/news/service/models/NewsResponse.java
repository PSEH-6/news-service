package com.news.service.models;

import java.io.Serializable;
import java.util.Date;

public class NewsResponse implements Serializable{

	private static final long serialVersionUID = 6673810067165451419L;
	
	private Object data;
	private String responseStatus;
	private Date responseTime;
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}
	public Date getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}
	
}
