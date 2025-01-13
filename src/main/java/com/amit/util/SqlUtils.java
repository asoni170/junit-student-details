package com.amit.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amit.config.SqlQueryConfig;

@Component
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class SqlUtils {
	
	private SqlQueryConfig config;

	public String getSql(String key) {
		return config.getQuery(key);
	}
}
