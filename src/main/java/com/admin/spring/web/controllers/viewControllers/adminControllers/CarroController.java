package com.admin.spring.web.controllers.viewControllers.adminControllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

import com.admin.spring.api.carro.DadosAtualizarCarro;
import com.admin.spring.api.carro.DadosCadastroCarro;
import com.admin.spring.api.carro.DadosListagemCarro;
import com.admin.spring.api.carro.Carro;
import com.admin.spring.api.carro.CarroRepository;
import com.admin.spring.api.categoria.Categoria;
import com.admin.spring.api.categoria.CategoriaRepository;
import com.admin.spring.api.categoria.DadosCadastroCategoria;
import com.admin.spring.api.empresa.DadosCadastroEmpresa;
import com.admin.spring.api.empresa.Empresa;
import com.admin.spring.api.empresa.EmpresaRepository;
import com.admin.spring.api.endereco.DadosEndereco;
import com.admin.spring.api.marca.DadosCadastroMarca;
import com.admin.spring.api.marca.Marca;
import com.admin.spring.api.marca.MarcaRepository;



@Controller
@RequestMapping("carros")
public class CarroController {
	
	
	@Autowired
	private CarroRepository repository;

	@Autowired
	private MarcaRepository marcaRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

		// Manda Informações para view
		@RequestMapping(value = "/cadastrar-carro", method = RequestMethod.GET)
		public ModelAndView novoCarroForm() {
			ModelAndView mav = new ModelAndView("carro/cadastrar-carro");

			List<Marca> marcas = marcaRepository.findAll();
			List<Categoria> categorias = categoriaRepository.findAll();
			List<Empresa> empresas = empresaRepository.findAll();

			mav.addObject("marcas", marcas);
			mav.addObject("categorias", categorias);
			mav.addObject("empresas", empresas);
			
			mav.addObject("novoCarro", new Carro());

			return mav;
		}

		// Salva dados formulário
		@RequestMapping(value = "/cadastrar-carro", method = RequestMethod.POST)
    public ModelAndView novoCarro(
							  @ModelAttribute("novoCarro") @Valid DadosCadastroCarro novoCarro,					  		  
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
							  Carro carro,
							  HttpServletResponse httpResponse,
							  Errors errors,
							  ModelAndView modelAndView
							  ) {
		String meuLog = ("iniciando");
		meuLog += (novoCarro.toString());
		meuLog += ("finalizando");

		logJava(meuLog);
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("/carro/cadastrar-carro");
		}else{

			novoCarro.setCategoria(new Categoria(Long.parseLong(novoCarro.getCategoriaid())));

			var empresa= empresaRepository.findById(Long.parseLong(novoCarro.getEmpresaid())).get();
			
			DadosEndereco ende = new DadosEndereco(empresa.getEndereco().getId());
			
			novoCarro.setEmpresa(new DadosCadastroEmpresa(empresa.getId(), ende));
			
			novoCarro.setMarca(new DadosCadastroMarca(Long.parseLong(novoCarro.getMarcaid())));
			
			repository.save(new Carro(novoCarro));


			redirectAttributes.addFlashAttribute("carroSalvo", true);

			modelAndView.setViewName("redirect:/carros/lista-carros");
		}

        return modelAndView;
    }


	// Manda Informações para view
	@RequestMapping(value = "/lista-carros", method = RequestMethod.GET)
	public ModelAndView enviaDadosFront() {
		ModelAndView mav = new ModelAndView("carro/lista-carros");
		List<Carro> carro = repository.findAll();
		mav.addObject("carros", carro);
		return mav;
	}
	
	@RequestMapping(value = "/editar-carro/{id}", method = RequestMethod.GET)
	public ModelAndView atualizarId(@PathVariable Long id, ModelAndView modelAndView) {
		List<Marca> marcas = marcaRepository.findAll();
		List<Categoria> categorias = categoriaRepository.findAll();
		List<Empresa> empresas = empresaRepository.findAll();

		modelAndView.addObject("marcas", marcas);
		modelAndView.addObject("categorias", categorias);
		modelAndView.addObject("empresas", empresas);

		modelAndView.setViewName("carro/editar-carro");

		Carro carroAtualizar = repository.findById(id).get();

		modelAndView.addObject("atualizarCarro", carroAtualizar);
		modelAndView.addObject("categoria_sel", carroAtualizar.getCategoria());
		modelAndView.addObject("empresa_sel", carroAtualizar.getEmpresa());
		modelAndView.addObject("marca_sel", carroAtualizar.getMarca());


		return modelAndView;
	}

	@RequestMapping(value = "/editar-carro/{id}", method = RequestMethod.POST)
	@Transactional
	public ModelAndView atualizar(
		@ModelAttribute("atualizarCarro") @Valid DadosAtualizarCarro atualizarCarro, 
			BindingResult bindingResult,	
			@PathVariable Long id, 
			RedirectAttributes redirectAttributes,
			Errors errors,
			ModelAndView modelAndView,
			Model model,
			HttpServletResponse httpResponse) {

			if(bindingResult.hasErrors()) {
				modelAndView.setViewName("carro/editar-carro");
			}else{

			var carro= repository.findById(id).get();

			carro.setCategoria(categoriaRepository.findById(Long.parseLong(atualizarCarro.categoriaid())).get());

			carro.setEmpresa(empresaRepository.findById(Long.parseLong(atualizarCarro.empresaid())).get());

			carro.setMarca(marcaRepository.findById(Long.parseLong(atualizarCarro.marcaid())).get());

			carro.atualizarInformacoes(atualizarCarro);

			modelAndView.setViewName("redirect:/carros/lista-carros");

			redirectAttributes.addFlashAttribute("carroAtualizado", true);
			
			}


		
		return modelAndView;
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Empresa> remove(@PathVariable String id) {
		repository.deleteById(Long.valueOf(id));
		return ResponseEntity.noContent().build();
	}


	private static void logJava(String data) {
        try {
            Files.write(Paths.get("C:\\temp\\logjava.txt"), data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
}
