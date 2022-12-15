package com.admin.spring.api.carro;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;

import com.admin.spring.api.categoria.Categoria;
import com.admin.spring.api.empresa.Empresa;
import com.admin.spring.api.marca.Marca;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name = "tb_carro")
@Entity(name = "Carro")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Carro {

    public Carro(DadosCadastroCarro dados) {
		this.nome = dados.getNome();
		this.descricao = dados.getDescricao();
		this.preco = dados.getPreco();

		this.categoria = dados.getCategoria();
		
		this.marca = new Marca(dados.getMarca());

		this.empresa = new Empresa(dados.getEmpresa());
		
	}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private String preco;
    
    
	@ManyToOne(cascade=CascadeType.MERGE)
	private Categoria categoria;

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@ManyToOne(cascade=CascadeType.MERGE)
	private Marca marca;

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	@ManyToOne(cascade=CascadeType.MERGE)
	private Empresa empresa;

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public void atualizarInformacoes(@Valid DadosAtualizarCarro dados) {
		if(dados.nome() != null) {
			this.nome = dados.nome();
		}
		if(dados.descricao() != null) {
			this.descricao = dados.descricao();
		}
		if(dados.preco() != null) {
			this.preco = dados.preco();
		}

	}

}