package com.RecHuman.RecHuman.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RecHuman.RecHuman.entity.Vaga;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {
	
	Optional <Vaga> findByCodigo(long codigo);
	
	List<Vaga> findByNome(String nome);

}
