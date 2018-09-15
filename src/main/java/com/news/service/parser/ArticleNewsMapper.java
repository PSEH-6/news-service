package com.news.service.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.news.service.models.Articles;
import com.news.service.models.News;

@Component
public class ArticleNewsMapper {
	
	@Value("${des.word.limit}")
	private int descriptionWordLimit;
	
	private Set<String> excludedWords;
	
	@PostConstruct
	public void initialiseExcludedWordsList() {
		excludedWords = new HashSet<>();
		excludedWords.add("to");
		excludedWords.add("for");
		excludedWords.add("the");
		excludedWords.add("is");
		excludedWords.add("the");
		excludedWords.add("by");
		excludedWords.add("but");
		excludedWords.add("no");
		excludedWords.add("yes");
		excludedWords.add("from");
		excludedWords.add("his");
		excludedWords.add("her");
		excludedWords.add("he");
		excludedWords.add("she");
		excludedWords.add("in");
		excludedWords.add("and");
		excludedWords.add("over");
		excludedWords.add("again");
		excludedWords.add("map");
		excludedWords.add("side");
		excludedWords.add("could");
		excludedWords.add("would");
		excludedWords.add("its");
		excludedWords.add("routes");
		excludedWords.add("route");
		excludedWords.add("-");
		excludedWords.add("a");
		excludedWords.add("an");
	}

	public List<News> convertArticlesInNewsHeadlines(List<Articles> newsAPIResponse, String country, String category) {
		
		List<News> newsList = new ArrayList<>();
		for(Articles article : newsAPIResponse) {
			News news = new News();
			news.setCountry(country);
			news.setCategory(category);
			news.setTitle(article.getTitle());
			news.setSourceUrl(article.getTitle());
			news.setDescription(cropDescriptionToSpecifiedWordLimit(article.getDescription(), descriptionWordLimit));
			news.setFilterKeywords(parseFilterKeywords(article.getDescription()));
			newsList.add(news);
		}
		return newsList;
	}

	private List<String> parseFilterKeywords(String description) {
		List<String> keywords = new ArrayList<>();
		if(description == null) {
			return keywords;
		}
		String[] descriptionArray = description.split(" ");
		Map<String, Integer> wordRanking = new HashMap<>();
		for(String st : descriptionArray) {
			if(excludedWords.contains(st) || excludedWords.contains(StringUtils.capitalize(st)) || excludedWords.contains(st.toUpperCase())) {
				continue;
			}
			Integer fetched = wordRanking.get(st);
			if(fetched == null || fetched == 0) {
				wordRanking.put(st, 1);
			}else {
				wordRanking.put(st, fetched + 1);
			}
		}
		Set<Entry<String, Integer>> set = wordRanking.entrySet();
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
        Collections.sort( list, new Comparator<Map.Entry<String, Integer>>()
        {
            public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )
            {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );
        for(int i = 0; i < 2; i++){
        	Map.Entry<String, Integer> entry = list.get(i);
        	keywords.add(entry.getKey());
        }
		return keywords;
	}

	private String cropDescriptionToSpecifiedWordLimit(String description, int limit) {
		if(description == null) {
			return description;
		}
		String[] descriptionArray = description.split(" ");
		if(limit == 0) {
			//Default Word Limit
			limit = 100;
		}
		if(descriptionArray.length <= limit) {
			return description;
		}
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < limit; i++ ) {
			builder.append(descriptionArray[i]);
			builder.append(" ");
		}
		return builder.toString();
	}

}
