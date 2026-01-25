package com.RecHuman.RecHuman.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RecHuman.RecHuman.entity.Candidato;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long>{
	
	Candidato findByRg(String rg);
	
	Candidato findById(long id);
	
	List<Candidato> findByNomeCandidato(String nomeCandidato);

}
