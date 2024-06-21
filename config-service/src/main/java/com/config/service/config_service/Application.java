package com.config.service.config_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer //Para que funcione como el servidor del repositorio github
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
