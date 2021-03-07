package com.ironhack.edgeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.*;
import org.springframework.cloud.openfeign.*;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class EdgeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdgeServiceApplication.class, args);
	}

}
