package com.tinnova.cadastroveiculos.repository;

import com.tinnova.cadastroveiculos.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    List<Veiculo> findByCreatedBetween(LocalDateTime dataInicial, LocalDateTime dataFinal);
}
