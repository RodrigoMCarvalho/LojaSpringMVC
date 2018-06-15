package br.com.casadocodigo.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.builder.ProdutoBuilder;
import br.com.casadocodigo.conf.JPAConfiguration;
import br.com.casadocodigo.model.Produto;
import br.com.casadocodigo.model.TipoPreco;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={JPAConfiguration.class, ProdutoDAO.class})
public class ProdutoDAOTest {
	
	@Autowired
	private ProdutoDAO dao;
	
	@Test @Transactional
	public void somaLivros() {
	List<Produto> livrosImpressos = ProdutoBuilder.newProduto(TipoPreco.IMPRESSO, BigDecimal.TEN)
			.more(4).buildAll();
	
	List<Produto> livrosEbooks = ProdutoBuilder.newProduto(TipoPreco.EBOOK, BigDecimal.TEN)
			.more(3).buildAll();
	
	livrosImpressos.stream().forEach(dao::salvar);
	livrosEbooks.stream().forEach(dao::salvar);
	
	BigDecimal valor = dao.totalPrecoPorTipo(TipoPreco.EBOOK);
	Assert.assertEquals(new BigDecimal(40).setScale(2), valor);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
}
