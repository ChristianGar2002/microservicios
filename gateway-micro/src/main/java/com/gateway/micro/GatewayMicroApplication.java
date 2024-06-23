package com.gateway.micro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayMicroApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayMicroApplication.class, args);
	}

}
