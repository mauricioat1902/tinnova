package com.tinnova.cadastroveiculos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VeiculoFabricanteDTO {
    private String marca;
    private Long numeroVeiculos;
}
