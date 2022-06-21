package com.springdata.elastic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SpringdataelasticApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringdataelasticApplication.class, args);
	}

}
