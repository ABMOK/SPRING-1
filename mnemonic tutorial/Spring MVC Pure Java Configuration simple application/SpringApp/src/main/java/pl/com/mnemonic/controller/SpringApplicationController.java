package pl.com.mnemonic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by mnemonic on 2016-10-31.
 */
@Controller
public class SpringApplicationController {

	@RequestMapping(value = { "/index" }, method = RequestMethod.GET)
	public ModelAndView welcome() {
		String msg = "MESSAGE FROM CONTROLLER";
		ModelAndView view = new ModelAndView("welcome");
		view.addObject("msg", msg);
		return view;
	}
}
