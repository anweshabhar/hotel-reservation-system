package com.example.guestprofileservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GuestProfileServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuestProfileServiceApplication.class, args);
	}

}
