package com.tinnova.cadastroveiculos.interfaces;

import com.tinnova.cadastroveiculos.dto.VeiculoDecadaDTO;
import com.tinnova.cadastroveiculos.dto.VeiculoEntradaDTO;
import com.tinnova.cadastroveiculos.dto.VeiculoFabricanteDTO;
import com.tinnova.cadastroveiculos.model.Veiculo;

import java.time.LocalDateTime;
import java.util.List;

public interface IVeiculoService {

    Veiculo save(Veiculo objeto);
    Veiculo findById(Long id);
    List<Veiculo> findAll();
    void deleteById(Long id);
    Veiculo partialUpdate(Veiculo veiculo, VeiculoEntradaDTO veiculoEntradaDTO);
    List<Veiculo> findByCreatedBetween(LocalDateTime now, LocalDateTime now1);

    List<VeiculoDecadaDTO> obterVeiculosPorDecadas();

    List<VeiculoFabricanteDTO> obterDistribuicaoPorFabricante();

}
