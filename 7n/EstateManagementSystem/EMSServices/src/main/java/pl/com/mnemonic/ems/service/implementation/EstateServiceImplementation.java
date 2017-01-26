package pl.com.mnemonic.ems.service.implementation;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.mnemonic.ems.commons.dto.AddressDto;
import pl.com.mnemonic.ems.commons.dto.FacilityDto;
import pl.com.mnemonic.ems.commons.dto.PlaceDto;
import pl.com.mnemonic.ems.dao.interfaces.AddressDaoInterface;
import pl.com.mnemonic.ems.dao.interfaces.FacilityDaoInterface;
import pl.com.mnemonic.ems.entity.Address;
import pl.com.mnemonic.ems.entity.Facility;
import pl.com.mnemonic.ems.mapper.implementation.AddressMapper;
import pl.com.mnemonic.ems.mapper.implementation.EstateMapper;
import pl.com.mnemonic.ems.service.interfaces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("estateService")
public class EstateServiceImplementation implements EstateServiceInterface {
    static final Logger LOGGER = Logger.getLogger(EstateServiceImplementation.class);


    @Autowired
    FacilityDaoInterface facilityDao;
    @Autowired
    AddressDaoInterface addressDao;
    @Autowired
    PlaceServiceInterface placeService;
    @Autowired
    FinanceServiceInterface financeService;
    @Autowired
    MediaServiceInterface mediaService;
    @Autowired
    ExpenseServiceInterface expenseService;


    /**
     * Prepares list of registered estates
     *
     * @return facilityList including all registered estates
     */
    @Override
    public List<FacilityDto> getFacilitiesAsDtos() {
        List<FacilityDto> facilityList = new ArrayList<FacilityDto>();
        FacilityDto dto;
        for (Facility facility : facilityDao.list()) {
            dto = getFacilityDtoById(facility.getIdfacility());
            facilityList.add(dto);
        }
        return facilityList;
    }

    /**
     * Collects estate data for FacilityDto
     *
     * @param id facility id
     * @return dto including estate with address and places
     */
    @Override
    public FacilityDto getFacilityDtoById(int id) {
        FacilityDto dto = null;
        Facility facility = facilityDao.find(id);
        if (facility != null) {
            AddressDto addressDto = AddressMapper.mapEntityToAddressDto(addressDao.find(facility.getAddress().getIdaddress()));
            dto = EstateMapper.mapEntityToFacilityDto(facility, addressDto);
            List<PlaceDto> places = placeService.getFacilityPlacesAsDtos(dto);
            dto.setPlaces(places);
        }

        return dto;
    }


    /**
     * Removes facility from database with all it's dependants
     *
     * @param ide Facility ID
     */
    @Override
    public void deleteEstate(Integer ide) {
        Facility facility = facilityDao.find(ide);
        facilityDao.remove(facility);
    }

    /**
     * Writes Facility to database with all it's dependants
     *
     * @param dto FacilityDto
     */
    @Override
    public void addFacility(FacilityDto dto) {

        Facility facility;
        Address address = null;
        boolean hasPlaces = false;
        boolean hasMedia = false;
        boolean hasServices = false;

        if (dto.getAddressDto() != null) {
            if (dto.getAddressDto().getIdaddress() != null && dto.getAddressDto().getIdaddress() > 0) {
                address = AddressMapper.mapAddressDtoToEntity(dto.getAddressDto(), addressDao.find(dto.getAddressDto().getIdaddress()));
                addressDao.update(address);
            } else {
                address = AddressMapper.mapAddressDtoToEntity(dto.getAddressDto());
                addressDao.add(address);
            }
        }

        if (dto.getIdfacility() != null && dto.getIdfacility() > 0) {
            facility = EstateMapper.mapFacilityDtoToEntity(dto, facilityDao.find(dto.getIdfacility()));
            facility.setAddress(address);
            facilityDao.update(facility);
        } else {
            facility = EstateMapper.mapFacilityDtoToEntity(dto, null);
            facility.setAddress(address);
            facilityDao.add(facility);
            hasPlaces = placeService.resolveFacilityPlaces(dto, address, facility);
            hasMedia = mediaService.resolveFacilityMedia(dto.getMediaTypes(), facility);
            hasServices = expenseService.resolveFacilityServices(dto.getExpenseTypes(), facility);
        }

        if (hasPlaces && hasMedia) {
            mediaService.initializePlaceMediaCounters(facility.getIdfacility());
        }
        //TODO if(hasServices)
    }


    //TODO check if used
    @Override
    public List<FacilityDto> validateDuplicateFacility(FacilityDto dto) {
        List<FacilityDto> facilitiesWithSameAddress = null;
        Map<String, String> criterias = new HashMap<>();
        criterias.put("cityName", dto.getAddressDto().getCityName());
        criterias.put("streetName", dto.getAddressDto().getStreetName());
        criterias.put("buildingNo", dto.getAddressDto().getBuildingNo());
        List<Address> adressFromDto = addressDao.getSimilarAddressByCriteria(criterias);
        if (adressFromDto != null && adressFromDto.size() > 0) {
            facilitiesWithSameAddress = new ArrayList<>();
            for (Address address : adressFromDto) {
                List<Facility> possibleMatch = facilityDao.getFacilityByAddressId(address.getIdaddress());
                if (possibleMatch != null && !possibleMatch.isEmpty()) {
                    AddressDto addressDto = AddressMapper.mapEntityToAddressDto(address);
                    for (Facility facilityMatch : possibleMatch) {
                        if (!facilityMatch.getIdfacility().equals(dto.getIdfacility())) {
                            facilitiesWithSameAddress.add(EstateMapper.mapEntityToFacilityDto(facilityMatch, addressDto));
                        }
                    }
                }
            }
        }
        return facilitiesWithSameAddress;
    }
}
