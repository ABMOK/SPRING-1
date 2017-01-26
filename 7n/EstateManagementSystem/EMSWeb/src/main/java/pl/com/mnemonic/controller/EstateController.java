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
import pl.com.mnemonic.ems.commons.dto.FacilityDto;
import pl.com.mnemonic.ems.commons.enums.CommonExpenseType;
import pl.com.mnemonic.ems.commons.enums.FacilityType;
import pl.com.mnemonic.ems.commons.enums.MediaType;
import pl.com.mnemonic.ems.service.interfaces.EstateServiceInterface;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = {EMSURIConstants.ESTATES})
public class EstateController {
    static final Logger LOGGER = Logger.getLogger(EstateController.class);
    private static String IGNORED_FIELD = "addressDto.officeNo";

    @Autowired
    EstateServiceInterface estateService;

    /**
     * Prepares list of registered estates
     *
     * @return estates view definition
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView estates() {
        LOGGER.info("Calling Estates Controller");
        ModelAndView view = new ModelAndView("estates");
        List<FacilityDto> facilities = estateService.getFacilitiesAsDtos();
        view.addObject("facilities", facilities);
        return view;
    }

    /**
     * Prepares facility object to be edited
     *
     * @return estate edit view definition
     */
    @RequestMapping(value = {EMSURIConstants.ESTATE}, method = RequestMethod.GET)
    public ModelAndView estate(@PathVariable("ide") Integer ide, Model model) {
        LOGGER.info("Calling Estates Controller estate by id");
        ModelAndView view = new ModelAndView("estate");
        if (!model.containsAttribute("facility")) {
            FacilityDto facility = estateService.getFacilityDtoById(ide);
            if(facility!=null){
                view.addObject("facility", facility);
            } else throw new NullPointerException();

        } else {
            view.addObject(model.asMap().get("facility"));
            if (model.asMap().get("possibleDuplicates") != null) {
                view.addObject(model.asMap().get("possibleDuplicates"));
            }
        }
        view.addObject("facilityTypes", FacilityType.values());
        view.addObject("mediaTypes", MediaType.values());
        view.addObject("expenseTypes", CommonExpenseType.values());
        return view;
    }

    /**
     * If Estate data properly filled, processes them to service
     * else redirects back to view
     *
     * @return redirect to estate view definition
     */
    @RequestMapping(value = {EMSURIConstants.ESTATE}, method = RequestMethod.POST)
    public String editedEstateSave(@ModelAttribute("facility") @Valid FacilityDto facilityDto,
                                   BindingResult result, RedirectAttributes redirectAttributes) {
        LOGGER.info("Estates Controller - new Estate Save");
        List<FacilityDto> possibleDuplicates = estateService.validateDuplicateFacility(facilityDto);
        if (result.hasErrors() && !IGNORED_FIELD.equals(result.getFieldError().getField()) || (possibleDuplicates != null && !possibleDuplicates.isEmpty())) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.facility", result);
            redirectAttributes.addFlashAttribute("facility", facilityDto);
            if (possibleDuplicates != null && !possibleDuplicates.isEmpty()) {
                redirectAttributes.addFlashAttribute("possibleDuplicates", possibleDuplicates);
            }
            return "redirect:" + EMSURIConstants.ESTATES + EMSURIConstants.ESTATE;
        }
        estateService.addFacility(facilityDto);
        return "redirect:" + EMSURIConstants.ESTATES;
    }

    /**
     * Prepares facility object to be edited
     *
     * @return clear estate view definition
     */
    @RequestMapping(value = {EMSURIConstants.NEW_ESTATE}, method = RequestMethod.GET)
    public ModelAndView newEstate(Model model) {
        LOGGER.info("Estates Controller - new Estate");
        ModelAndView view = new ModelAndView("estate");
        if (!model.containsAttribute("facility")) {
            view.addObject("facility", new FacilityDto());
        } else {
            view.addObject(model.asMap().get("facility"));
            if (model.asMap().get("possibleDuplicates") != null) {
                view.addObject(model.asMap().get("possibleDuplicates"));
            }
        }
        view.addObject("facilityTypes", FacilityType.values());
        view.addObject("mediaTypes", MediaType.values());
        view.addObject("expenseTypes", CommonExpenseType.values());
        return view;
    }

    /**
     * If Estate data properly filled, processes them to service
     * else redirects back to view
     *
     * @return redirect to estates view definition
     */
    @RequestMapping(value = {EMSURIConstants.NEW_ESTATE}, method = RequestMethod.POST)
    public String newEstateSave(@ModelAttribute("facility") @Valid FacilityDto facilityDto,
                                BindingResult result, RedirectAttributes redirectAttributes) {
        LOGGER.info("Estates Controller - new Estate Save");
        List<FacilityDto> possibleDuplicates = estateService.validateDuplicateFacility(facilityDto);
        if (result.hasErrors() && !IGNORED_FIELD.equals(result.getFieldError().getField())
                || possibleDuplicates != null && !possibleDuplicates.isEmpty()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.facility", result);
            redirectAttributes.addFlashAttribute("facility", facilityDto);
            if (possibleDuplicates != null && !possibleDuplicates.isEmpty()) {
                redirectAttributes.addFlashAttribute("possibleDuplicates", possibleDuplicates);
            }
            return "redirect:" + EMSURIConstants.ESTATES + EMSURIConstants.NEW_ESTATE;
        } else {
            estateService.addFacility(facilityDto);
            return "redirect:" + EMSURIConstants.ESTATES;
        }
    }

    /**
     * Processes Estate delete request to service
     *
     * @return redirect to welcome view definition
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = {EMSURIConstants.DELETE_ESTATE}, method = RequestMethod.GET)
    public String deleteEstate(@PathVariable("ide") Integer ide) {
        LOGGER.info("Estates Controller - new Estate Save");
        estateService.deleteEstate(ide);
        return "redirect:" + EMSURIConstants.ESTATES;

    }
}
