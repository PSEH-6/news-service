package com.news.service.manager;

import java.util.List;

import com.news.service.models.News;

public interface NewsServiceManager {

	List<News> getHeadlines(String country, String category);

}
