package takahara.cynthia.starwars.dto;

import java.util.Objects;

import takahara.cynthia.starwars.model.Planeta;

public class PlanetaDto {

	private String id;
	private String nome;
	private String clima;
	private String terreno;
	private Integer apariacoes;

	public PlanetaDto(Planeta planeta, Integer apariacoes) {
		super();
		this.id = planeta.getId();
		this.nome = planeta.getNome();
		this.clima = planeta.getClima();
		this.terreno = planeta.getTerreno();
		this.apariacoes = 0;
		if (Objects.nonNull(apariacoes))
			this.apariacoes = apariacoes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getClima() {
		return clima;
	}

	public void setClima(String clima) {
		this.clima = clima;
	}

	public String getTerreno() {
		return terreno;
	}

	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}

	public Integer getApariacoes() {
		return apariacoes;
	}

	public void setApariacoes(Integer apariacoes) {
		this.apariacoes = apariacoes;
	}

}
