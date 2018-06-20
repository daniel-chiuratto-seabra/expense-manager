package br.com.danielseabra.application.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
		"br.com.danielseabra.component",
		"br.com.danielseabra.controller",
		"br.com.danielseabra.service",
		"br.com.danielseabra.exception"
})
public class ApplicationConfig {

}
