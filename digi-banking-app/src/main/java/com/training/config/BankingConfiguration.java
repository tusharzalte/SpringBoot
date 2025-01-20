package com.training.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.training.utility.Message;

@Configuration
public class BankingConfiguration {

	@Bean(name="start")
	public Message start()
	{
		return new Message("batch start today");
	}
	
	@Bean(name="end")
	public Message end()
	{
		return new Message("batch end today");
	}
    
	@Bean(name="ofss")
	public Message date()
	{
		return new Message("todays date");
	}
}
