package com.usuario_micro.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.usuario_micro.modelos.Moto;

import java.util.List;

@FeignClient(name = "moto-micro", url = "http://localhost:8083/moto")
public interface MotoFeignClient {

    @PostMapping()
    public Moto save(@RequestBody Moto moto);

    @GetMapping("/usuario/{usuarioId}")
    public List<Moto> getMotos(@PathVariable ("usuarioId") int usuarioId);
}
