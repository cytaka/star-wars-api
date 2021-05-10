package takahara.cynthia.starwars.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import takahara.cynthia.starwars.dto.PlanetaDto;
import takahara.cynthia.starwars.model.Planeta;
import takahara.cynthia.starwars.repository.PlanetaRepository;

@RestController
@RequestMapping("/planetas")
public class PlanetaController {

	private PlanetaRepository repository;

	@Autowired
	public PlanetaController(PlanetaRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	public ResponseEntity<List<PlanetaDto>> listar(String nome) {
		List<Planeta> resultado = null;
		if (nome == null)
			resultado = repository.findAll();
		else
			resultado = repository.findByNome(nome);
		List<PlanetaDto> retorno = new ArrayList<PlanetaDto>();

		Map<String, Integer> map = ApariacoesController.getApariacoes(nome);
		resultado.forEach(e -> {
			retorno.add(new PlanetaDto(e, map.get(e.getNome())));
		});

		return ResponseEntity.ok(retorno);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PlanetaDto> pesquisar(@PathVariable String id) {
		Optional<Planeta> planeta = pesquisarPorId(id);
		if (planeta.isPresent())
			return ResponseEntity.ok(new PlanetaDto(planeta.get(), getApariacoes(planeta.get().getNome())));
		return ResponseEntity.notFound().build();
	}

	private Optional<Planeta> pesquisarPorId(String id) {
		return repository.findById(id);
	}

	public Integer getApariacoes(String nome) {
		return ApariacoesController.getApariacoes(nome).get(nome);
	}

	@PostMapping
	public ResponseEntity<PlanetaDto> inserir(@RequestBody @Valid Planeta planeta, UriComponentsBuilder uriBuilder) {
		repository.save(planeta);
		URI uri = uriBuilder.path("/planeta/{id}").buildAndExpand(planeta.getId()).toUri();
		return ResponseEntity.created(uri).body(new PlanetaDto(planeta, getApariacoes(planeta.getNome())));
	}

	@PutMapping("/{id}")
	public ResponseEntity<PlanetaDto> alterar(@PathVariable String id, @RequestBody @Valid Planeta planeta) {
		Optional<Planeta> optional = pesquisarPorId(id);
		if (optional.isPresent()) {
			Planeta entidade = optional.get();
			entidade.setNome(planeta.getNome());
			entidade.setClima(planeta.getClima());
			entidade.setTerreno(planeta.getTerreno());
			repository.save(entidade);
			return ResponseEntity.ok().body(new PlanetaDto(entidade, getApariacoes(entidade.getNome())));
		}
		return ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable String id) {
		Optional<Planeta> optional = pesquisarPorId(id);
		if (optional.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
