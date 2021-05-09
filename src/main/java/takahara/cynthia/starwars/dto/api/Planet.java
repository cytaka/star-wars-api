package takahara.cynthia.starwars.dto.api;

import java.io.Serializable;
import java.util.List;

public class Planet implements Serializable {

	private String name;
	private List<String> films;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getFilms() {
		return films;
	}

	public void setFilms(List<String> films) {
		this.films = films;
	}

}
