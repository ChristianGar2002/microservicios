package com.moto.micro.controladores;

import com.moto.micro.entidades.Moto;
import com.moto.micro.servicios.MotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moto")
public class MotoController {

    @Autowired
    private MotoService motoService;

    @GetMapping
    public ResponseEntity<List<Moto>> listar_Motos(){

        List<Moto> motos = motoService.getAll();

        if(motos.isEmpty()){//Si esta vacia que retorne que no encotro nada

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(motos);//Decimos que ok y retonarmos los usuarios
    }

    @GetMapping("/{id}")
    public ResponseEntity<Moto> obtenerMoto(@PathVariable("id") int id){

        Moto moto = motoService.getMotoById(id);

        if(moto == null){

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(moto);
    }

    @PostMapping
    public ResponseEntity<Moto> guardarMoto(@RequestBody Moto moto){

        Moto nuevaMoto = motoService.save(moto);

        return ResponseEntity.ok(nuevaMoto);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Moto>> listarMotosPorUsuarioId(@PathVariable("usuarioId") int id){

        List<Moto> motos = motoService.byUsuarioId(id);

        if(motos.isEmpty()){

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(motos);

    }

}
