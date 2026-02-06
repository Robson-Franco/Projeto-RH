package com.RecHuman.RecHuman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.RecHuman.RecHuman.entity.Candidato;
import com.RecHuman.RecHuman.repository.CandidatoRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/candidatos")
public class CandidatoController {
	
	@Autowired
	private CandidatoRepository candidatoRepository;
	
	@GetMapping("/cadastrar")
	public String adicionarCandidato(Model model) {
		model.addAttribute("candidato", new Candidato());
		return "/candidato/cadastrarCandidato";
	}
	
	@PostMapping("/salvar")
	public String salvarCandidato(@Valid Candidato candidato, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return "/candidato/cadastrarCandidato";
		}
		candidatoRepository.save(candidato);
		attributes.addFlashAttribute("success", "Candidato Cadastrado com sucesso!");
		return "redirect:/candidatos/cadastrar";
	}
	
	

}
