package com.tinnova.cadastroveiculos.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tinnova.cadastroveiculos.validation.ValidaMarca;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
/**
 * Classe de entrada aos serviços de veículo
 */
public class VeiculoSaidaDTO {

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
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime created;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime updated;
}
