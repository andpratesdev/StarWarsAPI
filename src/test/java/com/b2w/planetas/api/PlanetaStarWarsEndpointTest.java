package com.b2w.planetas.api;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.b2w.planetas.api.client.models.PlanetaStarWars;
import com.b2w.planetas.api.client.models.PlanetaStarWarsResult;
import com.b2w.planetas.api.client.services.PlanetaStarWarsService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PlanetaStarWarsEndpointTest {
	
	@MockBean
	private PlanetaStarWarsService planetaStarWarsService;
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void listarTodosDeveRetornarStatusCode200() throws Exception {
		BDDMockito.when(planetaStarWarsService.listarTodos()).thenReturn(gerarPlanetaStarWarsResult());
		mockMvc.perform(MockMvcRequestBuilders.get("/planetastarwars")).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void BuscarPorNomeValidoDeveRetornarStatusCode200() throws Exception {
		BDDMockito.when(planetaStarWarsService.buscarPorNome("Planeta1")).thenReturn(gerarPlanetaStarWarsResult());
		mockMvc.perform(MockMvcRequestBuilders.get("/planetastarwars/{nome}", "Planeta1")).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void BuscarPorNomeInexistenteDeveRetornarStatusCode404() throws Exception {
		BDDMockito.when(planetaStarWarsService.buscarPorNome("Planeta1")).thenReturn(new PlanetaStarWarsResult(0L, null, null, null));
		mockMvc.perform(MockMvcRequestBuilders.get("/planetastarwars/{nome}", "Planeta1")).andExpect(MockMvcResultMatchers.status().isNotFound());
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
