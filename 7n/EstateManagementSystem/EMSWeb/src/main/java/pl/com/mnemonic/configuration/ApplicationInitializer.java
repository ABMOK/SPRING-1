package pl.com.mnemonic.configuration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;


public class ApplicationInitializer implements WebApplicationInitializer{

	/**
	 * Describes application behaviour on startup,
	 * points Configuration location, defines servlet dispatcher
	 * @param servletContext interface ServletContext
	 * @throws ServletException
     */
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext applicationMainContext = new AnnotationConfigWebApplicationContext();
		applicationMainContext.setConfigLocation("pl.com.mnemonic.configuration");
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("JavaDispatcher", new DispatcherServlet(applicationMainContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");	

	}


}
