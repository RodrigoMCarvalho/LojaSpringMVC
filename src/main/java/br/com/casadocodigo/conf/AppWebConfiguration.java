package br.com.casadocodigo.conf;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.google.common.cache.CacheBuilder;

import br.com.casadocodigo.controller.HomeController;
import br.com.casadocodigo.dao.ProdutoDAO;
import br.com.casadocodigo.infra.FilerSaver;
import br.com.casadocodigo.model.CarrinhoCompras;

@EnableWebMvc
@ComponentScan(basePackageClasses= {HomeController.class, ProdutoDAO.class, 
		FilerSaver.class, CarrinhoCompras.class}) //scanear o pacote das classes
@EnableCaching  //habilitar o cache
public class AppWebConfiguration {
	
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		
		//todos os Beans ficam disponíveis como atributos na JSP
		//resolver.setExposeContextBeansAsAttributes(true);
		
		//disponibiliza apenas o bean carrinhoCompras
		resolver.setExposedContextBeanNames("carrinhoCompras");
		
		return resolver;
	}
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		
		messageSource.setBasename("/WEB-INF/messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(1);
		
		return messageSource;
	}
	
	@Bean //conversão de datas - o nome do método é padrão
	public FormattingConversionService mvcConversionService() {
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
		DateFormatterRegistrar registrar = new DateFormatterRegistrar();
		
		registrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
		registrar.registerFormatters(conversionService);
		
		return conversionService;
	}
	
	@Bean  //para enviar arquivos para o servidor
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
		
	}
	
	//para evitar o erro: No qualifying bean of type [org.springframework.web.client.RestTemplate] found for dependency: 
	//expected at least 1 bean which qualifies as autowire candidate for this dependency.
	@Bean 
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean   //habilitar o cache
	public CacheManager cacheManager() { //OBS: o tamanho máximo ou o tempo para expiração variam entre aplicações
		
		CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder()
				.maximumSize(100)
				.expireAfterAccess(5, TimeUnit.MINUTES);
		
		GuavaCacheManager manager = new GuavaCacheManager();
		manager.setCacheBuilder(builder);
		
		return manager;
	}
	
	@Bean   //retornar em formato json
	public ViewResolver contentNegotiationViewResolver(ContentNegotiationManager manager) {
		List<ViewResolver> viewResolvers = new ArrayList<>();
		viewResolvers.add(internalResourceViewResolver());
		viewResolvers.add(new JsonViewResolver());
		
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setViewResolvers(viewResolvers); 
		
		return resolver;
	}
	
	
	
	
	
	
	
	
	
}
