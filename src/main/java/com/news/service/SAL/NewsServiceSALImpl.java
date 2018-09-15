package com.news.service.SAL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.news.service.models.Articles;

@Repository
public class NewsServiceSALImpl implements NewsServiceSAL {
	
	@Value("${news.api.url}")
	private String newsAPIURL;
	
	@Value("${news.api.key}")
	private String newsAPIKey;

	@Override
	public List<Articles> getHeadlines(String country, String category) {
		
		List<Articles> articleList = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(newsAPIURL)
		        .queryParam("country", country)
		        .queryParam("category", category)
		        .queryParam("apiKey", newsAPIKey);

		HttpEntity<?> entity = new HttpEntity<>(headers);

		RestTemplate restTemplate = new RestTemplate(); 
		HttpEntity<String> response = restTemplate.exchange(
		        builder.toUriString(), 
		        HttpMethod.GET, 
		        entity, 
		        String.class);
		JSONObject responseJSON = new JSONObject(response.getBody());
		try {
			articleList = mapper.readValue(responseJSON.getJSONArray("articles").toString(), 
					TypeFactory.defaultInstance().constructCollectionType(List.class, Articles.class));
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
		return articleList;
	}
	
	

}
