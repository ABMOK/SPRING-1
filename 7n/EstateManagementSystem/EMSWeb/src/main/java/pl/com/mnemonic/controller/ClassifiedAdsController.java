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
import pl.com.mnemonic.ems.classified.service.interfaces.ClassifiedAdsInterface;
import pl.com.mnemonic.ems.classified.service.interfaces.UserServiceInterface;
import pl.com.mnemonic.ems.commons.dto.ClassifiedComponentsDto;
import pl.com.mnemonic.ems.commons.enums.SystemUserType;

import javax.validation.Valid;

@Controller
@RequestMapping(value = {EMSURIConstants.CLASSIFIED})
public class ClassifiedAdsController {
    static final Logger LOGGER = Logger.getLogger(ClassifiedAdsController.class);

    @Autowired
    UserServiceInterface systemUserService;
    @Autowired
    ClassifiedAdsInterface classyService;

    /**
     * Prepares user aware component containing data required to view
     * @return welcome view definition
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView classifiedAds() {
        LOGGER.info("Calling Estates Controller");
        ModelAndView view = new ModelAndView("welcome");
        ClassifiedComponentsDto classy = classyService.getUserAwareComponent(systemUserService.getLoggedUserDto());
        view.addObject("classy", classy);
        return view;
    }

    /**
     * Prepares user aware component containing data required to view - ads categories
     * @return welcome view definition
     */
    @RequestMapping(value = {EMSURIConstants.CATEGORIES}, method = RequestMethod.GET)
    public ModelAndView categories() {
        ModelAndView view = new ModelAndView("welcome");
        ClassifiedComponentsDto classy = classyService.getUserAwareComponentCategories(systemUserService.getLoggedUserDto());
        view.addObject("classy", classy);
        return view;
    }

    /**
     * Prepares user aware component containing data required to view - ads for specified category
     * @return welcome view definition
     */
    @RequestMapping(value = {EMSURIConstants.CATEGORY}, method = RequestMethod.GET)
    public ModelAndView categoryAds(@PathVariable("idc") Integer idc) {
        ModelAndView view = new ModelAndView("welcome");
        ClassifiedComponentsDto classy = classyService.getUserAwareComponentCategoryAds(systemUserService.getLoggedUserDto(), idc);
        view.addObject("classy", classy);
        return view;
    }

    /**
     * Prepares user aware component containing empty ad template
     * @return welcome view definition
     */
    @RequestMapping(value = {EMSURIConstants.NEW_AD}, method = RequestMethod.GET)
    public ModelAndView newAd(Model model) {
        LOGGER.info("Calling ClassifiedAds Controller");
        ModelAndView view = new ModelAndView("ad");
        ClassifiedComponentsDto classy = classyService.getUserAwareComponentAd(systemUserService.getLoggedUserDto());
        if (!model.containsAttribute("classy")) {
            view.addObject("classy", classy);
        } else {
            ClassifiedComponentsDto edited = prepareEditedClassifiedComponent((ClassifiedComponentsDto) model.asMap().get("classy"), classy);
            view.addObject("classy", edited);
        }
        return view;
    }

