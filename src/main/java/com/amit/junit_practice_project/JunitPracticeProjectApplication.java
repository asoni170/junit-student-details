package com.amit.junit_practice_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.amit.controller", "com.amit.config", "com.amit.util", 
	"com.amit.dao.impl", "com.amit.service", "com.amit.exception", "com.amit.mapper"})
public class JunitPracticeProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(JunitPracticeProjectApplication.class, args);
	}

}
