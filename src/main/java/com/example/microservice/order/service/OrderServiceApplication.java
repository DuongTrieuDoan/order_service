package com.example.microservice.order.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class OrderServiceApplication {

	public static void main(String[] args) {
        SpringApplication app = new SpringApplication(OrderServiceApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "9191"));
        app.run(args);
	}

}
