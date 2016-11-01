package pl.com.mnemonic.configuration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by mnemonic on 2016-10-31.
 */

@WebListener("application context listener")
public class ContextListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("I N I T I A L I Z E D");
	}

	public void contextDestroyed(ServletContextEvent sce) {

	}
}
