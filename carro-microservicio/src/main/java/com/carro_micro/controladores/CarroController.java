package com.carro_micro.controladores;

import com.carro_micro.entidades.Carro;
import com.carro_micro.servicio.CarroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carro")
public class CarroController {

    @Autowired
    private CarroServicio carroServicio;

    @GetMapping
    public ResponseEntity<List<Carro>> listar_Carros(){

        List<Carro> carros = carroServicio.getAll();

        if(carros.isEmpty()){//Si esta vacia que retorne que no encotro nada

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(carros);//Decimos que ok y retonarmos los usuarios
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> obtenerCarro(@PathVariable("id") int id){

        Carro carro = carroServicio.getCarroById(id);

        if(carro == null){

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(carro);
    }

    @PostMapping
    public ResponseEntity<Carro> guardarCarro(@RequestBody Carro carro){

        Carro nuevoCarro = carroServicio.save(carro);

        return ResponseEntity.ok(nuevoCarro);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Carro>> listarCarrosPorUsuarioId(@PathVariable("usuarioId") int id){

        List<Carro> carros = carroServicio.byUsuarioId(id);

        if(carros.isEmpty()){

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(carros);

    }

}
