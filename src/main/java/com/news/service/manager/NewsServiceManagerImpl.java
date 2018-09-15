package com.news.service.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.service.SAL.NewsServiceSAL;
import com.news.service.models.Articles;
import com.news.service.models.News;
import com.news.service.parser.ArticleNewsMapper;

@Service
public class NewsServiceManagerImpl implements NewsServiceManager {
	
	@Autowired
	private NewsServiceSAL newsServiceSAL;
	@Autowired
	private ArticleNewsMapper articleNewsMapper;

	@Override
	public List<News> getHeadlines(String country, String category) {
		List<Articles> newsAPIResponse = newsServiceSAL.getHeadlines(country, category);
		return articleNewsMapper.convertArticlesInNewsHeadlines(newsAPIResponse, country, category);
	}

}
