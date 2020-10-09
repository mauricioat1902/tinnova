package com.tinnova.cadastroveiculos.controller;


import com.tinnova.cadastroveiculos.dto.VeiculoDecadaDTO;
import com.tinnova.cadastroveiculos.dto.VeiculoEntradaDTO;
import com.tinnova.cadastroveiculos.dto.VeiculoFabricanteDTO;
import com.tinnova.cadastroveiculos.dto.VeiculoSaidaDTO;
import com.tinnova.cadastroveiculos.interfaces.IVeiculoService;
import com.tinnova.cadastroveiculos.model.Veiculo;
import com.tinnova.cadastroveiculos.util.ObjectMapperUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private IVeiculoService service;

    @ApiOperation(value = "Salvar um veículo", response = VeiculoSaidaDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Veículo salvo com sucesso"),
            @ApiResponse(code = 401, message = "Não autorizado para visualizar"),
            @ApiResponse(code = 403, message = "É proibido acessar o recurso que você estava tentando acessar"),
            @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<VeiculoSaidaDTO> salvar(@RequestBody @Valid VeiculoEntradaDTO veiculoEntradaDTO) {
        Veiculo veiculo = service.save(ObjectMapperUtils.map(veiculoEntradaDTO, Veiculo.class));
        return ResponseEntity.created(URI.create("veiculo")).body(ObjectMapperUtils.map(veiculo, VeiculoSaidaDTO.class));
    }

    @ApiOperation(value = "Atualizar todos os dados de um veículo", response = VeiculoSaidaDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Veículo salvo com sucesso"),
            @ApiResponse(code = 204, message = "Não há dados cadastrados"),
            @ApiResponse(code = 401, message = "Não autorizado para visualizar"),
            @ApiResponse(code = 403, message = "É proibido acessar o recurso que você estava tentando acessar"),
            @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado")
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<VeiculoSaidaDTO> atualizar(@RequestBody @Valid VeiculoEntradaDTO veiculoEntradaDTO, @PathVariable Long id) {
        Veiculo veiculo = service.findById(id);
        if (veiculo == null)
            return ResponseEntity.noContent().build();

        veiculo.setVeiculo(veiculoEntradaDTO.getVeiculo());
        veiculo.setAno(veiculoEntradaDTO.getAno());
        veiculo.setDescricao(veiculoEntradaDTO.getDescricao());
        veiculo.setVendido(veiculoEntradaDTO.getVendido());
        veiculo.setMarca(veiculoEntradaDTO.getMarca());
        return ResponseEntity.ok(ObjectMapperUtils.map(service.save(veiculo), VeiculoSaidaDTO.class));

    }

    @ApiOperation(value = "Atualizar alguns dados de um veículo", response = VeiculoSaidaDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Veículo salvo com sucesso"),
            @ApiResponse(code = 204, message = "Não há dados cadastrados"),
            @ApiResponse(code = 401, message = "Não autorizado para visualizar"),
            @ApiResponse(code = 403, message = "É proibido acessar o recurso que você estava tentando acessar"),
            @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado")
    })
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<VeiculoSaidaDTO> atualizacaoParcial(@RequestBody VeiculoEntradaDTO veiculoEntradaDTO, @PathVariable Long id) {
        // Sem @Valid pois é atualização parcial, assim não faz validação dos campos nulos
        Veiculo veiculo = service.findById(id);
        if (veiculo == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(ObjectMapperUtils.map(service.partialUpdate(veiculo, veiculoEntradaDTO), VeiculoSaidaDTO.class));

    }

    @ApiOperation(value = "Buscar veículo pelo ID", response = VeiculoSaidaDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operação realizada com sucesso"),
            @ApiResponse(code = 204, message = "Não há dados cadastrados"),
            @ApiResponse(code = 401, message = "Não autorizado para visualizar"),
            @ApiResponse(code = 403, message = "É proibido acessar o recurso que você estava tentando acessar"),
            @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado")
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<VeiculoSaidaDTO> buscarPorId(@PathVariable Long id) {
        Veiculo veiculo = service.findById(id);
        return veiculo != null
                ? ResponseEntity.ok(ObjectMapperUtils.map(veiculo, VeiculoSaidaDTO.class))
                : ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Buscar todos os veículos", response = VeiculoSaidaDTO[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operação realizada com sucesso"),
            @ApiResponse(code = 401, message = "Não autorizado para visualizar"),
            @ApiResponse(code = 403, message = "É proibido acessar o recurso que você estava tentando acessar"),
            @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado")
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<VeiculoSaidaDTO>> buscarTodos() {
        List<Veiculo> veiculos = service.findAll();
        return ResponseEntity.ok(ObjectMapperUtils.mapAll(veiculos, VeiculoSaidaDTO.class));
    }

    @ApiOperation(value = "Remover um veículo pelo ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operação realizada com sucesso"),
            @ApiResponse(code = 204, message = "Não há dados cadastrados"),
            @ApiResponse(code = 401, message = "Não autorizado para visualizar"),
            @ApiResponse(code = 403, message = "É proibido acessar o recurso que você estava tentando acessar"),
            @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> remover(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException ex) {
            return ResponseEntity.noContent().build();
        }
    }

    @ApiOperation(value = "Retornar o número de veículos não vendidos", response = Long.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operação realizada com sucesso"),
            @ApiResponse(code = 401, message = "Não autorizado para visualizar"),
            @ApiResponse(code = 403, message = "É proibido acessar o recurso que você estava tentando acessar"),
            @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado")
    })
    @GetMapping("/nao-vendidos")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> totalVeiculosNaoVendidos() {
        List<Veiculo> veiculos = service.findAll();
        return ResponseEntity.ok(veiculos.stream().filter(veiculo -> !veiculo.getVendido()).count());
    }

    @ApiOperation(value = "Retornar a quantidade de veículos distribuídos por década", response = VeiculoDecadaDTO[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operação realizada com sucesso"),
            @ApiResponse(code = 401, message = "Não autorizado para visualizar"),
            @ApiResponse(code = 403, message = "É proibido acessar o recurso que você estava tentando acessar"),
            @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado")
    })
    @GetMapping("/decada")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<VeiculoDecadaDTO>> veiculosPorDecadaFabricacao() {
        return ResponseEntity.ok(service.obterVeiculosPorDecadas());
    }

    @ApiOperation(value = "Retornar a informação da distruição de veículos por fabricante", response = VeiculoFabricanteDTO[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operação realizada com sucesso"),
            @ApiResponse(code = 401, message = "Não autorizado para visualizar"),
            @ApiResponse(code = 403, message = "É proibido acessar o recurso que você estava tentando acessar"),
            @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado")
    })
    @GetMapping("/distribuicao-fabricante")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<VeiculoFabricanteDTO>> distribuicaoPorFabricante() {
        return ResponseEntity.ok(service.obterDistribuicaoPorFabricante());
    }

    @ApiOperation(value = "Retornar a informação da distruição de veículos por fabricante", response = VeiculoSaidaDTO[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operação realizada com sucesso"),
            @ApiResponse(code = 401, message = "Não autorizado para visualizar"),
            @ApiResponse(code = 403, message = "É proibido acessar o recurso que você estava tentando acessar"),
            @ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado")
    })
    @GetMapping("/cadastro/ultima-semana")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<VeiculoSaidaDTO>> buscarVeiculosRegistradosUltimaSemana() {
        List<Veiculo> veiculos = service.findByCreatedBetween(LocalDateTime.now().minusWeeks(1), LocalDateTime.now());
        return ResponseEntity.ok(ObjectMapperUtils.mapAll(veiculos, VeiculoSaidaDTO.class));
    }
}
