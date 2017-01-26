package pl.com.mnemonic.ems.mapper.implementation;

import pl.com.mnemonic.ems.commons.dto.AddressDto;
import pl.com.mnemonic.ems.commons.dto.FacilityDto;
import pl.com.mnemonic.ems.commons.dto.MediaCounterDto;
import pl.com.mnemonic.ems.commons.dto.PlaceDto;
import pl.com.mnemonic.ems.entity.Address;
import pl.com.mnemonic.ems.entity.Facility;
import pl.com.mnemonic.ems.entity.Place;

import java.util.List;

/**
 * Created by mnemonic on 2016-02-21.
 */
public class PlaceMapper {

    /**
     * Updates Place entity from PlaceDto
     *
     * @param dto    PlaceDto
     * @param entity Place entity
     * @return entity Place
     */
    public static Place mapPlaceDtoToEntity(PlaceDto dto, Place entity) {
        if (entity == null || entity.getIdplace() == null) {
            entity = new Place();
        }
        if (dto.getPlaceid() != null && dto.getPlaceid() > 0) {
            entity.setIdplace(dto.getPlaceid());
        }
        entity.setDescription(dto.getDescription());
        entity.setArea(dto.getArea());
        entity.setResidentCount(dto.getResidentCount());

        return entity;
    }

    /**
     * Prepares new Place entity from PlaceDto with Address and Facility data
     * @param dto PlaceDto
     * @param address Address
     * @param facility Facility
     * @return place Place entity
     */
  /*  public static Place mapPlaceDtoToEntity(PlaceDto dto, Address address, Facility facility) {
        Place place = null;
        if(dto !=null){
            place = new Place();
            if(dto.getId()!=null && dto.getId()>0){
                place.setIdplace(dto.getId());
            }
            place.setDescription(dto.getDescription());
            place.setAddress(address);
            place.setFacility(facility);
            place.setArea(dto.getArea());
            place.setResidentCount(dto.getResidentCount());
        }
        return place;
    }
*/

    /**
     * Prepares PlaceDto including Place entity data, AddressDto, FacilityDto, MediaCounterDto list
     *
     * @param entity      Place entity
     * @param addressDto  AddressDto
     * @param facilityDto FacilityDto
     * @return placeDto
     */
    public static PlaceDto mapEntityToPlaceDto(Place entity, AddressDto addressDto, FacilityDto facilityDto) {
        PlaceDto placeDto = new PlaceDto(entity.getIdplace(), addressDto, facilityDto, entity.getDescription(), entity.getArea(), entity.getResidentCount());
        return placeDto;
    }

    /**
     * Prepares PlaceDto including Place entity data, AddressDto, FacilityDto, MediaCounterDto list
     *
     * @param entity        Place entity
     * @param addressDto    AddressDto
     * @param facilityDto   FacilityDto
     * @param mediaCounters MediaCounterDto List
     * @return placeDto
     */
    public static PlaceDto mapEntityToPlaceDto(Place entity, AddressDto addressDto, FacilityDto facilityDto,
                                               List<MediaCounterDto> mediaCounters) {
        PlaceDto placeDto = mapEntityToPlaceDto(entity, addressDto, facilityDto);
        placeDto.setMediaCounters(mediaCounters);
        return placeDto;
    }

    /**
     * Prepares new PlaceDto including AddressDto and FacilityDto
     *
     * @param addressDto  AddressDto
     * @param facilityDto FacilityDto
     * @return placeDto PlaceDto
     */
    public static PlaceDto mapNewPlaceDto(AddressDto addressDto, FacilityDto facilityDto) {
        PlaceDto placeDto = new PlaceDto(facilityDto, addressDto);
        return placeDto;
    }

    /**
     * Prepares new Place Entity based on PlaceDto data, with Address and Facility
     *
     * @param dto      PlaceDto
     * @param address  Address
     * @param facility Facility
     * @return place Place entity
     */
    public static Place mapPlaceDtoToEntity(PlaceDto dto, Address address, Facility facility) {
        Place place = null;
        if (dto != null) {
            place = new Place();
            if (dto.getId() != null && dto.getId() > 0) {
                place.setIdplace(dto.getId());
            }
            place.setDescription(dto.getDescription());
            place.setAddress(address);
            place.setFacility(facility);
            place.setArea(dto.getArea());
            place.setResidentCount(dto.getResidentCount());
        }
        return place;
    }
}
