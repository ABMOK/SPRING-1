package pl.com.mnemonic.configuration;

import org.apache.log4j.BasicConfigurator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener("application context listener")
public class ContextListener implements ServletContextListener {

	/**
	 * Allows to specify application  behaviour after start
	 * @param event event to use if needed
     */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		BasicConfigurator.configure();
	}

	/**
	 * Allows to specify application  behaviour after end
	 * @param sce event to use if needed
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}
	

}
