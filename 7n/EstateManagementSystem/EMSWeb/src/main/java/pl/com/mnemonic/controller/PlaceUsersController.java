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
import pl.com.mnemonic.ems.commons.dto.PlaceUserDto;
import pl.com.mnemonic.ems.commons.enums.PlaceUserType;
import pl.com.mnemonic.ems.service.interfaces.FinanceServiceInterface;
import pl.com.mnemonic.ems.service.interfaces.PlaceServiceInterface;
import pl.com.mnemonic.ems.service.interfaces.TenantsServiceInterface;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = {EMSURIConstants.ALL_TENANTS})
public class PlaceUsersController {
    static final Logger LOGGER = Logger.getLogger(PlaceUsersController.class);
    @Autowired
    TenantsServiceInterface tenantService;
    @Autowired
    FinanceServiceInterface financeService;
    @Autowired
    PlaceServiceInterface placeService;

    private static String IGNORED_FIELD = "userDto.myRoles";

    /**
     * Calls service to prepare list of all tenants registered
     *
     * @return tenants list view definition
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView tenants() {
        LOGGER.info("Calling TENANTS Controller");
        ModelAndView view = new ModelAndView("tenants");
        List<PlaceUserDto> tenants = tenantService.getAllTenantsAsDto();
        view.addObject("tenants", tenants);
        return view;
    }

    /**
     * Calls service to prepare list of all owners registered
     *
     * @return tenants list view definition
     */
    @RequestMapping(value = {EMSURIConstants.ALL_PLACEOWNERS}, method = RequestMethod.GET)
    public ModelAndView allOwners() {
        LOGGER.info("Calling TENANTS Controller");
        ModelAndView view = new ModelAndView("tenants");
        List<PlaceUserDto> tenants = tenantService.getAllPlaceOwnersAsDto();
        view.addObject("tenants", tenants);
        return view;
    }

    /**
     * Calls service to prepare list of all renters registered
     *
     * @return tenants list view definition
     */
    @RequestMapping(value = {EMSURIConstants.ALL_PLACERENTERS}, method = RequestMethod.GET)
    public ModelAndView allRenters() {
        LOGGER.info("Calling TENANTS Controller");
        ModelAndView view = new ModelAndView("tenants");
        List<PlaceUserDto> tenants = tenantService.getAllPlaceRentersAsDto();
        view.addObject("tenants", tenants);
        return view;
    }

    /**
     * Calls service to preapare list of all owners of one specified place
     *
     * @return tenants list view definition
     */
    @RequestMapping(value = {EMSURIConstants.PLACE_OWNERS}, method = RequestMethod.GET)
    public ModelAndView placeOwners(@PathVariable("ide") Integer ide, @PathVariable("idp") Integer idp) {
        LOGGER.info("Calling Places Controller - place owners list");
        ModelAndView view = new ModelAndView("tenants");
        List<PlaceUserDto> tenants = tenantService.getAllOwnersAsDto(ide, idp);
        PlaceUserDto currentOwner = tenantService.getCurrentPlaceOwner(ide, idp);
        view.addObject("tenants", tenants);
        view.addObject("owner", currentOwner);
        return view;
    }

    /**
     * Calls service to preapare empty tenant object with place id
     *
     * @return tenant view definition
     */
    @RequestMapping(value = {EMSURIConstants.NEW_PLACEOWNER}, method = RequestMethod.GET)
    public ModelAndView newOwner(@PathVariable("ide") Integer ide, @PathVariable("idp") Integer idp, Model model) {
        LOGGER.info("Calling TENANTS Controller - new owner");
        ModelAndView view = new ModelAndView("tenant");
        if (!model.containsAttribute("tenant")) {
            view.addObject("tenant", tenantService.newPlaceUser(ide, idp, PlaceUserType.OWNER));
        } else {
            view.addObject(model.asMap().get("tenant"));
        }
        view.addObject("usageTypes", PlaceUserType.values());
        return view;
    }

    /**
     * If tenant data properly filled, processes them to service
     * else redirects back to view
     *
     * @return redirect to owners list view definition
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @RequestMapping(value = {EMSURIConstants.NEW_PLACEOWNER}, method = RequestMethod.POST)
    public String newOwnerSave(@PathVariable("ide") Integer ide, @PathVariable("idp") Integer idp,
                               @ModelAttribute("tenant") @Valid PlaceUserDto dto, BindingResult result,
                               RedirectAttributes redirectAttributes) {
        LOGGER.info("Calling TENANTS Controller - new Tenant save");
        if (result.hasErrors() && !IGNORED_FIELD.equals(result.getFieldError().getField())) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.tenant", result);
            redirectAttributes.addFlashAttribute("tenant", dto);
            return "redirect:" + EMSURIConstants.ALL_TENANTS + EMSURIConstants.NEW_PLACEOWNER;
        }
        tenantService.saveTenantToPlace(dto, ide, idp);
        return "redirect:" + EMSURIConstants.ALL_TENANTS + EMSURIConstants.ALL_PLACEOWNERS;
    }

    /**
     * Calls service to preapare list of all renters of one specified place
     *
     * @return tenants list view definition
     */
    @RequestMapping(value = {EMSURIConstants.PLACE_RENTERS}, method = RequestMethod.GET)
    public ModelAndView placeRenters(@PathVariable("ide") Integer ide, @PathVariable("idp") Integer idp) {
        LOGGER.info("Calling Places Controller - place renters list");
        ModelAndView view = new ModelAndView("tenants");
        List<PlaceUserDto> tenants = tenantService.getAllRentersAsDto(ide, idp);
        view.addObject("tenants", tenants);
        return view;
    }

