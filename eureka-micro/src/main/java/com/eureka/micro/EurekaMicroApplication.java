package com.eureka.micro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer //Para que se ha un servidor que registra los microservicios clientes
public class EurekaMicroApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaMicroApplication.class, args);
	}

}
