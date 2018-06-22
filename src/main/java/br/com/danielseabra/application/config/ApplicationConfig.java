package br.com.danielseabra.application.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
		"br.com.danielseabra.component",
		"br.com.danielseabra.controller",
		"br.com.danielseabra.service",
		"br.com.danielseabra.exception"
})
public class ApplicationConfig implements WebMvcConfigurer {}