    /**
     * Calls service to prepare empty tenant object with place id
     *
     * @return tenant view definition
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @RequestMapping(value = {EMSURIConstants.NEW_PLACERENTER}, method = RequestMethod.GET)
    public ModelAndView newRenter(@PathVariable("ide") Integer ide, @PathVariable("idp") Integer idp, Model model) {
        LOGGER.info("Calling TENANTS Controller - new renter");
        ModelAndView view = new ModelAndView("tenant");
        if (!model.containsAttribute("tenant")) {
            view.addObject("tenant", tenantService.newPlaceUser(ide, idp, PlaceUserType.RENTER));
        } else {
            view.addObject(model.asMap().get("tenant"));
        }
        view.addObject("usageTypes", PlaceUserType.values());
        return view;
    }

    /**
     * If tenant data properly filled, processes them to service
     * else redirects back to view
     *
     * @return redirect to tenants view definition
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @RequestMapping(value = {EMSURIConstants.NEW_PLACERENTER}, method = RequestMethod.POST)
    public String newRenterSave(@PathVariable("ide") Integer ide, @PathVariable("idp") Integer idp,
                                @ModelAttribute("tenant") @Valid PlaceUserDto dto, BindingResult result,
                                RedirectAttributes redirectAttributes) {
        LOGGER.info("Calling TENANTS Controller - new renter save");
        if (result.hasErrors() && !IGNORED_FIELD.equals(result.getFieldError().getField())) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.tenant", result);
            redirectAttributes.addFlashAttribute("tenant", dto);
            return "redirect:" + EMSURIConstants.ALL_TENANTS + EMSURIConstants.NEW_PLACERENTER;
        }
        tenantService.saveTenantToPlace(dto, ide, idp);
        return "redirect:" + EMSURIConstants.ALL_TENANTS;
    }

    /**
     * Calls service to prepare tenant object with specified tenant id and place id
     *
     * @return tenant view definition
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @RequestMapping(value = {EMSURIConstants.PLACERENTER, EMSURIConstants.PLACEOWNER}, method = RequestMethod.GET)
    public ModelAndView placeuserEdit(@PathVariable("ide") Integer ide, @PathVariable("idp") Integer idp,
                                      @PathVariable("idt") Integer idt, Model model) {
        LOGGER.info("Calling TENANTS Controller TENANT by id");
        ModelAndView view = new ModelAndView("tenant");
        if (!model.containsAttribute("tenant")) {
            PlaceUserDto tenant = tenantService.getTenantDtoById(idt, ide, idp);
            view.addObject("tenant", tenant);
        } else {
            view.addObject(model.asMap().get("tenant"));
        }
        view.addObject("usageTypes", PlaceUserType.values());

        return view;
    }


    /**
     * If tenant data properly filled, processes them to service
     * else redirects back to view
     *
     * @return redirect to tenants view definition
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @RequestMapping(value = {EMSURIConstants.PLACERENTER, EMSURIConstants.PLACEOWNER}, method = RequestMethod.POST)
    public String placeuserUpdate(@PathVariable("idt") Integer idt, @ModelAttribute("tenant") @Valid PlaceUserDto dto,
                                  BindingResult result, RedirectAttributes redirectAttributes) {
        LOGGER.info("Calling TENANTS Controller update TENANT by id");
        PlaceUserDto placeUserDto = tenantService.getTenantDtoById(idt);
        if (result.hasErrors() && !IGNORED_FIELD.equals(result.getFieldError().getField())) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.tenant", result);
            redirectAttributes.addFlashAttribute("tenant", dto);
            if (PlaceUserType.OWNER.name().equals(placeUserDto.getUsageType())) {
                return "redirect:" + EMSURIConstants.ALL_TENANTS + EMSURIConstants.PLACEOWNER;
            }
            if (PlaceUserType.RENTER.name().equals(placeUserDto.getUsageType())) {
                return "redirect:" + EMSURIConstants.ALL_TENANTS + EMSURIConstants.PLACERENTER;
            }
        }
        tenantService.updateTenant(dto, idt);
        return "redirect:" + EMSURIConstants.ALL_TENANTS;
    }

    /**
     * Processes tenant delete request to service
     *
     * @return redirect to places list view definition
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = {EMSURIConstants.DELETE_TENANT}, method = RequestMethod.GET)
    public String deleteTenant(@PathVariable("idt") Integer idt) {
        LOGGER.info("Estates Controller - new Estate Save");
        tenantService.deletePlaceUser(idt);
        return "redirect:" + EMSURIConstants.ALL_TENANTS;

    }
}
