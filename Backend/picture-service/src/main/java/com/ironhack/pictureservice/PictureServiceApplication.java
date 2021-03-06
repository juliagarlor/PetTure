package com.ironhack.pictureservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.*;

@SpringBootApplication
@EnableEurekaClient
public class PictureServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PictureServiceApplication.class, args);
	}

}
