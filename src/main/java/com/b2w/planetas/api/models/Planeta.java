package com.b2w.planetas.api.models;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Planeta {

	@Id
	private String id;
	@NotEmpty(message = "Campo nome é obrigatório.")
	private String nome;
	@NotEmpty(message = "Campo clima é obrigatório.")
	private String clima;
	@NotEmpty(message = "Campo terreno é obrigatório.")
	private String terreno;
	private int aparicoesFilmes;

	public Planeta() {
	}

	public Planeta(String nome, String clima, String terreno, int aparicoesFilmes) {
		this.nome = nome;
		this.clima = clima;
		this.terreno = terreno;
		this.aparicoesFilmes = aparicoesFilmes;
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

	public int getAparicoesFilmes() {
		return aparicoesFilmes;
	}

	public void setAparicoesFilmes(int aparicoesFilmes) {
		this.aparicoesFilmes = aparicoesFilmes;
	}

}
