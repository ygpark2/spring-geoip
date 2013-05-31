package com.ol.mvc.web.config;

import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.orm.hibernate3.HibernateExceptionTranslator;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Equivalent to &lt;mvc:annotation:driven/&gt; . Uses
 * {@code DelegatingWebMvcConfiguration}. Implement {@code WebMvcConfigurer} or
 * extend {@code WebMvcConfigurerAdapter}.
 * 
 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer
 * 
 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
 * 
 * @author <a href="http://rockhoppertech.com/blog/">Gene De Lisa</a>
 * 
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.ol.mvc.web" })
@EnableJpaRepositories("com.ol.mvc.repositories")
public class WebMvcConfig extends WebMvcConfigurerAdapter { // WebMvcConfigurerAdapter {

	private static final String MESSAGE_SOURCE = "/WEB-INF/classes/messages";

	private static final Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);

	/**
	 * @return the view resolver
	 */
	@Bean
	public ViewResolver viewResolver() {
		logger.debug("setting up view resolver");

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Bean(name = "messageSource")
	public MessageSource configureMessageSource() {
		logger.debug("setting up message source");
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename(MESSAGE_SOURCE);
		messageSource.setCacheSeconds(5);
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver lr = new org.springframework.web.servlet.i18n.SessionLocaleResolver();
		lr.setDefaultLocale(Locale.ENGLISH);
		return lr;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		logger.debug("setting up resource handlers");
		registry.addResourceHandler("/resources/").addResourceLocations("/resources/**");
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		logger.debug("configureDefaultServletHandling");
		// if the spring dispatcher is mapped to / then forward non handled
		// requests
		// (e.g. static resource) to the container's "default servlet"
		configurer.enable();
	}

	@Override
	public void addInterceptors(final InterceptorRegistry registry) {
		registry.addInterceptor(new LocaleChangeInterceptor());
	}

	@Bean 
    public HibernateExceptionTranslator hibernateExceptionTranslator(){ 
		return new HibernateExceptionTranslator(); 
    }

	@Bean
	public AnnotationMethodHandlerAdapter adapter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(new HibernateAwareObjectMapper());

		AnnotationMethodHandlerAdapter adapter = new AnnotationMethodHandlerAdapter();
		adapter.setMessageConverters(new HttpMessageConverter[] { converter });

		return adapter;
	}

    public void configureMessageConverters(	List converters) {
        super.configureMessageConverters(converters);
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(new HibernateAwareObjectMapper());
        converters.add(converter);
    }

    @Bean
	public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
		SimpleMappingExceptionResolver b = new SimpleMappingExceptionResolver();

		Properties mappings = new Properties();
		mappings.put("org.springframework.web.servlet.PageNotFound", "p404");
		mappings.put("org.springframework.dao.DataAccessException", "dataAccessFailure");
		mappings.put("org.springframework.transaction.TransactionException", "dataAccessFailure");
		b.setExceptionMappings(mappings);
		return b;
	}

}
