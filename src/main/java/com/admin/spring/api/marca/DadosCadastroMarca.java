package com.admin.spring.api.marca;

import javax.validation.constraints.NotBlank;

public class DadosCadastroMarca {

	private Long id;
	@NotBlank(message = "O campo nome é obrigatório")
	private String nome;

	public DadosCadastroMarca() {
	}

	public DadosCadastroMarca(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public DadosCadastroMarca(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public DadosCadastroMarca id(Long id) {
		setId(id);
		return this;
	}

	public DadosCadastroMarca nome(String nome) {
		setNome(nome);
		return this;
	}

	@Override
	public String toString() {
		return "{" +
				" id='" + getId() + "'" +
				", nome='" + getNome() + "'" +
				"}";
	}

}
