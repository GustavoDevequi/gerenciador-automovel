package com.admin.spring.web.controllers.viewControllers.adminControllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.admin.spring.api.empresa.Empresa;
import com.admin.spring.api.marca.DadosAtualizarMarca;
import com.admin.spring.api.marca.DadosCadastroMarca;
import com.admin.spring.api.marca.DadosListagemMarca;
import com.admin.spring.api.marca.Marca;
import com.admin.spring.api.marca.MarcaRepository;
import com.admin.spring.service.UserService;


@RestController
@RequestMapping("marcas")
public class MarcaController {
	
	
	@Autowired
	private MarcaRepository repository;

		// Manda Informações para view
		@RequestMapping(value = "/cadastrar-marca", method = RequestMethod.GET)
		public ModelAndView novaMarcaForm() {
			ModelAndView mav = new ModelAndView("marca/cadastrar-marca");
			mav.addObject("novaMarca", new Marca());

			return mav;
		}
		// Salva dados formulário
		@RequestMapping(value = "/cadastrar-marca", method = RequestMethod.POST)
    public ModelAndView novaMarca(
							  @ModelAttribute("novaMarca") @Valid DadosCadastroMarca novaMarca,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
							  Marca marca,
							  HttpServletResponse httpResponse,
							  Errors errors,
							  ModelAndView modelAndView
							  ) {

		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("/marca/cadastrar-marca");
		}else{
			repository.save(new Marca(novaMarca));
			redirectAttributes.addFlashAttribute("marcaSalvo", true);

			modelAndView.setViewName("redirect:/marcas/lista-marcas");
		}

        return modelAndView;
    }


	// Manda Informações para view
	@RequestMapping(value = "/lista-marcas", method = RequestMethod.GET)
	public ModelAndView enviaDadosFront() {
		ModelAndView mav = new ModelAndView("marca/lista-marcas");
		List<Marca> marca = repository.findAll();
		mav.addObject("marcas", marca);
		return mav;
	}
	
	@RequestMapping(value = "/editar-marca/{id}", method = RequestMethod.GET)
	public ModelAndView atualizarId(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView("marca/editar-marca");
		Marca marcaAtualizar = repository.findById(id).get();
		mav.addObject("atualizarMarca", marcaAtualizar);

		return mav;
	}

	@RequestMapping(value = "/editar-marca/{id}", method = RequestMethod.POST)
	@Transactional
	public ModelAndView atualizar(
		@ModelAttribute("atualizarMarca") @Valid DadosAtualizarMarca atualizarMarca, 
			BindingResult bindingResult,	
			@PathVariable Long id, 
			RedirectAttributes redirectAttributes,
			Errors errors,
			ModelAndView modelAndView,
			Model model,
			HttpServletResponse httpResponse) {

			if(bindingResult.hasErrors()) {
				modelAndView.setViewName("marca/editar-marca");
			}else{

			var marca= repository.findById(id).get();
			marca.atualizarInformacoes(atualizarMarca);
			modelAndView.setViewName("redirect:/marcas/lista-marcas");
			redirectAttributes.addFlashAttribute("marcaAtualizado", true);
			}


		
		return modelAndView;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Empresa> remove(@PathVariable String id) {
		repository.deleteById(Long.valueOf(id));
		return ResponseEntity.noContent().build();
	}
	
}
