package pl.com.mnemonic.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.com.mnemonic.ems.classified.service.interfaces.UserServiceInterface;
import pl.com.mnemonic.ems.commons.dto.*;
import pl.com.mnemonic.ems.commons.enums.CommonExpenseType;
import pl.com.mnemonic.ems.commons.enums.HappeningOccurence;
import pl.com.mnemonic.ems.commons.enums.HappeningType;
import pl.com.mnemonic.ems.commons.enums.MediaType;
import pl.com.mnemonic.ems.service.interfaces.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = {EMSURIConstants.ALL_FINANCES})
public class FinanceController {
    static final Logger LOGGER = Logger.getLogger(FinanceController.class);

    @Autowired
    FinanceServiceInterface financeService;
    @Autowired
    EstateServiceInterface estateService;
    @Autowired
    PlaceServiceInterface placeService;
    @Autowired
    MediaServiceInterface mediaService;
    @Autowired
    ExpenseServiceInterface expenseService;
    @Autowired
    UserServiceInterface systemUserService;
    @Autowired
    ReportServiceInterface reportService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView finances() {
        LOGGER.info("Calling finances Controller");
        ModelAndView view = new ModelAndView("finances");
        return view;
    }

    @RequestMapping(value = {EMSURIConstants.ALL_MEDIA}, method = RequestMethod.GET)
    public ModelAndView allMedia() {
        LOGGER.info("Calling Estates Controller - places list");
        ModelAndView view = new ModelAndView("finances");
        Map<FacilityDto, List<MediaDto>> mediaMap = mediaService.getAllMediaDefined();
        view.addObject("mediaMap", mediaMap);
        return view;
    }

    @RequestMapping(value = {EMSURIConstants.MEDIA}, method = RequestMethod.GET)
    public ModelAndView media(@PathVariable("ide") Integer ide) {
        LOGGER.info("Calling Estates Controller - places list");
        ModelAndView view = new ModelAndView("media");
        List<MediaDto> media = mediaService.getFacilityMediaAsDtos(ide);
        view.addObject("media", media);
        return view;
    }

    @RequestMapping(value = {EMSURIConstants.MEDIUM}, method = RequestMethod.GET)
    public ModelAndView getExistingMedium(@PathVariable("ide") Integer ide, @PathVariable("idm") Integer idm, Model model) {
        LOGGER.info("Estates Controller - Medium edit");
        ModelAndView view = new ModelAndView("medium");
        if (!model.containsAttribute("medium")) {
            view.addObject("medium", mediaService.getMediaDtoByFacilityAndMediumIds(ide, idm));
        } else {
            view.addObject(model.asMap().get("medium"));
        }
        view.addObject("mediaTypes", MediaType.values());
        return view;
    }