    /**
     * If data properly filled, processes them to service
     * else redirects back to view
     * @return redirect to welcome view definition
     */
    @RequestMapping(value = {EMSURIConstants.NEW_AD}, method = RequestMethod.POST)
    public String newAdSave(@ModelAttribute("classy") @Valid ClassifiedComponentsDto classyDto,
                            BindingResult result, RedirectAttributes redirectAttributes) {
        LOGGER.info("Calling ClassifiedAds Controller");
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.classy", result);
            redirectAttributes.addFlashAttribute("classy", classyDto);
            return "redirect:" + EMSURIConstants.CLASSIFIED + EMSURIConstants.NEW_AD;
        }
        classyService.saveAdComponent(classyDto);
        return "redirect:" + EMSURIConstants.CLASSIFIED;
    }

    /**
     * Prepares requested ad view
     * @return welcome view definition
     */
    @RequestMapping(value = {EMSURIConstants.AD}, method = RequestMethod.GET)
    public ModelAndView ad(@PathVariable("idc") Integer idc, @PathVariable("idad") Integer idad) {
        ModelAndView view = new ModelAndView("welcome");
        ClassifiedComponentsDto classy = classyService.getUserAwareComponentAd(idc, idad);
        view.addObject("classy", classy);
        return view;
    }

    /**
     * Processes delete request to service
     * @return redirect to welcome view definition
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = {EMSURIConstants.AD_DELETE}, method = RequestMethod.GET)
    public String deleteAd(@PathVariable("idc") Integer idc, @PathVariable("idad") Integer idad) {
        classyService.deleteAd(idad);
        return "redirect:" + EMSURIConstants.CLASSIFIED;
    }

    /**
     * Prepares requested ad to be edited
     * @return welcome view definition
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = {EMSURIConstants.AD_EDIT}, method = RequestMethod.GET)
    public ModelAndView editAd(@PathVariable("idc") Integer idc,
                               @PathVariable("idad") Integer idad,
                               Model model) {
        ModelAndView view = new ModelAndView("ad");
        ClassifiedComponentsDto classy = classyService.getUserAwareComponentAd(systemUserService.getLoggedUserDto(), idc, idad);
        if (!model.containsAttribute("classy")) {
            view.addObject("classy", classy);
        } else {
            ClassifiedComponentsDto edited = prepareEditedClassifiedComponent((ClassifiedComponentsDto) model.asMap().get("classy"), classy);
            view.addObject("classy", edited);
        }

        return view;
    }

    /**
     * If edited ad data properly filled, processes them to service
     * else redirects back to view
     * @return redirect to welcome view definition
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = {EMSURIConstants.AD_EDIT}, method = RequestMethod.POST)
    public String postEditAd(@ModelAttribute("classy") @Valid ClassifiedComponentsDto classyDto,
                             BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.classy", result);
            redirectAttributes.addFlashAttribute("classy", classyDto);
            return "redirect:" + EMSURIConstants.CLASSIFIED + EMSURIConstants.AD_EDIT;
        }
        return newAdSave(classyDto, result, redirectAttributes);
    }

    /**
     * Prepares ClassifiedComponentsDto to work with data editing
     * @return administration view definition
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @RequestMapping(value = {EMSURIConstants.ADMINISTRATION}, method = RequestMethod.GET)
    public ModelAndView adsAdministration(Model model) {
        LOGGER.info("Calling ClassifiedAds Controller");
        ModelAndView view = new ModelAndView("adsadmin");
        ClassifiedComponentsDto classy = classyService.getUserAwareComponentAdministration(systemUserService.getLoggedUserDto());
        if (!model.containsAttribute("classy")) {
            view.addObject("classy", classy);
        } else {
            ClassifiedComponentsDto edited = prepareEditedClassifiedComponent((ClassifiedComponentsDto) model.asMap().get("classy"), classy);
            view.addObject("classy", edited);
        }
        view.addObject("usageTypes", SystemUserType.values());
        return view;
    }

    /**
     * If ClassifiedComponentsDto data properly filled, processes them to service
     * else redirects back to administration view definition
     * @return administration view definition
     */
    @RequestMapping(value = {EMSURIConstants.ADMINISTRATION}, method = RequestMethod.POST)
    public String adsAdministrationPost(@ModelAttribute("classy") @Valid ClassifiedComponentsDto classyDto,
                                        BindingResult result, RedirectAttributes redirectAttributes) {
        LOGGER.info("Calling ClassifiedAds Controller");
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.classy", result);
            redirectAttributes.addFlashAttribute("classy", classyDto);
            return "redirect:" + EMSURIConstants.CLASSIFIED + EMSURIConstants.ADMINISTRATION;
        }
        classyService.saveAdminComponent(classyDto);
        return "redirect:/classifiedads" + EMSURIConstants.ADMINISTRATION;
    }

    /**
     * Prepares ClassifiedComponentsDto to work with category editing
     * @return administration view definition
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @RequestMapping(value = {EMSURIConstants.EDIT_CATEGORY}, method = RequestMethod.GET)
    public ModelAndView editCategory(@PathVariable("idc") Integer idc, Model model) {
        LOGGER.info("Calling ClassifiedAds Controller");
        ModelAndView view = new ModelAndView("adsadmin");
        ClassifiedComponentsDto classy = classyService.getUserAwareComponentAdministration(systemUserService.getLoggedUserDto(), idc);
        if (!model.containsAttribute("classy")) {
            view.addObject("classy", classy);
        } else {
            ClassifiedComponentsDto edited = prepareEditedClassifiedComponent((ClassifiedComponentsDto) model.asMap().get("classy"), classy);
            view.addObject("classy", edited);
        }
        view.addObject("usageTypes", SystemUserType.values());
        return view;
    }

    /**
     * If ClassifiedComponentsDto Category properly filled, processes them to service
     * else redirects back to administration view definition
     * @return administration view definition
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @RequestMapping(value = {EMSURIConstants.EDIT_CATEGORY}, method = RequestMethod.POST)
    public String editCategoryPost(@ModelAttribute("classy") @Valid ClassifiedComponentsDto classyDto,
                                   BindingResult result, RedirectAttributes redirectAttributes) {
        LOGGER.info("Calling ClassifiedAds Controller");
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.classy", result);
            redirectAttributes.addFlashAttribute("classy", classyDto);
            return "redirect:" + EMSURIConstants.CLASSIFIED + EMSURIConstants.EDIT_CATEGORY;
        }
        classyService.saveAdminComponent(classyDto);
        return "redirect:/classifiedads" + EMSURIConstants.ADMINISTRATION;
    }

    /**
     * processes Category delete request to service
     * @return administration view definition
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = {EMSURIConstants.DELETE_CATEGORY}, method = RequestMethod.GET)
    public String deleteCategory(@PathVariable("idc") Integer idc) {
        LOGGER.info("Calling ClassifiedAds Controller");
        classyService.deleteCategory(idc);
        return "redirect:/classifiedads" + EMSURIConstants.ADMINISTRATION;
    }

    /**
     * processes Attribute delete request to service
     * @return administration view definition
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = {EMSURIConstants.DELETE_ATTRIBUTE}, method = RequestMethod.GET)
    public String deleteAttribute(@PathVariable("ida") Integer ida) {
        LOGGER.info("Calling ClassifiedAds Controller");
        classyService.deleteAttribute(ida);
        return "redirect:/classifiedads" + EMSURIConstants.ADMINISTRATION;
    }

    /**
     * Prepares edited object if data were inconsistent
     * @return ClassifiedComponentsDto with crucial informations
     */
    private ClassifiedComponentsDto prepareEditedClassifiedComponent(ClassifiedComponentsDto edited, ClassifiedComponentsDto source) {
        edited.setAllAttributes(source.getAllAttributes());
        edited.setCategoryList(source.getCategoryList());
        edited.setCategoryAttributes(source.getCategoryAttributes());
        edited.setClassyAdList(source.getClassyAdList());
        return edited;
    }

}
