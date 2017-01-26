package pl.com.mnemonic.controller;

import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EstatesApplicationController {
	static final Logger logger = Logger.getLogger(EstatesApplicationController.class);

	/**
	 * Handles first application request after login
	 * @return welcome view definition
	 */
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_OWNER','ROLE_RENTER')")
	@RequestMapping(value = {"/welcome","/index"})
	public ModelAndView welcome(){ 
		logger.info("Calling EstatesApplicationController");
		String msg = "Estates Application v. 1.0";
		ModelAndView view = new ModelAndView("welcome");
		view.addObject("msg",msg);
		return view;
		}

}
