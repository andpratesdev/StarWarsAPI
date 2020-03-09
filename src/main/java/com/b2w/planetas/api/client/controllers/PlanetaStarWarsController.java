package com.b2w.planetas.api.client.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.b2w.planetas.api.client.models.PlanetaStarWarsResult;
import com.b2w.planetas.api.client.services.PlanetaStarWarsService;

@Controller
@RequestMapping("planetastarwars")
public class PlanetaStarWarsController {
	
	@Autowired
	private PlanetaStarWarsService planetaStarWarsService;

	@GetMapping
	public ResponseEntity<PlanetaStarWarsResult> listarTodos(){
		return new ResponseEntity<>(this.planetaStarWarsService.listarTodos(), HttpStatus.OK);
	}
	
	@GetMapping(path = "{nome}")
	public ResponseEntity<PlanetaStarWarsResult> buscarPorNome(@PathVariable("nome") String nome){
		PlanetaStarWarsResult planetaStarWarsResult = this.planetaStarWarsService.buscarPorNome(nome);
		if(planetaStarWarsResult.getCount() == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(planetaStarWarsResult, HttpStatus.OK);
	}

}
