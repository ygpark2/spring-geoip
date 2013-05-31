package com.ol.mvc.web.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;


/**
 * @Configuration classes =~ <beans/> xml documents
 * @author <a href="http://rockhoppertech.com/blog/">Gene De Lisa</a>
 * 
 */

@Configuration
@ComponentScan(basePackages = { "com.ol.mvc.service" })
public class RootConfig {

	private static final Logger logger = LoggerFactory.getLogger(RootConfig.class);

	@Bean
	public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
		logger.debug("load property place holder configurer!!!!!!!!!!!!!!!!");
		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		ppc.setLocation(new ClassPathResource("/persistence.properties"));
		return ppc;
	}

}