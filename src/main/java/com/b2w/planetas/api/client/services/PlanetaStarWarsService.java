package com.b2w.planetas.api.client.services;

import com.b2w.planetas.api.client.models.PlanetaStarWarsResult;

public interface PlanetaStarWarsService {
	
	PlanetaStarWarsResult listarTodos();
	
	PlanetaStarWarsResult buscarPorNome(String nome);

}
