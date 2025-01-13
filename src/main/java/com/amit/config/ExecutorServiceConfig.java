package com.amit.config;

import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;

@Configuration
public class ExecutorServiceConfig {
	
	@Value("${application.thread.count}")
	private Integer threadPoolCount;
	
	@Bean("asyncTaskExecutor")
	public AsyncTaskExecutor plateformThreadExecutor() {
		var adapter = new TaskExecutorAdapter(Executors.newFixedThreadPool(threadPoolCount));
		return adapter;
	}

}
