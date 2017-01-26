package pl.com.mnemonic.ems.mapper.implementation;

import pl.com.mnemonic.ems.commons.dto.AddressDto;
import pl.com.mnemonic.ems.commons.dto.FacilityDto;
import pl.com.mnemonic.ems.entity.Facility;

/**
 * Created by mnemonic on 2016-02-21.
 */
public class EstateMapper {

    /**
     * Updates or Prepares facility entity from FacilityDto data
     * @param dto      FacilityDto
     * @param facility Facility
     * @return Facility entity
     */
    public static Facility mapFacilityDtoToEntity(FacilityDto dto, Facility facility) {
        if (facility == null || facility.getIdfacility() == null) {
            facility = new Facility();
        }
        if(dto.getIdfacility()!=null && dto.getIdfacility()>0){
            facility.setIdfacility(dto.getIdfacility());
        }
        facility.setDescription(dto.getDescription());
        facility.setType(dto.getType());
        facility.setArea(dto.getArea());
        return facility;
    }

    /**
     * Prepares FacilityDto data
     *
     * @param entity Facility entity
     * @return FacilityDto
     */
    public static FacilityDto mapEntityToFacilityDto(Facility entity) {
        FacilityDto dto = new FacilityDto(entity.getIdfacility(), entity.getDescription(), entity.getType(), entity.getArea());
        return dto;
    }

    /**
     * Prepares FacilityDto including AddressDto
     *
     * @param entity     Facility entity
     * @param addressDto AddressDto
     * @return FacilityDto
     */
    public static FacilityDto mapEntityToFacilityDto(Facility entity, AddressDto addressDto) {
        FacilityDto dto = mapEntityToFacilityDto(entity);
        dto.setAddressDto(addressDto);
        return dto;
    }
}
