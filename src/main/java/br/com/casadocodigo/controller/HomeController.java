package br.com.casadocodigo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.dao.ProdutoDAO;
import br.com.casadocodigo.model.Produto;

@Controller
public class HomeController {
	
	@Autowired
	private ProdutoDAO dao;

	@RequestMapping("/")
	public ModelAndView index() {
		List<Produto> produtos = dao.listar();
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("produtos",produtos);
		
		return modelAndView;
	}

}
