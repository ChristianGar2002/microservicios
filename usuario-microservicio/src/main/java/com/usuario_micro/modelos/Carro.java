package com.usuario_micro.modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Carro {

    private String marca;
    private String modelo;
    private int usuarioId;
}
