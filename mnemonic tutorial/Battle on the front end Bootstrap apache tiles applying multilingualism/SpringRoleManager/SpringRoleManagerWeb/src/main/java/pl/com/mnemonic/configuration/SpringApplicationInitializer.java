package pl.com.mnemonic.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/**
	 * Enables Spring configuration
	 * @return
     */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {ContextConfiguration.class};
	}

	/**
	 * Enables Spring configuration
	 * @return
	 */
	@Override
	protected Class<?>[] getServletConfigClasses(){
		return null;
	}

	/**
	 * Enables Spring configuration
	 * @return
	 */
	@Override
	protected String[] getServletMappings(){
		return new String[] {"/"};
	}

}
