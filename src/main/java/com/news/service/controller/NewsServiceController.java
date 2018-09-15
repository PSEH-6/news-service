package com.news.service.controller;

import java.security.InvalidParameterException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.news.service.manager.NewsServiceManager;
import com.news.service.models.News;
import com.news.service.models.NewsResponse;
import com.news.service.validator.RequestValidator;

@RestController
@RequestMapping("/news-api/v1")
public class NewsServiceController {
	
	@Autowired
	private NewsServiceManager manager;
	@Autowired
	private RequestValidator validator;
	
	
	@GetMapping("/headlines")
	public NewsResponse fetchNews(@RequestParam("country") String country, @RequestParam("category") String category) {
		String validateResponse = validator.validateCountryCategory(country, category);
		if(validateResponse != null) {
			throw new InvalidParameterException(validateResponse);
		}
		List<News> newsList = manager.getHeadlines(country, category);
		NewsResponse response = new NewsResponse();
		response.setData(newsList);
		return response;
	}

}
