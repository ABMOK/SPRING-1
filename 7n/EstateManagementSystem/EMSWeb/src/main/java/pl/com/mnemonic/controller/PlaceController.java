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
import pl.com.mnemonic.ems.commons.dto.PlaceDto;
import pl.com.mnemonic.ems.service.interfaces.EstateServiceInterface;
import pl.com.mnemonic.ems.service.interfaces.PlaceServiceInterface;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = {EMSURIConstants.ALL_PLACES, EMSURIConstants.PLACES})
public class PlaceController {
    static final Logger LOGGER = Logger.getLogger(PlaceController.class);

    @Autowired
    EstateServiceInterface estateService;
    @Autowired
    PlaceServiceInterface placeService;


    /**
     * Calls service to preapare list of all places registered
     *
     * @return places list view definition
     */
    @RequestMapping(value = {EMSURIConstants.ALL_PLACES_LIST}, method = RequestMethod.GET)
    public ModelAndView allPlaces() {
        LOGGER.info("Calling Estates Controller - all places list");
        ModelAndView view = new ModelAndView("places");
        List<PlaceDto> places = placeService.getAllPlacesAsDtos();
        view.addObject("places", places);
        return view;
    }

    /**
     * Calls service to preapare list of all places of one specified facility
     *
     * @return places list view definition
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView places(@PathVariable("ide") Integer ide) {
        LOGGER.info("Calling Estates Controller - places list");
        ModelAndView view = new ModelAndView("places");
        List<PlaceDto> places = estateService.getFacilityDtoById(ide).getPlaces();
        view.addObject("places", places);
        return view;
    }

    /**
     * Calls service to preapare empty place object with estate id
     *
     * @return places list view definition
     */
    @RequestMapping(value = {EMSURIConstants.NEW_PLACE}, method = RequestMethod.GET)
    public ModelAndView newPlace(@PathVariable("ide") Integer ide, Model model) {
        LOGGER.info("Estates Controller - new Place");
        ModelAndView view = new ModelAndView("place");
        if (!model.containsAttribute("place")) {
            view.addObject("place", placeService.getPurePlaceDtoByFacilityId(ide));
        } else {
            view.addObject(model.asMap().get("place"));
            if (model.asMap().get("possibleDuplicates") != null) {
                view.addObject(model.asMap().get("possibleDuplicates"));
            }
        }
        return view;
    }

    /**
     * If Place data properly filled, processes them to service
     * else redirects back to view
     *
     * @return redirect to place view definition
     */
    @RequestMapping(value = {EMSURIConstants.NEW_PLACE}, method = RequestMethod.POST)
    public String newPlaceSave(@PathVariable("ide") Integer ide, @ModelAttribute("place") @Valid PlaceDto dto,
                               BindingResult result, RedirectAttributes redirectAttributes) {
        LOGGER.info("Estates Controller - new Place Save");
        List<PlaceDto> duplicatePlaces = placeService.validateDuplicatePlace(ide, dto);
        if (result.hasErrors() || !duplicatePlaces.isEmpty()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.place", result);
            redirectAttributes.addFlashAttribute("place", dto);
            redirectAttributes.addFlashAttribute("possibleDuplicates", duplicatePlaces);

            return "redirect:" + EMSURIConstants.PLACES + EMSURIConstants.NEW_PLACE;
        }
        placeService.prepareFacilityPlacesUpdate(dto, ide);
        return "redirect:" + EMSURIConstants.PLACES;
    }

    /**
     * calls service to prepare place object for edit for specified place id
     *
     * @return place view definition
     */
    @RequestMapping(value = {EMSURIConstants.PLACE}, method = RequestMethod.GET)
    public ModelAndView place(@PathVariable("ide") Integer ide, @PathVariable("idp") Integer idp, Model model) {
        LOGGER.info("Calling Places Controller place by idestate, idplace");
        ModelAndView view = new ModelAndView("place");
        if (!model.containsAttribute("place")) {
            PlaceDto placeDto = placeService.getPlaceDtoByFacilityAndPlaceIds(ide, idp);
            view.addObject("place", placeDto);
        } else {
            view.addObject(model.asMap().get("place"));
            if (model.asMap().get("possibleDuplicates") != null) {
                view.addObject(model.asMap().get("possibleDuplicates"));
            }
        }
        return view;
    }

    /**
     * If Place data properly filled, processes them to service
     * else redirects back to view
     *
     * @return redirect to places view definition
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @RequestMapping(value = {EMSURIConstants.PLACE}, method = RequestMethod.POST)
    public String updatePlace(@PathVariable("ide") Integer ide, @PathVariable("idp") Integer idp,
                              @ModelAttribute("place") @Valid PlaceDto dto,
                              BindingResult result, RedirectAttributes redirectAttributes) {
        LOGGER.info("Calling Places Controller place by idestate, idplace");
        List<PlaceDto> duplicatePlaces = placeService.validateDuplicatePlace(ide, dto);
        if (result.hasErrors() || !duplicatePlaces.isEmpty()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.place", result);
            redirectAttributes.addFlashAttribute("place", dto);
            redirectAttributes.addFlashAttribute("possibleDuplicates", duplicatePlaces);
            return "redirect:" + EMSURIConstants.PLACES + EMSURIConstants.PLACE;
        }
        placeService.prepareFacilityPlacesUpdate(dto, ide);
        return "redirect:" + EMSURIConstants.PLACES;
    }

    /**
     * Processes Place delete request to service
     *
     * @return redirect to places list view definition
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = {EMSURIConstants.DELETE_PLACE}, method = RequestMethod.GET)
    public String deletePlace(@PathVariable("ide") Integer ide, @PathVariable("idp") Integer idp) {
        LOGGER.info("Estates Controller - new Place delete");
        placeService.deletePlace(ide, idp);
        return "redirect:" + EMSURIConstants.ALL_PLACES + EMSURIConstants.ALL_PLACES_LIST;
    }
}