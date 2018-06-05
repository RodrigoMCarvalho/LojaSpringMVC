package br.com.casadocodigo.model;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION) //padrão SCOPE_APPLICATION
public class CarrinhoCompras {

	private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<CarrinhoItem, Integer>();
	
	public void add(CarrinhoItem item) {
		itens.put(item, getQuantidade(item) + 1);
		System.out.println(itens.size());
	}

	private Integer getQuantidade(CarrinhoItem item) {
		if (!itens.containsKey(item)) {  //se não conter o item
			itens.put(item, 0);
		}
		return itens.get(item); //retorna o valor de item
	}
	
	public int getQuantidade() {
		return itens.values().stream().reduce(0, 
				(proximo, acumulador) -> proximo + acumulador);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