    @RequestMapping(value = {EMSURIConstants.MEDIUM}, method = RequestMethod.POST)
    public String updateExistingMedium(@PathVariable("ide") Integer ide, @PathVariable("idm") Integer idm,
                                       @ModelAttribute("medium") @Valid MediaDto dto, BindingResult result, RedirectAttributes redirectAttributes) {
        LOGGER.info("Estates Controller - medium update");
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.medium", result);
            redirectAttributes.addFlashAttribute("medium", dto);
            return "redirect:" + EMSURIConstants.ALL_FINANCES + EMSURIConstants.MEDIUM;
        }
        mediaService.prepareFacilityMediumUpdate(dto, ide, idm);
        return "redirect:" + EMSURIConstants.ALL_FINANCES + EMSURIConstants.MEDIA;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = {EMSURIConstants.MEDIUM_DELETE}, method = RequestMethod.GET)
    public String deleteExistingMedium(@PathVariable("ide") Integer ide, @PathVariable("idm") Integer idm) {
        LOGGER.info("Estates Controller - Medium delete");
        mediaService.deleteMedium(idm);
        return "redirect:" + EMSURIConstants.ALL_FINANCES + EMSURIConstants.MEDIA;
    }

    @RequestMapping(value = {EMSURIConstants.NEW_MEDIUM}, method = RequestMethod.GET)
    public ModelAndView newMedium(@PathVariable("ide") Integer ide, Model model) {
        LOGGER.info("Estates Controller - new Medium");
        ModelAndView view = new ModelAndView("medium");
        if (!model.containsAttribute("medium")) {
            view.addObject("medium", mediaService.getNewMediaDtoByFacilityId(ide));
        } else {
            view.addObject(model.asMap().get("medium"));
        }
        view.addObject("mediaTypes", MediaType.values());
        return view;
    }

    @RequestMapping(value = {EMSURIConstants.NEW_MEDIUM}, method = RequestMethod.POST)
    public String newMediumSave(@PathVariable("ide") Integer ide,
                                @ModelAttribute("medium") @Valid MediaDto dto,
                                BindingResult result,
                                RedirectAttributes redirectAttributes) {
        LOGGER.info("Estates Controller - new Medium Save");
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.medium", result);
            redirectAttributes.addFlashAttribute("medium", dto);
            return "redirect:" + EMSURIConstants.ALL_FINANCES + EMSURIConstants.NEW_MEDIUM;
        }
        mediaService.addNewMedium(dto, ide);
        return "redirect:" + EMSURIConstants.ALL_FINANCES + EMSURIConstants.MEDIA;
    }


    @RequestMapping(value = {EMSURIConstants.ALL_EXPENSES}, method = {RequestMethod.GET})
    public ModelAndView allExpenses() {
        LOGGER.info("Calling finances Controller - expenses");
        ModelAndView view = new ModelAndView("finances");
        Map<FacilityDto, List<ExpenseDto>> allExpenses = expenseService.getAllExpensesDefined();
        view.addObject("allExpenses", allExpenses);
        return view;
    }


    @RequestMapping(value = {EMSURIConstants.EXPENSES}, method = {RequestMethod.GET})
    public ModelAndView facilityExpenses(@PathVariable("ide") Integer ide) {
        LOGGER.info("Calling finances Controller - expenses");
        ModelAndView view = new ModelAndView("expenses");
        List<ExpenseDto> commonExpenses = expenseService.getCommonExpensesList(ide);
        view.addObject("commonExpenses", commonExpenses);
        return view;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @RequestMapping(value = {EMSURIConstants.EXPENSE}, method = {RequestMethod.GET})
    public ModelAndView getFacilityExpense(@PathVariable("ide") Integer ide,
                                           @PathVariable("idexp") Integer idexp,
                                           Model model) {
        LOGGER.info("Calling finances Controller - expenses");
        ModelAndView view = new ModelAndView("expense");
        if (!model.containsAttribute("expenseDto")) {
            ExpenseDto expenseDto = expenseService.getExpenseDto(ide, idexp);
            view.addObject("expenseDto", expenseDto);
        } else {
            view.addObject(model.asMap().get("expenseDto"));
        }
        view.addObject("expenseTypes", CommonExpenseType.values());
        view.addObject("happeningTypes", HappeningType.values());
        view.addObject("happeningOccurence", HappeningOccurence.values());
        return view;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @RequestMapping(value = {EMSURIConstants.EXPENSE}, method = {RequestMethod.POST})
    public String facilityExpenseUpdate(@PathVariable("ide") Integer ide,
                                        @PathVariable("idexp") Integer idexp,
                                        @ModelAttribute("expense") @Valid ExpenseDto expenseDto,
                                        BindingResult result, RedirectAttributes redirectAttributes) {
        LOGGER.info("Calling finances Controller - expenses");
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.expenseDto", result);
            redirectAttributes.addFlashAttribute("expenseDto", expenseDto);
            return "redirect:" + EMSURIConstants.ALL_FINANCES + EMSURIConstants.EXPENSE;
        }
        expenseService.saveNewExpense(expenseDto, ide);
        return "redirect:" + EMSURIConstants.ALL_FINANCES + EMSURIConstants.EXPENSES;
    }

    @RequestMapping(value = {EMSURIConstants.NEW_EXPENSE}, method = {RequestMethod.GET})
    public ModelAndView newFacilityExpense(@PathVariable("ide") Integer ide) {
        LOGGER.info("Calling finances Controller - expense");
        ModelAndView view = new ModelAndView("expense");
        view.addObject("expenseDto", new ExpenseDto());
        view.addObject("expenseTypes", CommonExpenseType.values());
        view.addObject("happeningTypes", HappeningType.values());
        view.addObject("happeningOccurence", HappeningOccurence.values());

        return view;
    }

    @RequestMapping(value = {EMSURIConstants.NEW_EXPENSE}, method = {RequestMethod.POST})
    public String newFacilityExpenseSave(@PathVariable("ide") Integer ide,
                                         @ModelAttribute("expense") @Valid ExpenseDto expenseDto,
                                         BindingResult result, RedirectAttributes redirectAttributes) {
        LOGGER.info("Calling finances Controller - expenses");
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.expenseDto", result);
            redirectAttributes.addFlashAttribute("expenseDto", expenseDto);
            return "redirect:" + EMSURIConstants.ALL_FINANCES + EMSURIConstants.NEW_EXPENSE;
        }
        expenseService.saveNewExpense(expenseDto, ide);
        return "redirect:" + EMSURIConstants.ALL_FINANCES + EMSURIConstants.EXPENSES;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = {EMSURIConstants.DELETE_EXPENSE}, method = RequestMethod.GET)
    public String deleteExistingExpense(@PathVariable("ide") Integer ide,
                                        @PathVariable("idexp") Integer idexp) {
        LOGGER.info("Estates Controller - Medium delete");
        expenseService.deleteExpense(idexp);
        return "redirect:" + EMSURIConstants.ALL_FINANCES + EMSURIConstants.EXPENSES;
    }

    @RequestMapping(value = {EMSURIConstants.ALL_PLACECOUNTERS}, method = RequestMethod.GET)
    public ModelAndView allPlaceMediaCounters() {
        LOGGER.info("Calling Places Controller - media counters list");
        ModelAndView view = new ModelAndView("finances");
        List<Map<PlaceDto, List<MediaCounterDto>>> allCountersMaps = mediaService.getAllCountersDefined();
        view.addObject("allCountersMaps", allCountersMaps);
        return view;
    }

    @RequestMapping(value = {EMSURIConstants.ALL_COMMON_PLACECOUNTERS}, method = RequestMethod.GET)
    public ModelAndView allCommonMediaCounters() {
        LOGGER.info("Calling Places Controller - media counters list");
        ModelAndView view = new ModelAndView("finances");
        Map<FacilityDto, List<MediaCounterDto>> commonCountersMap = mediaService.getAllCommonCountersDefined();
        view.addObject("commonCountersMap", commonCountersMap);
        return view;
    }

    @RequestMapping(value = {EMSURIConstants.FACILITY_COMMON_PLACECOUNTERS}, method = RequestMethod.GET)
    public ModelAndView facilityCommonMediaCounters(@PathVariable("ide") Integer ide, Model model) {
        LOGGER.info("Calling Places Controller - media counters list");
        ModelAndView view = new ModelAndView("counters");
        FacilityDto facility = estateService.getFacilityDtoById(ide);
        view.addObject("facility", facility);
        if (!model.containsAttribute("placeDto")) {
            List<MediaCounterDto> facilityCommonCounters = mediaService.getFacilityCommontMediaCountersAsDtos(ide);
            PlaceDto placeDto = new PlaceDto();
            placeDto.setMediaCounters(facilityCommonCounters);
            view.addObject("placeDto", placeDto);
        } else {
            view.addObject(model.asMap().get("placeDto"));
        }
        return view;
    }


    @RequestMapping(value = {EMSURIConstants.FACILITY_COMMON_PLACECOUNTERS}, method = RequestMethod.POST)
    public String saveFacilityCommonMediaCounters(@PathVariable("ide") Integer ide,
                                                  //@ModelAttribute("placeDto") @Valid PlaceDto placeDto,
                                                  @ModelAttribute("placeDto") PlaceDto placeDto,
                                                  BindingResult result, RedirectAttributes redirectAttributes) {
        LOGGER.info("Calling Places Controller - media counters list");
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.placeDto", result);
            redirectAttributes.addFlashAttribute("placeDto", placeDto);
            return "redirect:" + EMSURIConstants.ALL_FINANCES + EMSURIConstants.FACILITY_COMMON_PLACECOUNTERS;
        }
        financeService.updatePlaceCounters(placeDto.getMediaCounters(), ide, null);
        return "redirect:" + EMSURIConstants.ALL_FINANCES + EMSURIConstants.ALL_COMMON_PLACECOUNTERS;
    }

    @RequestMapping(value = {EMSURIConstants.PLACECOUNTERS}, method = RequestMethod.GET)
    public ModelAndView placeMediaCounters(@PathVariable("ide") Integer ide,
                                           @PathVariable("idp") Integer idp, Model model) {
        LOGGER.info("Calling Places Controller - media counters list");
        ModelAndView view = new ModelAndView("counters");
        if (!model.containsAttribute("placeDto")) {
            List<MediaCounterDto> mediaCounters = mediaService.getPlaceMediaCountersAsDtos(idp);
            PlaceDto placeDto = new PlaceDto();
            placeDto.setMediaCounters(mediaCounters);
            view.addObject("placeDto", placeDto);
        } else {
            view.addObject(model.asMap().get("placeDto"));
        }

        return view;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER', 'ROLE_OWNER')")
    @RequestMapping(value = {EMSURIConstants.PLACECOUNTERS}, method = RequestMethod.POST)
    public String placeMediaCountersUpdate(@PathVariable("ide") Integer ide,
                                           @PathVariable("idp") Integer idp,
                                          // @ModelAttribute("placeDto") @Valid PlaceDto placeDto,
                                           @ModelAttribute("placeDto") PlaceDto placeDto,
                                           BindingResult result, RedirectAttributes redirectAttributes) {
        LOGGER.info("Calling Places Controller - media counters list");
        if (result.hasErrors() ) {
            //&& result.getAllErrors().contains(MediaCounterDto.class)
            for(ObjectError error : result.getAllErrors()){
                System.out.println(error.getObjectName());
            }
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.placeDto", result);
            redirectAttributes.addFlashAttribute("placeDto", placeDto);
            return "redirect:" + EMSURIConstants.ALL_FINANCES + EMSURIConstants.PLACECOUNTERS;
        }
        financeService.updatePlaceCounters(placeDto.getMediaCounters(), ide, idp);
        return "redirect:" + EMSURIConstants.ALL_FINANCES;
    }


    @RequestMapping(value = {EMSURIConstants.USER_PLACECOUNTERS}, method = RequestMethod.GET)
    public ModelAndView userCounters(Model model) {
        ModelAndView view = new ModelAndView("counters");
        UserDto userDto = systemUserService.getLoggedUserDto();
        if (!model.containsAttribute("placeDto")) {
            List<MediaCounterDto> mediaCounters = mediaService.getPlaceMediaCountersAsDtos(userDto);
            PlaceDto placeDto = new PlaceDto();
            placeDto.setMediaCounters(mediaCounters);
            view.addObject("placeDto", placeDto);
        } else {
            view.addObject(model.asMap().get("placeDto"));
        }
        return view;
    }

    @PreAuthorize("hasAnyRole('ROLE_OWNER')")
    @RequestMapping(value = {EMSURIConstants.USER_PLACECOUNTERS}, method = RequestMethod.POST)
    public String placeMediaCountersUpdateOwner(@ModelAttribute("placeDto") PlaceDto placeDto,
                                           BindingResult result, RedirectAttributes redirectAttributes) {
        LOGGER.info("Calling Places Controller - media counters list");
        if (result.hasErrors() ) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.placeDto", result);
            redirectAttributes.addFlashAttribute("placeDto", placeDto);
            return "redirect:" + EMSURIConstants.ALL_FINANCES + EMSURIConstants.USER_PLACECOUNTERS;
        }
        financeService.updatePlaceCounters(placeDto.getMediaCounters(),systemUserService.getLoggedUserDto());
        return "redirect:" + EMSURIConstants.ALL_FINANCES + EMSURIConstants.USER_PLACECOUNTERS;
    }


    @RequestMapping(value = {EMSURIConstants.ESTATES}, method = RequestMethod.GET)
    public ModelAndView estates() {
        LOGGER.info("Calling finances Controller - estates");
        ModelAndView view = new ModelAndView("finances");
        List<FacilityDto> facilities = estateService.getFacilitiesAsDtos();
        view.addObject("facilities", facilities);
        return view;
    }

   @RequestMapping(value = {EMSURIConstants.ALL_PLACES_LIST}, method = RequestMethod.GET)
    public ModelAndView allPlaces() {
        LOGGER.info("Calling Estates Controller - all places list");
        ModelAndView view = new ModelAndView("finances");
        List<PlaceDto> places = placeService.getAllPlacesAsDtos();
        view.addObject("places", places);
        return view;
    }


    @RequestMapping(value = {EMSURIConstants.REPORTING}, method = RequestMethod.GET)
    public ModelAndView reporting(Model model) {
        LOGGER.info("Calling finances Controller - reporting");
        ModelAndView view = new ModelAndView("reporting");
        if (!model.containsAttribute("requiredReports")) {
            UserDto userDto = systemUserService.getLoggedUserDto();
            ReportingDto requiredReports = reportService.getRequiredReportsList(userDto);
            view.addObject("requiredReports", requiredReports);
        } else {
            view.addObject(model.asMap().get("requiredReports"));
        }
        return view;
    }

    @RequestMapping(value = {EMSURIConstants.REPORTING}, method = RequestMethod.POST)
    public String reportingPost(@ModelAttribute("requiredReports") @Valid ReportingDto requiredReports,
                                           BindingResult result, RedirectAttributes redirectAttributes) {
        LOGGER.info("Calling Places Controller - media counters list");
        if (result.hasErrors() ) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.requiredReports", result);
            redirectAttributes.addFlashAttribute("requiredReports", requiredReports);
            return "redirect:" + EMSURIConstants.ALL_FINANCES + EMSURIConstants.REPORTING;
        }
        reportService.addNewReporting(requiredReports, systemUserService.getLoggedUserDto());
        return "redirect:" +EMSURIConstants.ALL_FINANCES + EMSURIConstants.REPORTING;
    }
}
