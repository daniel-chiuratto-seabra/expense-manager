package br.com.danielseabra.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@PropertySource("classpath:application.properties")
@EnableWebMvc
@ComponentScan(basePackages = {
		"br.com.danielseabra.component",
		"br.com.danielseabra.controller",
		"br.com.danielseabra.service",
		"br.com.danielseabra.transformer"
})
public class ApplicationConfig implements WebMvcConfigurer {

	@Bean
	public InternalResourceViewResolver buildViewResolver() {
		final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver("/WEB-INF/views/", ".jsp");
		viewResolver.setViewClass(JstlView.class);
		return viewResolver;
	}

}
