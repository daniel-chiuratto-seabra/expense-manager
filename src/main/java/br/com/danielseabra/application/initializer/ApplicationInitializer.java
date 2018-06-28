package br.com.danielseabra.application.initializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class ApplicationInitializer implements WebApplicationInitializer {

	private static final String TMP_LOCATION = "/tmp";
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 12;

	@Override
	public void onStartup(final ServletContext servletContext) throws ServletException {
		final AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setConfigLocations("br.com.danielseabra.application.config");

		servletContext.addListener(new ContextLoaderListener(context));

		final ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");

		final MultipartConfigElement multiPartConfigElement = new MultipartConfigElement(TMP_LOCATION, MAX_FILE_SIZE, MAX_FILE_SIZE / 2,
				MAX_FILE_SIZE * 2);
		dispatcher.setMultipartConfig(multiPartConfigElement);
	}

}
