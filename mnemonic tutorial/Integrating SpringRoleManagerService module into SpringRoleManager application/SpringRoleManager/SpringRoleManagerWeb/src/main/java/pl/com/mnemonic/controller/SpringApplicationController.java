package pl.com.mnemonic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.com.mnemonic.services.interfaces.SpringRoleManagerInterface;

/**
 * Created by mnemonic on 2016-10-31.
 */
@Controller
public class SpringApplicationController {
	
	@Autowired
	SpringRoleManagerInterface roleManager;

	@RequestMapping(value = { "/index" }, method = RequestMethod.GET)
	public ModelAndView welcome() {
		ModelAndView view = new ModelAndView("welcome");
		view.addObject("msg", roleManager.welcome());
		return view;
	}
}
