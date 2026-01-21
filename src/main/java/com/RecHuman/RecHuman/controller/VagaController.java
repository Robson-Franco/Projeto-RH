package com.RecHuman.RecHuman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.RecHuman.RecHuman.entity.Vaga;
import com.RecHuman.RecHuman.repository.CandidatoRepository;
import com.RecHuman.RecHuman.repository.VagaRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/vagas")
public class VagaController {
	
	@Autowired
	private VagaRepository vagaRepository;
	
	@Autowired
	private CandidatoRepository candidatoRepository; 
	
	@GetMapping("/cadastrar")
	public String adicionarVaga() {
		return "vaga/cadastrarVaga";
	}
	
	@PostMapping("/salvar")
	public String salvarVaga(@Valid Vaga vaga, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique as informações dos campos...");
			return "redirect:/salvar";
		}
		
		vagaRepository.save(vaga);
		attributes.addFlashAttribute("mensagem", "Vaga cadastrada com sucesso!");
		return "redirect:/salvar";
	}

}
