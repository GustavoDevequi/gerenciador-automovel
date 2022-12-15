package com.admin.spring.api.marca;

public record DadosListagemMarca(
		Long id,
		String nome) {
	
	public DadosListagemMarca(Marca fornecedor) {
		this(fornecedor.getId(), fornecedor.getNome());
	}
}
