//La clase para conectar los microservicios es lo que permite la clase RestTemplate
package com.usuario_micro.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

//Con esta anotacion indica que esta clase va a registrar beans en IOSCONTEINER
@Configuration  //Indica que es una clase que fuarda beans
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(){

        return new RestTemplate();
    }
}
