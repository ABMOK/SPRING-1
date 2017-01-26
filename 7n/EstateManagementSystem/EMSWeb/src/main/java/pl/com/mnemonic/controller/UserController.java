package pl.com.mnemonic.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.com.mnemonic.ems.classified.service.interfaces.UserServiceInterface;
import pl.com.mnemonic.ems.commons.dto.AdministrationDto;
import pl.com.mnemonic.ems.commons.dto.FacilityDto;
import pl.com.mnemonic.ems.commons.enums.SystemUserType;
import pl.com.mnemonic.ems.service.interfaces.EstateServiceInterface;

import javax.validation.Valid;
import java.util.List;



@Controller
@RequestMapping(value = {EMSURIConstants.ADMINISTRATION})
public class UserController {
	static final Logger LOGGER = Logger.getLogger(UserController.class);

	@Autowired
	UserServiceInterface systemUserService;
	@Autowired
	EstateServiceInterface estateService;

	/**
	 * Calls service to prepare list of all system users registered
	 *
	 * @return users list view definition
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView userListModel() {
		LOGGER.debug("userListModel");
		ModelAndView view = new ModelAndView("administration");
		AdministrationDto admin = systemUserService.getAllSystemUsers();
		view.addObject("admin", admin);
		return view;
	}

	/**
	 * Calls service to preapare empty system user object
	 *
	 * @return user view definition
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = {EMSURIConstants.NEW_SYSTEM_USER}, method = RequestMethod.GET)
	public ModelAndView newUser(Model model) {
		ModelAndView view = new ModelAndView("newuser");
		if(!model.containsAttribute("admin")){
			view.addObject("admin", new AdministrationDto());
		} else {
			view.addObject(model.asMap().get("admin"));
		}
		List<FacilityDto> facilities = estateService.getFacilitiesAsDtos();
		view.addObject("facilities", facilities);
		view.addObject("usageTypes", SystemUserType.values());
		return view;
	}

	/**
	 * If user data properly filled, processes them to service
	 * else redirects back to view
	 *
	 * @return redirect to users list view definition
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = {EMSURIConstants.NEW_SYSTEM_USER}, method = RequestMethod.POST)
	public String newUserSave(@ModelAttribute("admin") @Valid AdministrationDto adminDto,
							  BindingResult result, RedirectAttributes redirectAttributes) {
		if(result.hasErrors() || placeuserHasNoPlaceSpecified(adminDto)){
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.admin", result);
			redirectAttributes.addFlashAttribute("admin", adminDto);
			return "redirect:/administration"+EMSURIConstants.NEW_SYSTEM_USER;
		}
		systemUserService.saveNewUser(adminDto);
		return "redirect:/administration";

	}

	/**
	 * Calls service to prepare existing system user object
	 *
	 * @return user view definition
	 */
	@RequestMapping(value = {EMSURIConstants.USER}, method = RequestMethod.GET)
	public ModelAndView editUser(@PathVariable("idu") Integer idu, Model model) {
		ModelAndView view = new ModelAndView("newuser");
		if(!model.containsAttribute("admin")){
			AdministrationDto admin = systemUserService.getUser(idu);
			view.addObject("admin", admin);
		} else {
			view.addObject(model.asMap().get("admin"));
		}
		List<FacilityDto> facilities = estateService.getFacilitiesAsDtos();
		view.addObject("facilities", facilities);
		view.addObject("usageTypes", SystemUserType.values());
		return view;
	}

	/**
	 * If user data properly filled, processes them to service
	 * else redirects back to view
	 *
	 * @return redirect to users list view definition
	 */
	@RequestMapping(value = {EMSURIConstants.USER}, method = RequestMethod.POST)
	public String userEditSave(@PathVariable("idu") Integer idu, @ModelAttribute("admin") @Valid AdministrationDto adminDto,
							  BindingResult result, RedirectAttributes redirectAttributes) {
		if(result.hasErrors() || placeuserHasNoPlaceSpecified(adminDto)){
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.admin", result);
			redirectAttributes.addFlashAttribute("admin", adminDto);
			return "redirect:/administration"+EMSURIConstants.USER;
		}
		systemUserService.saveNewUser(adminDto);
		return "redirect:/administration";

	}

	/**
	 * Processes user delete request to service
	 *
	 * @return redirect to places list view definition
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = {EMSURIConstants.DELETE_USER}, method = RequestMethod.GET)
    public String delete(@PathVariable("idu") Integer idu) {
        LOGGER.info("Deleting user: " + idu);
		systemUserService.deleteUser(idu);
        return "redirect:"+EMSURIConstants.ADMINISTRATION;
    }

	/**
	 * validates if place user has place chosen within ui
	 * @param adminDto
	 * @return
     */
	private boolean placeuserHasNoPlaceSpecified(AdministrationDto adminDto){
		if(adminDto!=null
				&& adminDto.getUserDto()!=null
				&& adminDto.getUserDto().getMyRoles()!=null
				&& !adminDto.getUserDto().getMyRoles().isEmpty()
				&& (adminDto.getUserDto().getMyRoles().contains(SystemUserType.RENTER)
				|| adminDto.getUserDto().getMyRoles().contains(SystemUserType.OWNER))
				&& (adminDto.getPlaceDto() == null || adminDto.getPlaceDto().getPlaceid()==null)){
			return true;
		}
		return false;
	}
}
