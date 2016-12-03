package pl.com.mnemonic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.com.mnemonic.dto.UserDto;
import pl.com.mnemonic.dto.UserRole;
import pl.com.mnemonic.services.interfaces.SpringRoleManagerInterface;

@Controller
@RequestMapping(value = { RoleManagerUriConstants.ADMINISTRATION })
public class UserController {

	@Autowired
	SpringRoleManagerInterface roleManager;

	@RequestMapping(value = { RoleManagerUriConstants.USERS }, method = RequestMethod.GET)
	public ModelAndView userList() {		
		return prepareModelAndView(null);
	}

	@RequestMapping(value = { RoleManagerUriConstants.USERS }, method = RequestMethod.POST)
	public String newUserSave(@ModelAttribute("user") UserDto userDto) {
		roleManager.saveUser(userDto);
		return "redirect:" + RoleManagerUriConstants.ADMINISTRATION + RoleManagerUriConstants.USERS;
	}
	
	@RequestMapping(value = { RoleManagerUriConstants.DELETE_USER }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable("idu") Integer id) {
		roleManager.deleteUser(id);
		return "redirect:" + RoleManagerUriConstants.ADMINISTRATION + RoleManagerUriConstants.USERS;
	}
	
	@RequestMapping(value = { RoleManagerUriConstants.UPDATE_USER }, method = RequestMethod.GET)
	public ModelAndView updateUser(@PathVariable("idu") Integer id) {		
		UserDto user = roleManager.readUser(id);
		return prepareModelAndView(user);
	}
	
	@RequestMapping(value = { RoleManagerUriConstants.UPDATE_USER }, method = RequestMethod.POST)
	public String updateUserPost(@ModelAttribute("user") UserDto userDto) {		
		return newUserSave(userDto);
	}
	
	private ModelAndView prepareModelAndView(UserDto user){
		
		ModelAndView view = new ModelAndView("users");
		view.addObject("users", roleManager.getAllUsers());
		
		if(user!=null){
			view.addObject("user", user);
		} else {
			view.addObject("user", new UserDto());
		}		
		view.addObject("roles", UserRole.values());
		return view;
	}
}
