package com.admin.spring.api.carro;

import javax.validation.constraints.NotBlank;

import com.admin.spring.api.categoria.Categoria;
import com.admin.spring.api.empresa.DadosCadastroEmpresa;
import com.admin.spring.api.marca.DadosCadastroMarca;

public class DadosCadastroCarro{

	private String nome;
	private String descricao;
	private String preco;
	private Categoria categoria;
	private String categoriaid;
	private String marcaid;
	private String empresaid;
	private DadosCadastroMarca marca;
	private DadosCadastroEmpresa empresa;

	public String getEmpresaid() {
		return this.empresaid;
	}

	public void setEmpresaid(String empresaid) {
		this.empresaid = empresaid;
	}

	public String getMarcaid() {
		return this.marcaid;
	}

	public void setMarcaid(String marcaid) {
		this.marcaid = marcaid;
	}

	public String getCategoriaid() {
		return this.categoriaid;
	}

	public void setCategoriaid(String categoriaid) {
		this.categoriaid = categoriaid;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPreco() {
		return this.preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public DadosCadastroMarca getMarca() {
		return this.marca;
	}

	public void setMarca(DadosCadastroMarca marca) {
		this.marca = marca;
	}

	public DadosCadastroEmpresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(DadosCadastroEmpresa empresa) {
		this.empresa = empresa;
	}

	public DadosCadastroCarro nome(String nome) {
		setNome(nome);
		return this;
	}

	public DadosCadastroCarro descricao(String descricao) {
		setDescricao(descricao);
		return this;
	}

	public DadosCadastroCarro preco(String preco) {
		setPreco(preco);
		return this;
	}

	public DadosCadastroCarro categoria(Categoria categoria) {
		setCategoria(categoria);
		return this;
	}

	public DadosCadastroCarro marca(DadosCadastroMarca marca) {
		setMarca(marca);
		return this;
	}

	public DadosCadastroCarro empresa(DadosCadastroEmpresa empresa) {
		setEmpresa(empresa);
		return this;
	}


	@Override
	public String toString() {
		return "{" +
			" nome='" + getNome() + "'" +
			", descricao='" + getDescricao() + "'" +
			", preco='" + getPreco() + "'" +
			", categoria='" + getCategoria() + "'" +
			", marca='" + getMarca() + "'" +
			", empresa='" + getEmpresa() + "'" +
			", categoriaid='" + getCategoriaid() + "'" +
			", marcaid='" + getMarcaid() + "'" +
			", empresaid='" + getEmpresaid() + "'" +
			"}";
	}

	
}
