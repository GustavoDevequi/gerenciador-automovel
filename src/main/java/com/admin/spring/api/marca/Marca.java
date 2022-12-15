package com.admin.spring.api.marca;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "tb_marca")
@Entity(name = "Marca")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Marca {

    public Marca(DadosCadastroMarca dados) {
    	if(dados.getId() != null) {
    		this.id = dados.getId();
    	}
		this.nome = dados.getNome();
	}
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@NotBlank (message = "O campo nome é obrigatório")
	private String nome;

    
	public void atualizarInformacoes(DadosAtualizarMarca dados) {
		
			this.nome = dados.nome();
		
	
	}

}
