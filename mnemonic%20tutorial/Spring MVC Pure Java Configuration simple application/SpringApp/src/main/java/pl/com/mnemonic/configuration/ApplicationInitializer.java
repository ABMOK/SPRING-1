package pl.com.mnemonic.configuration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by mnemonic on 2016-10-31.
 */
public class ApplicationInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext applicationMainContext = new AnnotationConfigWebApplicationContext();
		applicationMainContext.setConfigLocation("pl.com.mnemonic.configuration");
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("JavaDispatcher",
				new DispatcherServlet(applicationMainContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
	}
}
