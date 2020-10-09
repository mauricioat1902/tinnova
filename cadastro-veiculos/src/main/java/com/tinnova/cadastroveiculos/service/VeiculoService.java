package com.tinnova.cadastroveiculos.service;

import com.tinnova.cadastroveiculos.dto.VeiculoDecadaDTO;
import com.tinnova.cadastroveiculos.dto.VeiculoEntradaDTO;
import com.tinnova.cadastroveiculos.dto.VeiculoFabricanteDTO;
import com.tinnova.cadastroveiculos.interfaces.IVeiculoService;
import com.tinnova.cadastroveiculos.model.Veiculo;
import com.tinnova.cadastroveiculos.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VeiculoService implements IVeiculoService {

    @Autowired
    private VeiculoRepository repository;

    @Override
    public Veiculo save(Veiculo veiculo) {
        if (veiculo.getId() != null) {
            veiculo.setUpdated(LocalDateTime.now());
        } else {
            veiculo.setCreated(LocalDateTime.now());
        }

        return repository.save(veiculo);
    }

    @Override
    public Veiculo findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Veiculo> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Veiculo partialUpdate(Veiculo veiculo, VeiculoEntradaDTO veiculoEntradaDTO) {
        if (veiculoEntradaDTO.getVeiculo() != null) veiculo.setVeiculo(veiculo.getVeiculo());
        if (veiculoEntradaDTO.getMarca() != null) veiculo.setMarca(veiculo.getMarca());
        if (veiculoEntradaDTO.getAno() != null) veiculo.setAno(veiculo.getAno());
        if (veiculoEntradaDTO.getDescricao() != null) veiculo.setDescricao(veiculo.getDescricao());
        if (veiculoEntradaDTO.getVendido() != null) veiculo.setVendido(veiculo.getVendido());

        return save(veiculo);
    }

    @Override
    public List<Veiculo> findByCreatedBetween(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return repository.findByCreatedBetween(dataInicial, dataFinal);
    }

    @Override
    public List<VeiculoDecadaDTO> obterVeiculosPorDecadas() {
        return this.findAll().stream()
                .collect(Collectors.groupingBy(Veiculo::getDecada, Collectors.counting()))
                .entrySet()
                .stream()
                .map(item -> new VeiculoDecadaDTO(item.getKey(), item.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<VeiculoFabricanteDTO> obterDistribuicaoPorFabricante() {
        return this.findAll().stream()
                .collect(Collectors.groupingBy(Veiculo::getMarca, Collectors.counting()))
                .entrySet()
                .stream()
                .map(item -> new VeiculoFabricanteDTO(item.getKey(), item.getValue()))
                .collect(Collectors.toList());
    }
}
