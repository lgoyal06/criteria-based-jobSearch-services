package com.aon.search.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
/**
 * 
 * 
 * @author lalit goyal
 *
 *         TODO Unit Tests for Bussiness Logic and Java source code
 */
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}