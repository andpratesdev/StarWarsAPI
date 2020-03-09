package com.b2w.planetas.api.services;

import java.util.List;
import java.util.Optional;

import com.b2w.planetas.api.models.Planeta;

public interface PlanetaService {

	List<Planeta> listarTodos();

	Optional<Planeta> buscarPorId(String id);
	
	Optional<Planeta> buscarPorNome(String id);

	Planeta cadastrar(Planeta planeta);

	void remover(String id);

	int obterQtdeAparicoesFilmes(String nome);

}
