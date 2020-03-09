package com.b2w.planetas.api.client.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class PlanetaStarWarsResult implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long count;
	private String next;
	private String previous;

	private List<PlanetaStarWars> results;

	public PlanetaStarWarsResult() {
	}

	public PlanetaStarWarsResult(Long count, String next, String previous, List<PlanetaStarWars> results) {
		this.count = count;
		this.next = next;
		this.previous = previous;
		this.results = results;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public String getPrevious() {
		return previous;
	}

	public void setPrevious(String previous) {
		this.previous = previous;
	}

	public List<PlanetaStarWars> getResults() {
		return results;
	}

	public void setResults(List<PlanetaStarWars> results) {
		this.results = results;
	}

}
