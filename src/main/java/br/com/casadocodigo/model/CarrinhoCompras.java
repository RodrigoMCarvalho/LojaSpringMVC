package br.com.casadocodigo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION) //padrão SCOPE_APPLICATION
public class CarrinhoCompras implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<CarrinhoItem, Integer>();
	
	public Collection<CarrinhoItem> getItens() {
		return itens.keySet();
	}
	
	public void add(CarrinhoItem item) {
		itens.put(item, getQuantidade(item) + 1);
		System.out.println(itens.size());
	}

	public Integer getQuantidade(CarrinhoItem item) {
		if (!itens.containsKey(item)) {  //se não conter o item
			itens.put(item, 0);
		}
		return itens.get(item); //retorna o valor de item
	}
	
	public int getQuantidade() {
		return itens.values().stream().reduce(0, 
				(proximo, acumulador) -> proximo + acumulador);
	}
	
	public BigDecimal getTotal(CarrinhoItem item) {
		return item.getTotal(getQuantidade(item));
	}
	
	public BigDecimal getTotal() {
		BigDecimal total  = BigDecimal.ZERO;
		
		for (CarrinhoItem carrinhoItem : itens.keySet()) { //para cada 
			total = total.add(getTotal(carrinhoItem));
		}
		
		return total;
	}

	public void remover(Integer produtoId, TipoPreco tipoPreco) {
		
		Produto produto = new Produto();
		produto.setId(produtoId);
		
		itens.remove(new CarrinhoItem(produto, tipoPreco));
		
	}
	
	
	
	
	
	
	
	
	
}
