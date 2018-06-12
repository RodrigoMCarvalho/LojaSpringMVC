package br.com.casadocodigo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.casadocodigo.model.Usuario;

@Repository
public class UsuarioDAO implements UserDetailsService {
	
	@PersistenceContext
	private EntityManager manager;
	
	public Usuario loadUserByUsername(String email) {
		System.out.println(email);
		List<Usuario> usuarios = manager.createQuery("SELECT u FROM Usuario u WHERE u.email =:email", Usuario.class)
			.setParameter("email", email)
			.getResultList();
		if (usuarios.isEmpty()) {
			throw new UsernameNotFoundException("Email " + usuarios + " não localizado.");
		}
		return usuarios.get(0);
	}

}
