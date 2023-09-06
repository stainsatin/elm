package com.neusoft.elmboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@EnableCaching
@SpringBootApplication
public class ElmbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElmbootApplication.class, args);
	}

}
