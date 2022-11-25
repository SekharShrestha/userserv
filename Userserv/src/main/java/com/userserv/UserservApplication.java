package com.userserv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserservApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserservApplication.class, args);
	}

}
