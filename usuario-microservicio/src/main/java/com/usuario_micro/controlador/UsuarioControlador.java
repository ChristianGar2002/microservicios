package com.usuario_micro.controlador;

import com.usuario_micro.entidades.Usuario;
import com.usuario_micro.modelos.Carro;
import com.usuario_micro.modelos.Moto;
import com.usuario_micro.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping
    public ResponseEntity<List<Usuario>> listar_Usuarios(){

        List<Usuario> usuarios = usuarioServicio.getAll();

        if(usuarios.isEmpty()){//Si esta vacia que retorne que no encotro nada

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(usuarios);//Decimos que ok y retonarmos los usuarios
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id") int id){

        Usuario usuario = usuarioServicio.getUsuarioById(id);

        if(usuario == null){

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario){

        Usuario nuevoUsuario = usuarioServicio.save(usuario);

        return ResponseEntity.ok(nuevoUsuario);
    }

    @GetMapping("/carros/{usuarioId}")
    public ResponseEntity<List<Carro>> listarCarros(@PathVariable("usuarioId") int id){

        Usuario usuario = usuarioServicio.getUsuarioById(id);

        if(usuario == null){

            return ResponseEntity.notFound().build();
        }

        List<Carro> carros = usuarioServicio.getCarros(id);

        return ResponseEntity.ok(carros);
    }

    @GetMapping("/motos/{usuarioId}")
    public ResponseEntity<List<Moto>> listarMotos(@PathVariable("usuarioId") int id){

        Usuario usuario = usuarioServicio.getUsuarioById(id);

        if(usuario == null){

            return ResponseEntity.notFound().build();
        }

        List<Moto> motos = usuarioServicio.getMotos(id);

        return ResponseEntity.ok(motos);
    }

    @PostMapping("/carro/{usuarioId}")
    public ResponseEntity<Carro> guardarCarro(@PathVariable("usuarioId") int usuarioId, @RequestBody Carro carro){

        Carro nuevoCarro = usuarioServicio.saveCarro(usuarioId, carro);

        return ResponseEntity.ok(nuevoCarro);
    }

    @PostMapping("/moto/{usuarioId}")
    public ResponseEntity<Moto> guardarMoto(@PathVariable("usuarioId") int usuarioId, @RequestBody Moto moto){

        Moto nuevaMoto = usuarioServicio.saveMoto(usuarioId, moto);

        return ResponseEntity.ok(nuevaMoto);
    }

    @GetMapping("/todos/{usuarioId}")
    public ResponseEntity<Map<String, Object>> listarTodosLosVehiculos(@PathVariable("usuarioId") int usuarioId){

        Map<String, Object> resultado = usuarioServicio.getUsuarioAndVehiculos(usuarioId);

        return ResponseEntity.ok(resultado);

    }
}
