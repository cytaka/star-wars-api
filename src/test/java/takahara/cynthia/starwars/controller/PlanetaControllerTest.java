package takahara.cynthia.starwars.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import takahara.cynthia.starwars.dto.PlanetaDto;
import takahara.cynthia.starwars.model.Planeta;
import takahara.cynthia.starwars.repository.PlanetaRepository;

public class PlanetaControllerTest {

	@Autowired
	private PlanetaController controller;
	@Mock
	private PlanetaRepository repository;

	@Before
	public void init() {
		repository = Mockito.mock(PlanetaRepository.class);
		controller = new PlanetaController(repository);
	}

	private List<Planeta> criarListaPlaneta(Boolean unico) {
		List<Planeta> lista = new ArrayList<Planeta>();
		lista.add(new Planeta("Naboo", "temperate", "grassy hills, swamps, forests, mountains"));
		if (!unico)
			lista.add(new Planeta("Tatooine", "arid", "desert"));
		return lista;

	}

	@Test
	public void testListarTodosPlanetas() {
		Mockito.when(repository.findAll()).thenReturn(criarListaPlaneta(false));
		ResponseEntity<List<PlanetaDto>> resultado = controller.listar(null);
		Assert.assertNotNull(resultado);
		Assert.assertNotNull(resultado.getBody().iterator().next().getApariacoes());
		Assert.assertEquals(2, resultado.getBody().size());
	}

	@Test
	public void testListarTodosPlanetasVazio() {
		ResponseEntity<List<PlanetaDto>> resultado = controller.listar(null);
		Assert.assertNotNull(resultado);
		Assert.assertTrue(resultado.getBody().isEmpty());
	}

	@Test
	public void testListarPlanetasPorNome() {
		String nome = "Naboo";
		Mockito.when(repository.findByNome(nome)).thenReturn(criarListaPlaneta(true));
		ResponseEntity<List<PlanetaDto>> resultado = controller.listar(nome);
		Assert.assertNotNull(resultado);
		Assert.assertEquals(nome, resultado.getBody().iterator().next().getNome());
	}

	@Test
	public void testListarPlanetasPorNomeVazio() {
		String nome = "Naboo";
		ResponseEntity<List<PlanetaDto>> resultado = controller.listar(nome);
		Assert.assertNotNull(resultado);
		Assert.assertTrue(resultado.getBody().isEmpty());
	}

	@Test
	public void testPesquisarPlanetaPorId() {
		String id = "6097134690d0210d61c51d7c";
		Planeta planeta = new Planeta("Naboo", "temperate", "grassy hills, swamps, forests, mountains");
		planeta.setId(id);
		PlanetaDto dto = new PlanetaDto(planeta, controller.getApariacoes(planeta.getNome()));

		Mockito.when(repository.findById(id)).thenReturn(Optional.of(planeta));
		ResponseEntity<PlanetaDto> resultado = controller.pesquisar(id);
		Assert.assertNotNull(resultado);
		Assert.assertEquals(dto.getApariacoes(), resultado.getBody().getApariacoes());
		Assert.assertEquals(dto.getNome(), resultado.getBody().getNome());

	}

	@Test()
	public void testPesquisarPlanetaPorIdInvalido() {
		String id = "invalido";
		ResponseEntity<PlanetaDto> resultado = controller.pesquisar(id);
		Assert.assertNotNull(resultado);
		Assert.assertEquals(HttpStatus.NOT_FOUND, resultado.getStatusCode());
	}

	@Test
	public void testInserirPlaneta() {
		Planeta planeta = new Planeta("Naboo", "temperate", "grassy hills, swamps, forests, mountains");
		PlanetaDto dto = new PlanetaDto(planeta, controller.getApariacoes(planeta.getNome()));
		Mockito.when(repository.save(planeta)).thenReturn(planeta);
		ResponseEntity<PlanetaDto> resultado = controller.inserir(planeta, UriComponentsBuilder.newInstance());
		Assert.assertNotNull(resultado);
		Assert.assertEquals(dto.getApariacoes(), resultado.getBody().getApariacoes());
		Assert.assertEquals(dto.getNome(), resultado.getBody().getNome());
	}
	
	@Test
	public void testAlterarPlaneta() {
		String id = "6097134690d0210d61c51d7c";
		Planeta planeta = new Planeta("Naboo", "temperate", "grassy hills, swamps, forests, mountains");
		planeta.setId(id);

		Mockito.when(repository.findById(id)).thenReturn(Optional.of(planeta));
		Planeta planetaAlterado = new Planeta("Naboo Modificado", "temperate",
				"grassy hills, swamps, forests, mountains");
		planetaAlterado.setId(id);
		PlanetaDto dto = new PlanetaDto(planetaAlterado, controller.getApariacoes(planetaAlterado.getNome()));

		ResponseEntity<PlanetaDto> resultado = controller.alterar(id, planetaAlterado);
		Assert.assertNotNull(resultado);
		Assert.assertEquals(dto.getApariacoes(), resultado.getBody().getApariacoes());
		Assert.assertEquals(dto.getNome(), resultado.getBody().getNome());
	}
	@Test
	public void testAlterarPlanetaPorIdInvalido() {
		String id = "invalido";
		Planeta planetaAlterado = new Planeta("Naboo Modificado", "temperate",
				"grassy hills, swamps, forests, mountains");

		ResponseEntity<PlanetaDto> resultado = controller.alterar(id, planetaAlterado);
		Assert.assertNotNull(resultado);
		Assert.assertEquals(HttpStatus.NOT_FOUND, resultado.getStatusCode());
	}

	@Test
	public void testRemoverPlaneta() {
		String id = "6097134690d0210d61c51d7c";
		Planeta planeta = new Planeta("Naboo", "temperate", "grassy hills, swamps, forests, mountains");
		planeta.setId(id);
		Mockito.when(repository.findById(id)).thenReturn(Optional.of(planeta));
		controller.remover(id);
		verify(repository, times(1)).deleteById(id);

	}
	@Test
	public void testRemoverPlanetaPorIdInvalido() {
		String id = "invalido";
		ResponseEntity<?> resultado = controller.remover(id);
		Assert.assertEquals(HttpStatus.NOT_FOUND, resultado.getStatusCode());

	}

}
