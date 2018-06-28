package br.com.danielseabra.application.config;

import java.nio.charset.Charset;
import java.util.Locale;

import javax.validation.Validation;
import javax.validation.Validator;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@PropertySource("classpath:application.properties")
@EnableWebMvc
@ComponentScan(basePackages = { "br.com.danielseabra.component", "br.com.danielseabra.controller", "br.com.danielseabra.service",
"br.com.danielseabra.transformer" })
public class ApplicationConfig implements WebMvcConfigurer {

	@Bean
	public InternalResourceViewResolver buildViewResolver() {
		final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver("/WEB-INF/views/", ".jsp");
		viewResolver.setViewClass(JstlView.class);
		return viewResolver;
	}

	@Bean("messageSource")
	public MessageSource buildMessageSource() {
		final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:locale/messages");
		messageSource.setDefaultEncoding(Charset.defaultCharset().name());
		messageSource.setUseCodeAsDefaultMessage(true);
		return messageSource;
	}

	@Bean("localeResolver")
	public LocaleResolver buildLocaleResolver() {
		final CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}

	@Bean
	public Validator buildValidator() {
		final Locale locale = LocaleContextHolder.getLocale();
		return Validation.byDefaultProvider().configure()
				.messageInterpolator(new ResourceBundleMessageInterpolator(
						new PlatformResourceBundleLocator("ValidationMessages_" + locale.getLanguage() + "_" + locale.getCountry())))
				.buildValidatorFactory().getValidator();
	}

	@Override
	public void addInterceptors(final InterceptorRegistry registry) {
		final LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		registry.addInterceptor(localeChangeInterceptor);
	}

}
