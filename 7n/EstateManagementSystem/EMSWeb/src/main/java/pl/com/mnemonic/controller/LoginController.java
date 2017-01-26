package pl.com.mnemonic.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.com.mnemonic.ems.commons.dto.AddressDto;
import pl.com.mnemonic.ems.commons.dto.RegistrationDto;
import pl.com.mnemonic.ems.commons.dto.UserDto;
import pl.com.mnemonic.ems.dao.interfaces.UserDaoInterface;
import pl.com.mnemonic.ems.entity.User;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class LoginController {
	static final Logger logger = Logger.getLogger(LoginController.class);
	@Autowired
	private UserDaoInterface userDao;

	/**
	 * Prepares login view
	 * @return login view definition
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET )
    public ModelAndView login(){
		ModelAndView view = new ModelAndView("login");
    	logger.info("calling login controller");
        return view;
    }
	
    
	//PUBLIC REGISTER
	
	@RequestMapping(value = {"/register"}, method = RequestMethod.GET)
	public ModelAndView newUserRegistration() {
		ModelAndView view = new ModelAndView("registrationform");
		//User user = new User();
		//view.addObject("user", user);
		RegistrationDto register = new RegistrationDto();
		register.setUser(new UserDto());
		register.getUser().setRegdate(new Date());
		register.setAddress(new AddressDto());
		view.addObject("register", register);
		return view;

	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String newUserRegistrationPost(@ModelAttribute("register") @Valid RegistrationDto register,
			BindingResult result) {
		if (result.hasErrors()) {
            return "registrationform";
       } else {
		return "redirect:/login";
       }

	}
	
	@RequestMapping(value = { "/public" }, method = RequestMethod.GET)
	public ModelAndView userList() {
		logger.debug("userlist");
		ModelAndView view = new ModelAndView("public");
		List<User>users = new ArrayList<User>();
		try {
			users = userDao.getUsers();
		} catch (Exception ex) {
			logger.info(ex);
		} finally {
			if (users != null) {
				view.addObject("users", users);
			} else {
				users = new ArrayList<User>();
				view.addObject("users", users);
			}
		}
		return view;
	}
	
	
}
