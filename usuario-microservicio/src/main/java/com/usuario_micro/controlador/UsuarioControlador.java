package com.usuario_micro.controlador;

import com.usuario_micro.entidades.Usuario;
import com.usuario_micro.modelos.Carro;
import com.usuario_micro.modelos.Moto;
import com.usuario_micro.servicio.UsuarioServicio;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
    //Cuando susceda un error al llamar a este microservicio se llama al metodo fallBackGetCarros para que le un mensaje devuelta
    //@CicuitBreaker sirve para evitar que se ciagan todos los microservicios, name="Al nombre que esta en bootstrap.yaml es la instancia con la que manejaremos la tolerancia de fallos, el fallback es el nombre del metodo que creare"
    @CircuitBreaker(name = "carrosCB", fallbackMethod = "fallBackGetCarros")
    @GetMapping("/carros/{usuarioId}")
    public ResponseEntity<List<Carro>> listarCarros(@PathVariable("usuarioId") int id){

        Usuario usuario = usuarioServicio.getUsuarioById(id);

        if(usuario == null){

            return ResponseEntity.notFound().build();
        }

        List<Carro> carros = usuarioServicio.getCarros(id);

        return ResponseEntity.ok(carros);
    }

    @CircuitBreaker(name = "motosCB", fallbackMethod = "fallBackGetMotos")
    @GetMapping("/motos/{usuarioId}")
    public ResponseEntity<List<Moto>> listarMotos(@PathVariable("usuarioId") int id){

        Usuario usuario = usuarioServicio.getUsuarioById(id);

        if(usuario == null){

            return ResponseEntity.notFound().build();
        }

        List<Moto> motos = usuarioServicio.getMotos(id);

        return ResponseEntity.ok(motos);
    }

    @CircuitBreaker(name = "carrosCB", fallbackMethod = "fallBackSaveCarro")
    @PostMapping("/carro/{usuarioId}")
    public ResponseEntity<Carro> guardarCarro(@PathVariable("usuarioId") int usuarioId, @RequestBody Carro carro){

        Carro nuevoCarro = usuarioServicio.saveCarro(usuarioId, carro);

        return ResponseEntity.ok(nuevoCarro);
    }

    @CircuitBreaker(name = "motosCB", fallbackMethod = "fallBackGetMoto")
    @PostMapping("/moto/{usuarioId}")
    public ResponseEntity<Moto> guardarMoto(@PathVariable("usuarioId") int usuarioId, @RequestBody Moto moto){

        Moto nuevaMoto = usuarioServicio.saveMoto(usuarioId, moto);

        return ResponseEntity.ok(nuevaMoto);
    }

    @CircuitBreaker(name = "todosCB", fallbackMethod = "fallBackGetTodos")
    @GetMapping("/todos/{usuarioId}")
    public ResponseEntity<Map<String, Object>> listarTodosLosVehiculos(@PathVariable("usuarioId") int usuarioId){

        Map<String, Object> resultado = usuarioServicio.getUsuarioAndVehiculos(usuarioId);

        return ResponseEntity.ok(resultado);

    }

    private ResponseEntity<List<Carro>> fallBackGetCarros(@PathVariable("usuarioId") int id, RuntimeException exception){//Si no se puede acceder al microservicio carro sucedaedara esto

        return new ResponseEntity("El usuario: " + id + " tiene los carros en el taller", HttpStatus.ACCEPTED);
    }

    private ResponseEntity<Carro> fallBackSaveCarro(@PathVariable("usuarioId") int id, @RequestBody Carro carro, RuntimeException exception){//Si no se puede acceder al microservicio carro sucedaedara esto

        return new ResponseEntity("El usuario: " + id + " no tiene dinero para los carros", HttpStatus.ACCEPTED);
    }

    private ResponseEntity<List<Moto>> fallBackGetMotos(@PathVariable("usuarioId") int id, RuntimeException exception){//Si no se puede acceder al microservicio carro sucedaedara esto

        return new ResponseEntity("El usuario: " + id + " tiene las motos en el taller", HttpStatus.ACCEPTED);
    }

    private ResponseEntity<Moto> fallBackSaveMoto(@PathVariable("usuarioId") int id, @RequestBody Moto moto, RuntimeException exception){//Si no se puede acceder al microservicio carro sucedaedara esto

        return new ResponseEntity("El usuario: " + id + " no tiene dinero para los carros", HttpStatus.ACCEPTED);
    }

    private ResponseEntity<Map<String, Object>> fallBackGetTodos(@PathVariable("usuarioId") int id, RuntimeException exception){//Si no se puede acceder al microservicio carro sucedaedara esto

        return new ResponseEntity("El usuario: " + id + " tiene los vehiculos en el taller", HttpStatus.ACCEPTED);
    }
}
