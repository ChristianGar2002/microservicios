package com.usuario_micro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//Con esto habilitamos el cliente feign e indicamos que este es el cliente, con este podemos anadir a los demas microservicios
@EnableFeignClients
@SpringBootApplication
public class UsuarioMicroApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsuarioMicroApplication.class, args);
	}

}
