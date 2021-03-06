package takahara.cynthia.starwars.dto.api;

import java.io.Serializable;
import java.util.List;

public class PlanetList implements Serializable {

	private Integer count;
	private String next;
	private List<Planet> results;

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}


	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<Planet> getResults() {
		return results;
	}

	public void setResults(List<Planet> results) {
		this.results = results;
	}
}
