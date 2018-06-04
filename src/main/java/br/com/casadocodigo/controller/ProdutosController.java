package br.com.casadocodigo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.dao.ProdutoDAO;
import br.com.casadocodigo.infra.FilerSaver;
import br.com.casadocodigo.model.Produto;
import br.com.casadocodigo.model.TipoPreco;
import br.com.casadocodigo.validation.ProdutoValidation;

@Controller
public class ProdutosController {
	
	@Autowired
	private ProdutoDAO dao;
	
	@Autowired
	private FilerSaver fileSaver;
	
	@InitBinder  //junta a validação no ProdutosController
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new ProdutoValidation());
	}
	
	@RequestMapping("/produtos/form")
	public ModelAndView form(Produto produto) { //recebe um valor devido a validação 
		
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("tipos", TipoPreco.values());  //TipoPreco.values() - retornar todos os valores da classe enum TipoPreco
		//"tipos" é o mesmo de items no view
		
		return modelAndView;
	}
	
	@RequestMapping(value="/produtos", method=RequestMethod.POST)
	public ModelAndView gravar(MultipartFile sumario, @Valid Produto produto,BindingResult resultado, 
				RedirectAttributes redirectAttributes ) {
		//IMPORTANTE: BindingResult tem que vir logo apos do que será validado
		
		if (resultado.hasErrors()) { //se tiver erros
			return form(produto);
		}
		
		//salvamento de arquivo
		String path = fileSaver.writer("arquivos-sumario", sumario);
		produto.setSumarioPath(path);
		
		dao.salvar(produto);
		redirectAttributes.addFlashAttribute("sucesso", "Produto salvo com sucesso!"); //adiciona no próximo redirect
		
		return new ModelAndView("redirect:produtos"); //após persistir no BD, redireciona para /produtos;
		//OBS: É necessario fazer redirect após o formulário enviar um POST, pois ao pressionar F5, 
		//os dados são reenviados podendo duplicar informações no banco de dados.
	}
	
	@RequestMapping(value="/produtos", method=RequestMethod.GET)
	public ModelAndView listar() {
		List<Produto> produtos = dao.listar();
		ModelAndView modelAndView = new ModelAndView("produtos/lista");
		modelAndView.addObject("produtos",produtos);
		
		return modelAndView;
	}
	
	@RequestMapping("/produtos/detalhe/{id}")
	public ModelAndView detalhe(@PathVariable("id") Integer id) {
		 ModelAndView modelAndView = new ModelAndView("produtos/detalhe");
		 Produto produto = dao.find(id);
		 System.out.println(produto);
		 
		 modelAndView.addObject("produto", produto); 
		 
		 
		 
		 return modelAndView;
	}
	
	
	
}
