package br.com.casadocodigo.model;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable()
public class Preco {

	private BigDecimal valor;
	
	//@Enumerated(EnumType.STRING) - salvará o enum como string no BD, o padrão salvará como int
	//String - pode alterar a ordem, mas não renomear
	//int - pode renomear, mas não alterar a rdem
	private TipoPreco tipo;

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoPreco getTipo() {
		return tipo;
	}

	public void setTipo(TipoPreco tipo) {
		this.tipo = tipo;
	}

}
