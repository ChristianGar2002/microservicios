package com.carro_micro.repositorio;

import com.carro_micro.entidades.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarroRepositorio extends JpaRepository<Carro, Integer> {

    List<Carro> findByUsuarioId(int usuarioId);

}
