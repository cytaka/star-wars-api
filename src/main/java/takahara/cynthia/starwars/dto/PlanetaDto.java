package takahara.cynthia.starwars.dto;

import java.util.Objects;

import takahara.cynthia.starwars.model.Planeta;

public class PlanetaDto extends Planeta {

	private Integer aparicoes;

	public PlanetaDto(Planeta planeta, Integer aparicoes) {
		super();
		this.setId(planeta.getId());
		this.setNome(planeta.getNome());
		this.setClima(planeta.getClima());
		this.setTerreno(planeta.getTerreno());
		this.aparicoes = 0;
		if (Objects.nonNull(aparicoes))
			this.aparicoes = aparicoes;
	}

	public Integer getAparicoes() {
		return aparicoes;
	}

}
