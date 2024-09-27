package com.at.veiculos.controller;

import com.at.veiculos.model.Veiculo;
import com.at.veiculos.repository.VeiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class VeiculoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @BeforeEach
    public void setup() {
        veiculoRepository.deleteAll();
    }

    @Test
    public void deveListarVeiculos() throws Exception {
        Veiculo veiculo = new Veiculo();
        veiculo.setMarca("Fiat");
        veiculo.setModelo("Palio");
        veiculo.setCor("Prata");
        veiculo.setAno(1999);
        veiculoRepository.save(veiculo);

        mockMvc.perform(get("/veiculos/listarTodos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].marca").value("Fiat"))
                .andExpect(jsonPath("$[0].modelo").value("Palio"))
                .andExpect(jsonPath("$[0].cor").value("Prata"))
                .andExpect(jsonPath("$[0].ano").value(1999));
    }

    @Test
    public void deveAdicionarVeiculo() throws Exception {
        String veiculoJson = "{\"marca\":\"Chevrolet\",\"modelo\":\"Onix Premier\",\"cor\":\"Prata\",\"ano\":2022}";

        mockMvc.perform(post("/veiculos/adicionarVeiculo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(veiculoJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.mensagem").value("Veículo ID 1 adicionado.")) // Valida a mensagem
                .andExpect(jsonPath("$.data.marca").value("Chevrolet")) // Valida o campo 'marca' dentro de 'data'
                .andExpect(jsonPath("$.data.modelo").value("Onix Premier"))
                .andExpect(jsonPath("$.data.cor").value("Prata"))
                .andExpect(jsonPath("$.data.ano").value(2022));
    }
}