package com.usuario_micro.servicio;

import com.usuario_micro.entidades.Usuario;
import com.usuario_micro.feignclients.CarroFeignClient;
import com.usuario_micro.feignclients.MotoFeignClient;
import com.usuario_micro.modelos.Carro;
import com.usuario_micro.modelos.Moto;
import com.usuario_micro.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsuarioServicio {

    @Autowired
    private MotoFeignClient motoFeignClient;

    @Autowired
    private CarroFeignClient carroFeignClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UsuarioRepository usuarioRepository;

    //Este es el restTemplate

    public List<Carro> getCarros(int usuarioId){

        //Aqui hacemos una peticion a la api de carros para obtenerlos
        List<Carro> carros = restTemplate.getForObject("http://carro-micro/carro/usuario/" + usuarioId, List.class);
        return carros;
    }

    public List<Moto> getMotos(int usuarioId){

        //Aqui hacemos una peticion a la api de motos para obtenerlos
        List<Moto> motos = restTemplate.getForObject("http://moto-micro/moto/usuario/" + usuarioId, List.class);

        return motos;
    }

    //Este con fiegnclient
    public Carro saveCarro(int usuarioId, Carro carro){

        carro.setUsuarioId(usuarioId);
        Carro nuevoCarro = carroFeignClient.save(carro);
        return nuevoCarro;
    }

    public Moto saveMoto(int usuarioId, Moto moto){

        moto.setUsuarioId(usuarioId);
        Moto nuevaMoto = motoFeignClient.save(moto);

        return nuevaMoto;
    }

    public Map<String, Object> getUsuarioAndVehiculos(int usuarioId){
        Map<String, Object> resultado = new HashMap<>();

        //Buscamos el usario
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);

        if(usuario == null){

            resultado.put("mensaje", "El usuario no existe");
            return resultado;
        }

        //Se guarda el objeto usuario en el Map
        resultado.put("usuario", usuario);

        //Se obtiene los carros
        List<Carro> carros = carroFeignClient.getCarros(usuarioId);

        if(carros.isEmpty()){

            resultado.put("Carros", "El usuario no tiene carros");

        }else{
            resultado.put("Carros", carros);
        }

        List<Moto> motos = motoFeignClient.getMotos(usuarioId);

        if(motos.isEmpty()){

            resultado.put("Motos", "El usuario no tiene motos");

        }else{
            resultado.put("Motos", motos);
        }

        return resultado;
    }

    //Metodos comunes
    public List<Usuario> getAll(){

        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(int id){

        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario save(Usuario usuario){

        Usuario nuevoUsuario = usuarioRepository.save(usuario);

        return nuevoUsuario;
    }

}
