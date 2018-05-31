package br.com.casadocodigo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.dao.ProdutoDAO;
import br.com.casadocodigo.model.Produto;
import br.com.casadocodigo.model.TipoPreco;

@Controller
public class ProdutosController {
	
	@Autowired
	private ProdutoDAO dao;
	
	@RequestMapping("/produtos/form")
	public ModelAndView form() {
		
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("tipos", TipoPreco.values());  //TipoPreco.values() - retornar todos os valores da classe enum TipoPreco
		//"tipos" é o mesmo de items no view
		
		return modelAndView;
	}
	
	@RequestMapping(value="/produtos", method=RequestMethod.POST)
	public String gravar(Produto produto) {
		System.out.println(produto);
		dao.salvar(produto);
		
		return "produtos/ok";
	}
	
	@RequestMapping(value="/produtos", method=RequestMethod.GET)
	public ModelAndView listar() {
		List<Produto> produtos = dao.listar();
		ModelAndView modelAndView = new ModelAndView("produtos/lista");
		modelAndView.addObject("produtos",produtos);
		
		return modelAndView;
	}
	
	
	
	
	
}
