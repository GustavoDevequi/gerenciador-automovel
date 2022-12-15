package com.admin.spring.api.carro;

import com.admin.spring.api.categoria.Categoria;
import com.admin.spring.api.marca.Marca;

public record DadosListagemCarro(
		Long id,
		String nome,
		String descricao,
		String preco,
		Categoria categoria,
		Marca marca) {
	
	public DadosListagemCarro(Carro carro) {
		this(carro.getId(), carro.getNome(), carro.getDescricao(), carro.getPreco(), carro.getCategoria(), carro.getMarca());
	}
	
}
