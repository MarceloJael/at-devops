package com.at.webfluxR2DBC.repository;

import com.at.webfluxR2DBC.model.Veiculo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends ReactiveCrudRepository<Veiculo, Long> {
}
