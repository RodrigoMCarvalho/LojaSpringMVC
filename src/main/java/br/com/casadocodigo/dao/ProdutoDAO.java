package br.com.casadocodigo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.model.Produto;

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
}
