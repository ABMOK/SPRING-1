package pl.com.mnemonic.ems.service.implementation;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.mnemonic.ems.commons.dto.AddressDto;
import pl.com.mnemonic.ems.commons.dto.FacilityDto;
import pl.com.mnemonic.ems.commons.dto.MediaCounterDto;
import pl.com.mnemonic.ems.commons.dto.PlaceDto;
import pl.com.mnemonic.ems.dao.interfaces.AddressDaoInterface;
import pl.com.mnemonic.ems.dao.interfaces.FacilityDaoInterface;
import pl.com.mnemonic.ems.dao.interfaces.PlaceDaoInterface;
import pl.com.mnemonic.ems.entity.Address;
import pl.com.mnemonic.ems.entity.Facility;
import pl.com.mnemonic.ems.entity.Place;
import pl.com.mnemonic.ems.mapper.implementation.AddressMapper;
import pl.com.mnemonic.ems.mapper.implementation.EstateMapper;
import pl.com.mnemonic.ems.mapper.implementation.PlaceMapper;
import pl.com.mnemonic.ems.service.interfaces.EstateServiceInterface;
import pl.com.mnemonic.ems.service.interfaces.MediaServiceInterface;
import pl.com.mnemonic.ems.service.interfaces.PlaceServiceInterface;

import java.util.ArrayList;
import java.util.List;

@Service("placeService")
public class PlaceServiceImplementation implements PlaceServiceInterface {
    static final Logger LOGGER = Logger.getLogger(PlaceServiceImplementation.class);

    @Autowired
    FacilityDaoInterface facilityDao;
    @Autowired
    PlaceDaoInterface placeDao;
    @Autowired
    AddressDaoInterface addressDao;
    @Autowired
    EstateServiceInterface estateService;
    @Autowired
    MediaServiceInterface mediaService;

    /**
     * collects all registered places
     *
     * @return places List of all places
     */
    @Override
    public List<PlaceDto> getAllPlacesAsDtos() {
        List<PlaceDto> places = new ArrayList<PlaceDto>();
        for (Place place : placeDao.list()) {
            Facility facility = facilityDao.find(place.getFacility().getIdfacility());
            FacilityDto facilityDto = EstateMapper.mapEntityToFacilityDto(facility);
            AddressDto addressDto = AddressMapper.mapEntityToAddressDto(addressDao.find(place.getAddress().getIdaddress()));
            places.add(PlaceMapper.mapEntityToPlaceDto(place, addressDto, facilityDto));
        }
        return places;
    }

    /**
     * Retrieves PlaceDto by Place ID
     *
     * @param id Place ID
     * @return PlaceDto
     */
    @Override
    public PlaceDto getPlaceDtoByPlaceId(Integer id) {
        Place place = placeDao.find(id);
        return getPlaceDtoByFacilityAndPlaceIds(place.getFacility().getIdfacility(), id);
    }

    /**
     * Retrieves PlaceDto from Place and Facility IDs
     *
     * @param idf Facility ID
     * @param idp Place ID
     * @return placeDto PlaceDto
     */
    @Override
    public PlaceDto getPlaceDtoByFacilityAndPlaceIds(Integer idf, Integer idp) {
        FacilityDto facilityDto = estateService.getFacilityDtoById(idf);
        Place place = placeDao.find(idp);
        if (place == null) {
            throw new NullPointerException();
        }
        PlaceDto placeDto = null;
        if (place.getFacility().getIdfacility().equals(facilityDto.getIdfacility())) {
            AddressDto addressDto = AddressMapper.mapEntityToAddressDto(addressDao.find(place.getAddress().getIdaddress()));
            placeDto = PlaceMapper.mapEntityToPlaceDto(place, addressDto, facilityDto);
        }
        return placeDto;
    }

    /**
     * Deletes place with all it's dependants
     *
     * @param ide Estate ID
     * @param idp Place ID
     */
    @Override
    public void deletePlace(Integer ide, Integer idp) {
        Place place = placeDao.find(idp);
        placeDao.remove(place);

    }

    /**
     * Retrieves Place Entity by Facility and Place ID's
     *
     * @param idf Facility id
     * @param idp Place id
     * @return Place entity
     */
    @Override
    public Place retrievePlace(Integer idf, Integer idp) {
        Facility facility = facilityDao.find(idf);
        Place place = placeDao.find(idp);
        if (place.getFacility().getIdfacility().equals(facility.getIdfacility())) {
            return place;
        } else
            return null;
    }

    /**
     * Retrieves Place Entity by Place ID
     *
     * @param idp Place id
     * @return Place entity
     */
    @Override
    public Place retrievePlace(int idp) {
        return placeDao.find(idp);
    }

    /**
     * Prepares List of PlaceDto's for specified Estate
     *
     * @param facilityDto FacilityDto
     * @return facilityPlaces List of PlaceDto's
     */
    @Override
    public List<PlaceDto> getFacilityPlacesAsDtos(FacilityDto facilityDto) {
        List<PlaceDto> facilityPlaces = new ArrayList<PlaceDto>();
        List<MediaCounterDto> placeMediaCounters;
        for (Place place : placeDao.getFacilityPlaces(facilityDto.getIdfacility())) {
            AddressDto addressDto = AddressMapper.mapEntityToAddressDto(addressDao.find(place.getAddress().getIdaddress()));
            placeMediaCounters = mediaService.getPlaceMediaCountersAsDtos(place.getIdplace());
            facilityPlaces.add(PlaceMapper.mapEntityToPlaceDto(place, addressDto, facilityDto, placeMediaCounters));
        }
        return facilityPlaces;
    }


