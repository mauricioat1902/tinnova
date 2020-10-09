package br.com.tinnova.exercicio1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Voto {
    private LocalDateTime dataHoraVoto;
    private int tipoVoto;
    private Eleitor eleitor;
}
