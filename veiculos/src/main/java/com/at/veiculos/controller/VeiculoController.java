package com.at.veiculos.controller;

import com.at.veiculos.model.Veiculo;
import com.at.veiculos.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoService veiculoService;

    @Autowired
    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<Veiculo>> listarTodos() {
        List<Veiculo> veiculos = veiculoService.listarTodos();
        return ResponseEntity.ok(veiculos);
    }

    @GetMapping("/listarPorId/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable Long id) {
        Optional<Veiculo> veiculo = veiculoService.buscarPorId(id);
        if (veiculo.isPresent()) {
            return ResponseEntity.ok(veiculo.get());
        }
        return ResponseEntity.status(404).body("Veículo ID " + id + " não encontrado.");
    }

    @PostMapping("/adicionarVeiculo")
    public ResponseEntity<?> adicionarVeiculo(@RequestBody Veiculo veiculo) {
        Veiculo novoVeiculo = veiculoService.adicionar(veiculo);
        ResponseMessage responseMessage = new ResponseMessage("Veículo ID " + novoVeiculo.getId() + " adicionado.", novoVeiculo);
        return ResponseEntity.status(201).body(responseMessage);
    }

    @PutMapping("/alterarVeiculo/{id}")
    public ResponseEntity<?> alterarVeiculo(@PathVariable Long id, @RequestBody Veiculo veiculoAtualizado) {
        Optional<Veiculo> veiculoExistente = veiculoService.buscarPorId(id);
        if (veiculoExistente.isPresent()) {
            veiculoAtualizado.setId(id);
            Veiculo veiculoSalvo = veiculoService.adicionar(veiculoAtualizado);
            String mensagem = "Veículo ID " + id + " alterado.";
            return ResponseEntity.ok(mensagem + "\n" + veiculoSalvo);
        }
        return ResponseEntity.status(404).body("Veículo ID " + id + " não encontrado.");
    }

    @DeleteMapping("/deletarVeiculo/{id}")
    public ResponseEntity<String> deletarVeiculo(@PathVariable Long id) {
        Optional<Veiculo> veiculoExistente = veiculoService.buscarPorId(id);
        if (veiculoExistente.isPresent()) {
            veiculoService.remover(id);
            return ResponseEntity.ok("Veículo ID " + id + " deletado.");
        }
        return ResponseEntity.status(404).body("Veículo ID " + id + " não encontrado.");
    }

}
