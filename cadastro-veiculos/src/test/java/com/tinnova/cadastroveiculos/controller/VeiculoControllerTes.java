package com.tinnova.cadastroveiculos.controller;

import com.tinnova.cadastroveiculos.Util;
import com.tinnova.cadastroveiculos.dto.VeiculoEntradaDTO;
import com.tinnova.cadastroveiculos.interfaces.IVeiculoService;
import com.tinnova.cadastroveiculos.model.Veiculo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@WebAppConfiguration
public class VeiculoControllerTes {

    private final String BASE_URL = "/veiculos";

    @InjectMocks
    private VeiculoController controller;

    @Mock
    private IVeiculoService service;

    private MockMvc mockMvc;

    private Veiculo veiculo;
    private VeiculoEntradaDTO veiculoEntradaDTO;

    @Before
    public void setup() {
        this.veiculoEntradaDTO = new VeiculoEntradaDTO(1L, "Ka", "Ford", 2020, "Completo", true);
        this.veiculo = new Veiculo(1L, "Ka", "Ford", 2020, "Completo", true, LocalDateTime.now(), LocalDateTime.now());
        mockMvc = standaloneSetup(this.controller).build();
    }

    @Test
    public void deveRetornarOk_QuandoBuscarVeiculo() throws Exception {
        when(this.service.findById(1L)).thenReturn(this.veiculo);
        mockMvc.perform(get(BASE_URL + "/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void deveRetornarNoContent_QuandoBuscarVeiculo() throws Exception {
        when(this.service.findById(1L)).thenReturn(null);
        mockMvc.perform(get(BASE_URL + "/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deveRetornarOk_QuandoBuscarTodosVeiculos() throws Exception {
        List<Veiculo> veiculos = Arrays.asList(new Veiculo(), new Veiculo());
        when(this.service.findAll()).thenReturn(veiculos);
        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk());
    }

    @Test
    public void deveRetornarCreated_QuandoSalvarVeiculo() throws Exception {
        when(this.service.save(any(Veiculo.class))).thenReturn(this.veiculo);
        mockMvc.perform(
                post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Util.asJsonString(this.veiculoEntradaDTO))
        ).andExpect(status().isCreated());
    }

    @Test
    public void deveRetornarOk_QuandoAtualizarVeiculo() throws Exception {
        when(service.findById(any())).thenReturn(this.veiculo);
        when(this.service.save(any(Veiculo.class))).thenReturn(this.veiculo);
        mockMvc.perform(
                put(BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Util.asJsonString(this.veiculoEntradaDTO))
        ).andExpect(status().isOk());
    }

    @Test
    public void deveRetornarNotContent_QuandoAtualizarVeiculoInexistente() throws Exception {
        when(service.findById(any())).thenReturn(null);
        when(this.service.save(any(Veiculo.class))).thenReturn(this.veiculo);
        mockMvc.perform(
                put(BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Util.asJsonString(this.veiculoEntradaDTO))
        ).andExpect(status().isNoContent());
    }

    @Test
    public void deveRetornarOk_QuandoAtualizarParcialmenteVeiculo() throws Exception {
        when(service.findById(any())).thenReturn(this.veiculo);
        when(service.save(any(Veiculo.class))).thenReturn(this.veiculo);
        when(service.partialUpdate(any(Veiculo.class), any(VeiculoEntradaDTO.class))).thenReturn(this.veiculo);
        mockMvc.perform(
                patch(BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Util.asJsonString(this.veiculoEntradaDTO))
        ).andExpect(status().isOk());
    }

    @Test
    public void deveRetornarNoContent_QuandoAtualizarParcialmenteVeiculo() throws Exception {
        when(service.findById(any())).thenReturn(null);
        when(service.save(any(Veiculo.class))).thenReturn(this.veiculo);
        when(service.partialUpdate(any(Veiculo.class), any(VeiculoEntradaDTO.class))).thenReturn(this.veiculo);
        mockMvc.perform(
                patch(BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Util.asJsonString(this.veiculoEntradaDTO))
        ).andExpect(status().isNoContent());
    }

    /**
     * Teste com o nome da marca inválida
     * @throws Exception
     */
    @Test
    public void deveRetornarBadRequest_QuandoSalvarVeiculo() throws Exception {
        VeiculoEntradaDTO veiculoTest = new VeiculoEntradaDTO(1L, "Ka", "Ford123", 2020, "Completo", true);
        when(this.service.save(any(Veiculo.class))).thenReturn(this.veiculo);
        mockMvc.perform(
                post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(Util.asJsonString(veiculoTest))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void deveRetornarOk_QuandoDeletarVeiculo() throws Exception {
        mockMvc.perform(delete(BASE_URL + "/1"))
                .andExpect(status().isOk());
    }
    
}
