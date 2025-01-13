package com.amit.config;

import java.io.IOException;
import java.util.Properties;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

@Configuration
@PropertySource("classpath:StudentQuery.properties")
public class SqlQueryConfig {
	
	private final Properties properties;
	
	public SqlQueryConfig() throws IOException{
		Resource resource = new ClassPathResource("StudentQuery.properties");
		this.properties = PropertiesLoaderUtils.loadProperties(resource);
	}
	
	public String getQuery(String key) {
		return properties.getProperty(key);
	}

}
