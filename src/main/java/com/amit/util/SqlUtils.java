package com.amit.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amit.config.SqlQueryConfig;

@Component
public class SqlUtils {
	
	@Autowired
	private SqlQueryConfig config;

	public String getSql(String key) {
		return config.getQuery(key);
	}
}
