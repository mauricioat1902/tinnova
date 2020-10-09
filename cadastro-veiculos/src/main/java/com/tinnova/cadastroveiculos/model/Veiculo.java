package com.tinnova.cadastroveiculos.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "VEICULO")
public class Veiculo implements Serializable {
    private static final long serialVersionUID = 3176935189637861983L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String veiculo;
    @Column(nullable = false)
    private String marca;
    @Column(nullable = false)
    private Integer ano;
    @Column(length = 700)
    private String descricao;
    @Column(nullable = false)
    private Boolean vendido;
    @Column(nullable = false)
    private LocalDateTime created;
    private LocalDateTime updated;

    public Integer getDecada() {
        return this.ano - (this.ano % 10);
    }
}
