package com.tinnova.cadastroveiculos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VeiculoDecadaDTO {
    private Integer decada;
    private Long numeroVeiculos;
}
