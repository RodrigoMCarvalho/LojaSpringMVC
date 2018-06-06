package br.com.casadocodigo.model;

import java.math.BigDecimal;

public class DadosPagamento {
	
	private BigDecimal value; //deverá ter esse nome para integração com o rest("value":500, exemplo)

	public DadosPagamento(BigDecimal value) {
		this.value = value;
	}
	
	public DadosPagamento() {
		// TODO Auto-generated constructor stub
	}
	
	public BigDecimal getValue() {
		return value;
	}
}

