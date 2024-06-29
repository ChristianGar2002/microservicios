package com.usuario_micro.feignclients;

import com.usuario_micro.modelos.Carro;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Con esta anotacion indico que este sera un cliente feign, el nombre del microservicio, la url de ese microservicio, de aqui se podra acceder a los que son los metodos get y post
@FeignClient(name = "carro-micro")
public interface CarroFeignClient {

    @PostMapping
    public Carro save(@RequestBody Carro carro);

    @GetMapping("/usuario/{usuarioId}")
    public List<Carro> getCarros(@PathVariable("usuarioId") int usuarioId);
}
