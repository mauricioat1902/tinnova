package com.tinnova.cadastroveiculos.dto;

import com.tinnova.cadastroveiculos.validation.ValidaMarca;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
/**
 * Classe de saida aos serviços de veículo
 */
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoEntradaDTO {

    private Long id;
    @NotBlank(message = "campo 'veiculo' é obrigatório")
    private String veiculo;
    @NotBlank(message = "campo 'marca' é obrigatório")
    @ValidaMarca
    private String marca;
    @NotNull(message = "campo 'ano' é obrigatório")
    private Integer ano;
    private String descricao;
    private Boolean vendido = false;
}
