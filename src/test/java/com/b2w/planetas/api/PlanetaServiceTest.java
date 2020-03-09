package com.b2w.planetas.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.b2w.planetas.api.client.models.PlanetaStarWars;
import com.b2w.planetas.api.client.models.PlanetaStarWarsResult;
import com.b2w.planetas.api.client.services.PlanetaStarWarsService;
import com.b2w.planetas.api.models.Planeta;
import com.b2w.planetas.api.repositories.PlanetaRepository;
import com.b2w.planetas.api.services.PlanetaService;

@SpringBootTest()
class PlanetaServiceTest {

	@Autowired
	private PlanetaService planetaService;
	@MockBean
	private PlanetaRepository planetaRepository;
	@MockBean
	private PlanetaStarWarsService planetaStarWarsService;

	@Test
	public void listarTodosDeveRetornarRegistros() {
		List<Planeta> planetasRetorno = Arrays.asList(new Planeta("Planeta1", "clima1", "terreno1", 1),
				new Planeta("Planeta2", "clima2", "terreno2", 2));

		BDDMockito.when(planetaRepository.findAll()).thenReturn(planetasRetorno);
		List<Planeta> planetas = planetaService.listarTodos();

		assertThat(planetas).isNotNull();
		assertThat(planetas.size()).isEqualTo(2);
	}

	@Test
	public void buscarPorIdValidoDeveRetornarRegistro() {
		Planeta planetaRetorno = gerarPlaneta();
		BDDMockito.when(planetaRepository.findById(planetaRetorno.getId())).thenReturn(Optional.of(planetaRetorno));
		Optional<Planeta> planeta = planetaService.buscarPorId(planetaRetorno.getId());

		assertThat(planeta.get()).isNotNull();
		assertThat(planeta.get().getId()).isEqualTo(planetaRetorno.getId());
		assertThat(planeta.get().getNome()).isEqualTo(planetaRetorno.getNome());
		assertThat(planeta.get().getClima()).isEqualTo(planetaRetorno.getClima());
		assertThat(planeta.get().getTerreno()).isEqualTo(planetaRetorno.getTerreno());
		assertThat(planeta.get().getAparicoesFilmes()).isEqualTo(planetaRetorno.getAparicoesFilmes());
	}

	@Test
	public void buscarPorIdInexistenteDeveRetornarVazio() {
		String id = "1e11";
		BDDMockito.when(planetaRepository.findById("1")).thenReturn(Optional.of(new Planeta()));
		Optional<Planeta> planeta = planetaService.buscarPorId(id);

		assertThat(planeta).isEqualTo(Optional.empty());
	}

	@Test
	public void buscarPorNomeValidoDeveRetornarRegistro() {
		Planeta planetaRetorno = gerarPlaneta();
		BDDMockito.when(planetaRepository.findByNome(planetaRetorno.getNome())).thenReturn(Optional.of(planetaRetorno));
		Optional<Planeta> planeta = planetaService.buscarPorNome(planetaRetorno.getNome());

		assertThat(planeta.get()).isNotNull();
		assertThat(planeta.get().getId()).isEqualTo(planetaRetorno.getId());
		assertThat(planeta.get().getNome()).isEqualTo(planetaRetorno.getNome());
		assertThat(planeta.get().getClima()).isEqualTo(planetaRetorno.getClima());
		assertThat(planeta.get().getTerreno()).isEqualTo(planetaRetorno.getTerreno());
		assertThat(planeta.get().getAparicoesFilmes()).isEqualTo(planetaRetorno.getAparicoesFilmes());
	}

	@Test
	public void buscarPorNomeInexistenteDeveRetornarVazio() {
		BDDMockito.when(planetaRepository.findById("nomeInvalido")).thenReturn(Optional.of(new Planeta()));
		Optional<Planeta> planeta = planetaService.buscarPorId("nome");

		assertThat(planeta).isEqualTo(Optional.empty());
	}

	@Test
	public void deveSalvarPlanetaComNumeroAparicoes() {
		Planeta planeta = gerarPlaneta();
		Planeta planetaRetRepository = gerarPlaneta();
		PlanetaStarWarsResult planetaStarWarsResult = gerarPlanetaStarWarsResult();
		planetaRetRepository.setAparicoesFilmes(planetaStarWarsResult.getResults().get(0).getFilms().size());

		BDDMockito.when(planetaStarWarsService.buscarPorNome(planeta.getNome())).thenReturn(planetaStarWarsResult);
		BDDMockito.when(planetaRepository.save(planeta)).thenReturn(planetaRetRepository);

		Planeta planetaRetorno = planetaService.cadastrar(planeta);

		assertThat(planetaRetorno).isNotNull();
		assertThat(planetaRetorno.getId()).isNotNull();
		assertThat(planetaRetorno.getNome()).isEqualTo(planeta.getNome());
		assertThat(planetaRetorno.getClima()).isEqualTo(planeta.getClima());
		assertThat(planetaRetorno.getTerreno()).isEqualTo(planeta.getTerreno());
		assertThat(planetaRetorno.getAparicoesFilmes()).isNotNull();
		assertThat(planetaRetorno.getAparicoesFilmes())
				.isEqualTo(planetaStarWarsResult.getResults().get(0).getFilms().size());
	}

	@Test
	public void deveObterQtdeAparicoesFilmesPorNomePlanetaDaSwapi() {
		String nome = "Planeta1";
		PlanetaStarWarsResult planetaStarWarsResult = gerarPlanetaStarWarsResult();
		BDDMockito.when(planetaStarWarsService.buscarPorNome(nome)).thenReturn(planetaStarWarsResult);

		int qtdeAparicoesFilmes = planetaService.obterQtdeAparicoesFilmes(nome);

		assertThat(qtdeAparicoesFilmes)
				.isEqualTo(planetaStarWarsResult.getResults().get(0).getFilms().size());

	}

	@Test
	public void deveObterQtdeZeroAparicoesFilmesPorNomePlanetaInexistenteDaSwapi() {
		String nome = "PlanetaInexistente";
		PlanetaStarWarsResult planetaStarWarsResult = new PlanetaStarWarsResult();
		planetaStarWarsResult.setResults(new ArrayList<>());
		BDDMockito.when(planetaStarWarsService.buscarPorNome(nome)).thenReturn(planetaStarWarsResult);

		int qtdeAparicoesFilmes = planetaService.obterQtdeAparicoesFilmes(nome);

		assertThat(qtdeAparicoesFilmes).isEqualTo(0);

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
