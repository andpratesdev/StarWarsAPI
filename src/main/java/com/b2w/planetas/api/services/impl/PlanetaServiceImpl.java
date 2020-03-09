package com.b2w.planetas.api.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2w.planetas.api.client.models.PlanetaStarWarsResult;
import com.b2w.planetas.api.client.services.PlanetaStarWarsService;
import com.b2w.planetas.api.models.Planeta;
import com.b2w.planetas.api.repositories.PlanetaRepository;
import com.b2w.planetas.api.services.PlanetaService;

@Service
public class PlanetaServiceImpl implements PlanetaService {

	@Autowired
	private PlanetaRepository planetaRepository;

	@Autowired
	private PlanetaStarWarsService planetaStarWarsService;

	@Override
	public List<Planeta> listarTodos() {
		return this.planetaRepository.findAll();
	}

	@Override
	public Optional<Planeta> buscarPorId(String id) {
		return this.planetaRepository.findById(id);
	}

	@Override
	public Optional<Planeta> buscarPorNome(String nome) {
		return this.planetaRepository.findByNome(nome);
	}

	@Override
	public Planeta cadastrar(Planeta planeta) {
		planeta.setId(null);
		planeta.setAparicoesFilmes(obterQtdeAparicoesFilmes(planeta.getNome()));
		return this.planetaRepository.save(planeta);
	}

	@Override
	public void remover(String id) {
		this.planetaRepository.deleteById(id);
	}

	@Override
	public int obterQtdeAparicoesFilmes(String nome) {
		PlanetaStarWarsResult planetaStarWarsResult = this.planetaStarWarsService.buscarPorNome(nome);
		List<String> filmes = planetaStarWarsResult.getResults().stream().flatMap(p -> p.getFilms().stream())
				.collect(Collectors.toList());
		return filmes.size();
	}

}
