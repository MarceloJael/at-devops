package com.at.veiculos.service;

import com.at.veiculos.model.Veiculo;
import com.at.veiculos.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    @Autowired
    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public List<Veiculo> listarTodos() {
        return veiculoRepository.findAll();
    }

    public Optional<Veiculo> buscarPorId(Long id) {
        return veiculoRepository.findById(id);
    }

    public Veiculo adicionar(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public void remover(Long id) {
        veiculoRepository.deleteById(id);
    }

}
