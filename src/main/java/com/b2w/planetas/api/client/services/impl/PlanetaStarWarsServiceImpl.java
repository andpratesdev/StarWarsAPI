package com.b2w.planetas.api.client.services.impl;

import java.util.Arrays;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.b2w.planetas.api.client.models.PlanetaStarWarsResult;
import com.b2w.planetas.api.client.services.PlanetaStarWarsService;

@Service
public class PlanetaStarWarsServiceImpl implements PlanetaStarWarsService {

	String url = "https://swapi.co/api/planets";

	public PlanetaStarWarsResult listarTodos() {
		return buscarSwapi(url, "");
	}

	public PlanetaStarWarsResult buscarPorNome(String nome) {
		return buscarSwapi(url.concat("/?search="), nome);
	}

	private PlanetaStarWarsResult buscarSwapi(String url, String paramFiltro) {
		StringBuilder completeUrl = new StringBuilder();
		completeUrl.append(url).append(paramFiltro);

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();

		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

		ResponseEntity<PlanetaStarWarsResult> response;
		response = restTemplate.exchange(completeUrl.toString(), HttpMethod.GET,
				new HttpEntity<String>("parameters", headers), new ParameterizedTypeReference<PlanetaStarWarsResult>() {
				});

		return response.getBody();
	}

}
