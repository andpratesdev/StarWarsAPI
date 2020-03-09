package com.b2w.planetas.api;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.b2w.planetas.api.client.models.PlanetaStarWars;
import com.b2w.planetas.api.client.models.PlanetaStarWarsResult;
import com.b2w.planetas.api.client.services.PlanetaStarWarsService;
import com.b2w.planetas.api.models.Planeta;
import com.b2w.planetas.api.repositories.PlanetaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PlanetaEndpointTest {

	@MockBean
	private PlanetaRepository planetaRepository;
	@MockBean
	private PlanetaStarWarsService planetaStarWarsService;
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void listarTodosDeveRetornarStatusCode200() throws Exception {
		List<Planeta> planetasRet = Arrays.asList(new Planeta("Planeta1", "Clima1", "Terreno1", 1),
				new Planeta("Planeta2", "Clima2", "Terreno2", 2));

		BDDMockito.when(planetaRepository.findAll()).thenReturn(planetasRet);
		mockMvc.perform(MockMvcRequestBuilders.get("/planeta")).andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void buscarPorIdValidoDeveRetornarStatusCode200() throws Exception {
		BDDMockito.when(planetaRepository.findById("1")).thenReturn(Optional.of(gerarPlaneta()));
		mockMvc.perform(MockMvcRequestBuilders.get("/planeta/{id}", "1"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void buscarPorIdInexistenteDeveRetornarStatusCode404() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/planeta/{id}", "-1"))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	public void buscarPorNomeValidoDeveRetornarStatusCode200() throws Exception {
		BDDMockito.when(planetaRepository.findByNome("nome")).thenReturn(Optional.of(gerarPlaneta()));
		mockMvc.perform(MockMvcRequestBuilders.get("/planeta/buscarPorNome/{nome}", "nome"))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void buscarPorNomeInexistenteDeveRetornarStatusCode404() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/planeta/buscarPorNome/{nome}", "nome"))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

	}

	@Test
	public void salvarPlanetaOkDeveRetornarStatusCode200() throws Exception {
		Planeta planeta = gerarPlaneta();
		PlanetaStarWarsResult planetaStarWarsResult = gerarPlanetaStarWarsResult();
		BDDMockito.when(planetaStarWarsService.buscarPorNome(planeta.getNome())).thenReturn(planetaStarWarsResult);
		BDDMockito.when(planetaRepository.save(planeta)).thenReturn(planeta);

		mockMvc.perform(MockMvcRequestBuilders.post("/planeta").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(planeta)).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	public void salvarPlanetaQuandoNomeNuloDeveRetornarStatusCode400BadRequest() throws Exception {
		Planeta planeta = gerarPlaneta();
		planeta.setNome(null);
		PlanetaStarWarsResult planetaStarWarsResult = gerarPlanetaStarWarsResult();
		BDDMockito.when(planetaStarWarsService.buscarPorNome(planeta.getNome())).thenReturn(planetaStarWarsResult);
		BDDMockito.when(planetaRepository.save(planeta)).thenReturn(planeta);

		mockMvc.perform(MockMvcRequestBuilders.post("/planeta").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(planeta)).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void salvarPlanetaQuandoClimaNuloDeveRetornarStatusCode400BadRequest() throws Exception {
		Planeta planeta = gerarPlaneta();
		planeta.setClima(null);
		PlanetaStarWarsResult planetaStarWarsResult = gerarPlanetaStarWarsResult();
		BDDMockito.when(planetaStarWarsService.buscarPorNome(planeta.getNome())).thenReturn(planetaStarWarsResult);
		BDDMockito.when(planetaRepository.save(planeta)).thenReturn(planeta);

		mockMvc.perform(MockMvcRequestBuilders.post("/planeta").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(planeta)).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void salvarPlanetaQuandoTerrenoNuloDeveRetornarStatusCode400BadRequest() throws Exception {
		Planeta planeta = gerarPlaneta();
		planeta.setTerreno(null);
		PlanetaStarWarsResult planetaStarWarsResult = gerarPlanetaStarWarsResult();
		BDDMockito.when(planetaStarWarsService.buscarPorNome(planeta.getNome())).thenReturn(planetaStarWarsResult);
		BDDMockito.when(planetaRepository.save(planeta)).thenReturn(planeta);

		mockMvc.perform(MockMvcRequestBuilders.post("/planeta").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(planeta)).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	public void removerPlanetaDeveRetornarStatusCode200() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/planeta/{id}", 1))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	private Planeta gerarPlaneta() {
		Planeta planeta = new Planeta("Planeta1", "clima1", "terreno1", 1);
		planeta.setId("1e11");
		return planeta;
	}

	private PlanetaStarWarsResult gerarPlanetaStarWarsResult() {
		PlanetaStarWars planetaStarWars = new PlanetaStarWars();
		planetaStarWars.setName("Planeta1");
		planetaStarWars.setClimate("Clima1");
		planetaStarWars.setTerrain("Terreno1");
		planetaStarWars.setFilms(Arrays.asList("Filme1", "Filme2"));
		return new PlanetaStarWarsResult(1L, null, null, Arrays.asList(planetaStarWars));
	}

}
