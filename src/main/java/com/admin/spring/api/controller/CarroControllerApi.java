package com.admin.spring.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.admin.spring.api.carro.DadosAtualizarCarro;
import com.admin.spring.api.carro.DadosCadastroCarro;
import com.admin.spring.api.carro.DadosListagemCarro;
import com.admin.spring.api.carro.Carro;
import com.admin.spring.api.carro.CarroRepository;



@RestController
@RequestMapping("carros-api")
public class CarroControllerApi {
	
	@Autowired
	private CarroRepository repository;

	
	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastroCarro dados) {
		repository.save(new Carro(dados));
	}
	
	
	@GetMapping
	public Page<DadosListagemCarro> listarPagina(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
		return repository.findAll(paginacao).map(DadosListagemCarro::new);
	}
	
	@PutMapping
	@Transactional
	public void atualizar(@RequestBody @Valid DadosAtualizarCarro dados) {
		var medico= repository.getReferenceById(dados.id());
		medico.atualizarInformacoes(dados);
		
	}
	
	@DeleteMapping(path = "/{id}")
	@Transactional
	public void excluir(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
}
