package com.b2w.planetas.api.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.b2w.planetas.api.models.Planeta;
import com.b2w.planetas.api.services.PlanetaService;

@RestController
@RequestMapping("planeta")
public class PlanetaController {

	@Autowired
	private PlanetaService planetaService;

	@GetMapping
	public ResponseEntity<List<Planeta>> listarTodos() {
		return new ResponseEntity<>(this.planetaService.listarTodos(), HttpStatus.OK);
	}

	@GetMapping(path = "{id}")
	public ResponseEntity<Planeta> buscarPorId(@PathVariable("id") String id) {
		Optional<Planeta> resultado = this.planetaService.buscarPorId(id);
		if (!resultado.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(resultado.get(), HttpStatus.OK);
	}

	@GetMapping(path = "buscarPorNome/{nome}")
	public ResponseEntity<Planeta> buscarPorNome(@PathVariable("nome") String nome) {
		Optional<Planeta> resultado = this.planetaService.buscarPorNome(nome);
		if (!resultado.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(resultado.get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Planeta> cadastrar(@Valid @RequestBody Planeta planeta) {
		return new ResponseEntity<>(this.planetaService.cadastrar(planeta), HttpStatus.CREATED);
	}

	@DeleteMapping(path = "{id}")
	public ResponseEntity<Planeta> remover(@PathVariable("id") String id) {
		this.planetaService.remover(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
