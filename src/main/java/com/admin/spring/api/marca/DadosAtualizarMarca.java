package com.admin.spring.api.marca;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record DadosAtualizarMarca(
		@NotNull
		Long id,
		@NotBlank(message = "O campo nome é obrigatório")
		String nome) {

}
