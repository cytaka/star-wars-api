package takahara.cynthia.starwars.dto;

import java.util.Objects;

import takahara.cynthia.starwars.model.Planeta;

public class PlanetaDto extends Planeta {

	private Integer apariacoes;

	public PlanetaDto(Planeta planeta, Integer apariacoes) {
		super();
		this.setId(planeta.getId());
		this.setNome(planeta.getNome());
		this.setClima(planeta.getClima());
		this.setTerreno(planeta.getTerreno());
		this.apariacoes = 0;
		if (Objects.nonNull(apariacoes))
			this.apariacoes = apariacoes;
	}

	public Integer getApariacoes() {
		return apariacoes;
	}

}
