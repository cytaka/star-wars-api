package takahara.cynthia.starwars.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import takahara.cynthia.starwars.dto.api.Planet;
import takahara.cynthia.starwars.dto.api.PlanetList;

@Controller
public class ApariacoesController {

	public static Map<String, Integer> getApariacoes(String nome) {
		StringBuffer api = new StringBuffer();
		api.append("https://swapi.dev/api/planets/");
		if (!ObjectUtils.isEmpty(nome))
			api.append("?search=").append(nome);
		RestTemplate restTemplate = new RestTemplate();
		PlanetList lista = restTemplate.getForObject(api.toString(), PlanetList.class);
		Map<String, Integer> map = new HashMap<String, Integer>();
		preencherMapApariacoes(map, lista);
		int pag = 1;
		while (!ObjectUtils.isEmpty(lista.getNext())) {
			pag++;
			lista = restTemplate.getForObject("https://swapi.dev/api/planets/?page=" + pag, PlanetList.class);
			preencherMapApariacoes(map, lista);
		}
		return map;
	}

	private static void preencherMapApariacoes(Map<String, Integer> map, PlanetList lista) {
		map.putAll(lista.getResults().stream().collect(Collectors.toMap(Planet::getName, e -> e.getFilms().size())));

	}
}
