package br.com.tinnova.exercicio1.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Eleitor {
    private String nome;
    private String cpf;
    private String rg;
    private LocalDate dataNascimento;
}
