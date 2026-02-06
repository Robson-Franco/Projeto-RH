package com.RecHuman.RecHuman.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public String adicionarVaga(Model model) {
		model.addAttribute("vaga", new Vaga());
		return "/vaga/cadastrarVaga";
	}
	
	@PostMapping("/salvar")
	public String salvarVaga(@Valid Vaga vaga, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return "/vaga/cadastrarVaga";
		}
		vagaRepository.save(vaga);
		attributes.addFlashAttribute("success", "Vaga cadastrada com sucesso!");
		return "redirect:/vagas/cadastrar";
	}
	
	@GetMapping("/listar")
	public String listarVagas(Model model){
		model.addAttribute("listarVagas",vagaRepository.findAll());
		return "vaga/listarVagas";
	}
	
	@PostMapping("/deletar/{codigo}")
	public String deletarVaga(@PathVariable("codigo") long codigo, RedirectAttributes attributes) {
		Optional<Vaga> vagaBase = vagaRepository.findByCodigo(codigo);
		if(!vagaBase.isPresent()) {
			attributes.addFlashAttribute("warning","Falha ao deletar, código inexistente.");
			return "redirect:/vagas/listar";
		}
		
		Vaga vagaPresent = vagaBase.get();
		vagaRepository.delete(vagaPresent);
		attributes.addFlashAttribute("success", "Vaga deletada com sucesso!");
		return "redirect:/vagas/listar";
		
	}
	
	@GetMapping("/editar/{codigo}")
	public String editarVaga(@PathVariable("codigo") long codigo, Model model, RedirectAttributes attributes){
		Optional<Vaga> vagaBase = vagaRepository.findByCodigo(codigo);
		if(!vagaBase.isPresent()) {
			attributes.addFlashAttribute("mensagem", "ID inválido para alteração: " + codigo);
			return "redirect:vaga/listarVagas";
		}
		
		Vaga vagaPresente = vagaBase.get();
		model.addAttribute("vaga", vagaPresente);
		return ""; //TODO Modificar
		
		
	}

	

}
