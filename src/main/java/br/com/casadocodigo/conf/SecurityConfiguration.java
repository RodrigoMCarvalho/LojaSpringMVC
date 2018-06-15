package br.com.casadocodigo.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.casadocodigo.dao.UsuarioDAO;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UsuarioDAO usuarioDao;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()  //boa prática a negação de acesso ser configurada primeiro
			.antMatchers("/produtos/form").hasRole("ADMIN")
			.antMatchers("/produtos").hasRole("ADMIN")
			.antMatchers("/carrinho").permitAll()
			.antMatchers("/produtos/**").permitAll()
			.antMatchers("/resources/**").permitAll()  //para após logar não direcionar para o CSS
			.antMatchers("/").permitAll()
			.anyRequest().authenticated()  //qualquer request autenticado e enviado para tela de login
			.and().formLogin().loginPage("/login").permitAll()
			.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioDao)
		.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	
	
	
}
