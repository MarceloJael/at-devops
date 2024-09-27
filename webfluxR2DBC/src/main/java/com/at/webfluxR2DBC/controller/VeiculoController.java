package com.at.webfluxR2DBC.controller;

import com.at.webfluxR2DBC.model.Veiculo;
import com.at.webfluxR2DBC.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoRepository veiculoRepository;

    // Endpoint para listar todos os veículos
    @GetMapping("/listarTodos")
    public Flux<Veiculo> listarTodos() {
        return veiculoRepository.findAll();
    }

    // Endpoint para buscar veículo por ID
    @GetMapping("/listarPorId/{id}")
    public Mono<ResponseEntity<Veiculo>> listarPorId(@PathVariable Long id) {
        return veiculoRepository.findById(id)
                .map(veiculo -> ResponseEntity.ok(veiculo))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // Endpoint para adicionar novo veículo
    @PostMapping("/adicionarVeiculo")
    public Mono<ResponseEntity<Veiculo>> adicionarVeiculo(@RequestBody Veiculo veiculo) {
        return veiculoRepository.save(veiculo)
                .map(novoVeiculo -> ResponseEntity.status(201).body(novoVeiculo));
    }

    // Endpoint para alterar veículo existente
    @PutMapping("/alterarVeiculo/{id}")
    public Mono<ResponseEntity<Veiculo>> alterarVeiculo(@PathVariable Long id, @RequestBody Veiculo veiculoAtualizado) {
        return veiculoRepository.findById(id)
                .flatMap(veiculoExistente -> {
                    veiculoExistente.setMarca(veiculoAtualizado.getMarca());
                    veiculoExistente.setModelo(veiculoAtualizado.getModelo());
                    veiculoExistente.setCor(veiculoAtualizado.getCor());
                    veiculoExistente.setAno(veiculoAtualizado.getAno());
                    return veiculoRepository.save(veiculoExistente);
                })
                .map(veiculoSalvo -> ResponseEntity.ok(veiculoSalvo))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // Endpoint para deletar veículo por ID
    @DeleteMapping("/deletarVeiculo/{id}")
    public Mono<ResponseEntity<Void>> deletarVeiculo(@PathVariable Long id) {
        return veiculoRepository.findById(id)
                .flatMap(veiculo ->
                        veiculoRepository.delete(veiculo)
                                .then(Mono.just(ResponseEntity.ok().<Void>build()))
                )
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}