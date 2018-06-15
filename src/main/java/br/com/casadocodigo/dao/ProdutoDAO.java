package br.com.casadocodigo.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.model.Produto;
import br.com.casadocodigo.model.TipoPreco;

@Repository
@Transactional
public class ProdutoDAO {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void salvar(Produto produto) {
		manager.persist(produto);
	}

	public List<Produto> listar() {
		return manager.createQuery("SELECT p FROM Produto p", Produto.class).getResultList();
	}

	public Produto find(Integer id) {				//traz apenas um produto (distinct)
		return manager.createQuery("SELECT DISTINCT(p) FROM Produto p "
				+ "JOIN FETCH p.precos WHERE p.id = :id", Produto.class)
				.setParameter("id", id)
				.getSingleResult();
	}
	
	
	public BigDecimal totalPrecoPorTipo(TipoPreco tipoPreco) {
		TypedQuery<BigDecimal> query = manager.createQuery("SELECT SUM(preco.valor) FROM Produto p " 
				+ "JOIN p.precos preco WHERE preco.tipo =:tipoPreco", BigDecimal.class);
		query.setParameter("tipoPreco", tipoPreco);
		
		return query.getSingleResult();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
