package com.admin.spring.api.empresa;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.admin.spring.api.endereco.DadosAtualizarEndereco;


public class DadosAtualizarEmpresa{
		
		private Long id;
		@NotBlank
		private String nome;
		@Email
		private String email;
		@NotBlank
		private String telefone;
		@Valid
		private DadosAtualizarEndereco endereco;

	public DadosAtualizarEmpresa(Long id, String nome, String email, String telefone, DadosAtualizarEndereco endereco) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.endereco = endereco;
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public DadosAtualizarEndereco getEndereco() {
		return this.endereco;
	}

	public void setEndereco(DadosAtualizarEndereco endereco) {
		this.endereco = endereco;
	}

	public DadosAtualizarEmpresa id(Long id) {
		setId(id);
		return this;
	}

	public DadosAtualizarEmpresa nome(String nome) {
		setNome(nome);
		return this;
	}

	public DadosAtualizarEmpresa email(String email) {
		setEmail(email);
		return this;
	}

	public DadosAtualizarEmpresa telefone(String telefone) {
		setTelefone(telefone);
		return this;
	}

	public DadosAtualizarEmpresa endereco(DadosAtualizarEndereco endereco) {
		setEndereco(endereco);
		return this;
	}

	@Override
	public String toString() {
		return "{" +
			" id='" + getId() + "'" +
			", nome='" + getNome() + "'" +
			", email='" + getEmail() + "'" +
			", telefone='" + getTelefone() + "'" +
			", endereco='" + getEndereco() + "'" +
			"}";
	}


}
