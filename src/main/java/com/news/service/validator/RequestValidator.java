package com.news.service.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class RequestValidator {

	public String validateCountryCategory(String country, String category) {
		if(StringUtils.isEmpty(country)) {
			return "Country can not be null or blank";
		}
		if(StringUtils.isEmpty(category)) {
			return "Category can not be null or blank";
		}
		if(country.equalsIgnoreCase("null")) {
			return "Country can not be null string";
		}
		if(country.equalsIgnoreCase("null")) {
			return "Category can not be null string";
		}
		return null;
	}

}
