package pl.com.mnemonic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.com.mnemonic.ems.commons.dto.FacilityDto;
import pl.com.mnemonic.ems.commons.dto.PlaceDto;
import pl.com.mnemonic.ems.dao.interfaces.FacilityDaoInterface;
import pl.com.mnemonic.ems.mapper.implementation.EstateMapper;
import pl.com.mnemonic.ems.service.interfaces.PlaceServiceInterface;

import java.util.List;

/**
 * Created by mnemonic on 2016-01-30.
 */
@Controller
public class AjaxController {
    @Autowired
    PlaceServiceInterface placeService;
    @Autowired
    FacilityDaoInterface estateDao;

    /**
     * Provides list of facility places for requested facility
     *
     * @param facility
     * @return list of facility places
     */
    @RequestMapping(method = RequestMethod.GET, value = "/ajax/places", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<PlaceDto> getPlacesList(@RequestParam(value = "facility", required = true) String facility) {
        Integer facilityId = Integer.parseInt(facility);
        FacilityDto facilityDto = EstateMapper.mapEntityToFacilityDto(estateDao.find(facilityId));
        List<PlaceDto> facilityPlaces = placeService.getFacilityPlacesAsDtos(facilityDto);
        return facilityPlaces;
    }
}