    /**
     * Prepares data update for places from PlaceDto
     *
     * @param dto        PlaceDto
     * @param idfacility Facility ID
     */
    @Override
    public void prepareFacilityPlacesUpdate(PlaceDto dto, Integer idfacility) {
        Facility facility = facilityDao.find(idfacility);
        Address address;
        if (dto.getAddress() != null && dto.getAddress().getIdaddress() != null
                && dto.getAddress().getIdaddress() > 0) {
            Address forUpdate = addressDao.find(dto.getAddress().getIdaddress());
            address = AddressMapper.mapAddressDtoToEntity(dto.getAddress(), forUpdate);
            addressDao.update(address);
        } else {
            address = AddressMapper.mapAddressDtoToEntity(dto.getAddress());
            addressDao.add(address);
        }

        Place place;
        if (dto.getId() != null && dto.getId() > 0) {
            place = PlaceMapper.mapPlaceDtoToEntity(dto, placeDao.find(dto.getId()));
            place.setAddress(address);
            placeDao.update(place);
        } else {
            place = PlaceMapper.mapPlaceDtoToEntity(dto, address, facility);
            placeDao.add(place);
            mediaService.initializePlaceMediaCounters(place, idfacility);
        }
    }

    /**
     * Prepares PlaceDto containing Facility data and own Address based on Facility address
     * If facility had number of places specified, and places with no data exist for facility,
     * returns first of places with no data. If no such place exist, prepares new Dto
     *
     * @param ide Facility ID
     * @return dto PlaceDto with pre-applied data
     */
    @Override
    public PlaceDto getPurePlaceDtoByFacilityId(Integer ide) {
        FacilityDto facilityDto = estateService.getFacilityDtoById(ide);
        PlaceDto dto;
        if (facilityDto.getPlaces() != null && !facilityDto.getPlaces().isEmpty()) {
            for (PlaceDto placeDto : facilityDto.getPlaces()) {
                if (placeDto.getAddress().getOfficeNo() == null || placeDto.getAddress().getOfficeNo().trim().length() == 0) {
                    return placeDto;
                }
            }
        }
        dto = new PlaceDto(facilityDto, facilityDto.getAddressDto());
        dto.getAddress().setIdaddress(null);
        return dto;
    }

    /**
     * within PlaceController, validates data for new place.
     * If duplicate data exists within database, returns list of places with same data
     *
     * @param ide facility ID
     * @param placeDto new place DTO
     * @return list of duplicates
     */
    @Override
    public List<PlaceDto> validateDuplicatePlace(Integer ide, PlaceDto placeDto) {
        FacilityDto facilityDto = estateService.getFacilityDtoById(ide);
        List<PlaceDto> facilityDuplicatePlaces = new ArrayList<>();
        if (placeDto != null && facilityDto != null) {
            AddressDto addressDto = placeDto.getAddress();
            if (addressDto != null && addressDto.getOfficeNo() != null
                    && facilityDto.getPlaces() != null && !facilityDto.getPlaces().isEmpty()) {
                for (PlaceDto place : facilityDto.getPlaces()) {
                    if (!place.getPlaceid().equals(placeDto.getPlaceid())) {
                        AddressDto existingAddress = place.getAddress();
                        if (existingAddress.getOfficeNo() != null && addressDto.getOfficeNo() != null
                                && existingAddress.getOfficeNo().equals(addressDto.getOfficeNo())) {
                            facilityDuplicatePlaces.add(place);
                        }
                    }
                }
            }
        }

        return facilityDuplicatePlaces;
    }

    /**
     * Prepares Facility Places data update within database
     *
     * @param dto      FacilityDto
     * @param address  Address entity
     * @param facility Facility entity
     * @return boolean if success has been achieved
     */
    @Override
    public Boolean resolveFacilityPlaces(FacilityDto dto, Address address, Facility facility) {
        Place place;
        PlaceDto placeDto;

        List<PlaceDto> existingPlaces = dto.getPlaces();
        List<PlaceDto> placeDefinitions = new ArrayList<PlaceDto>();

        if (dto.getNumberOfPlaces() != null && dto.getNumberOfPlaces() > 0) {
            for (int i = 0; i < dto.getNumberOfPlaces(); i++) {
                if (existingPlaces != null && existingPlaces.size() < i) {
                    placeDto = existingPlaces.get(i);
                } else {
                    placeDto = PlaceMapper.mapNewPlaceDto(AddressMapper.mapEntityToAddressDto(address),
                            EstateMapper.mapEntityToFacilityDto(facility));
                }
                placeDefinitions.add(placeDto);
            }
        }

        if (!placeDefinitions.isEmpty()) {
            for (PlaceDto placeDefinition : placeDefinitions) {
                if (placeDefinition.getId() != null && placeDefinition.getId() > 0) {
                    place = PlaceMapper.mapPlaceDtoToEntity(placeDefinition,
                            placeDao.find(placeDefinition.getId()));
                    placeDao.update(place);
                } else {
                    Address newPlaceAddress = address;
                    newPlaceAddress.setIdaddress(null);
                    addressDao.add(newPlaceAddress);
                    place = PlaceMapper.mapPlaceDtoToEntity(placeDefinition, newPlaceAddress, facility);
                    placeDao.add(place);
                }
            }

            return true;
        }
        return false;
    }


}
