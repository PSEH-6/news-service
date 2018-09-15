package com.news.service.SAL;

import java.util.List;

import com.news.service.models.Articles;

public interface NewsServiceSAL {

	List<Articles> getHeadlines(String country, String category);

}
