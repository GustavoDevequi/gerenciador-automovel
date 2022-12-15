package com.admin.spring.api.carro;

import javax.validation.constraints.NotNull;

import com.admin.spring.api.categoria.DadosCadastroCategoria;

public record DadosAtualizarCarro(
		@NotNull
		Long id, 
		String nome, 
		String descricao, 
		String preco,
		String categoriaid,
		String marcaid,
		String empresaid,
		DadosCadastroCategoria categoria ) {

}
