package pl.com.mnemonic.ems.mapper.implementation;

import pl.com.mnemonic.ems.commons.dto.AddressDto;
import pl.com.mnemonic.ems.entity.Address;

/**
 * Created by mnemonic on 2016-02-21.
 */
public class AddressMapper {

    /**
     * Prepares AddressDto from Address entity
     * @param entity Address entity
     * @return AddressDto
     */
    public static AddressDto mapEntityToAddressDto(Address entity) {
        AddressDto dto = new AddressDto(entity.getIdaddress(), entity.getCountry(), entity.getCountryCode(),
                entity.getCityCode(), entity.getCityName(), entity.getDistrictName(), entity.getStreetName(),
                entity.getBuildingNo(), entity.getOfficeNo());
        return dto;
    }

    /**
     * Updates Address entity data from AddressDto
     * @param dto AddressDto
     * @param entity Address entity
     * @return Address entity
     */
    public static Address mapAddressDtoToEntity(AddressDto dto, Address entity) {
        if (dto != null) {
            if (entity.getIdaddress() != null && entity.getIdaddress() > 0) {
                //TODO ??
            }
                entity.setCountry(dto.getCountry());
                entity.setCountryCode(dto.getCountryCode());
                entity.setCityName(dto.getCityName());
                entity.setCityCode(dto.getCityCode());
                entity.setDistrictName(dto.getDistrictName());
                entity.setStreetName(dto.getStreetName());
                entity.setBuildingNo(dto.getBuildingNo());
                entity.setOfficeNo(dto.getOfficeNo());
        }
        return entity;
    }

    /**
     * Prepares new Address data from AddressDto
     * @param dto AddressDto
     * @return Address entity
     */
    public static Address mapAddressDtoToEntity(AddressDto dto) {
        Address address = null;
        if (dto != null) {
            address = new Address();
            address.setIdaddress(dto.getIdaddress());
            address.setCountry(dto.getCountry());
            address.setCountryCode(dto.getCountryCode());
            address.setCityName(dto.getCityName());
            address.setCityCode(dto.getCityCode());
            address.setDistrictName(dto.getDistrictName());
            address.setStreetName(dto.getStreetName());
            address.setBuildingNo(dto.getBuildingNo());
            address.setOfficeNo(dto.getOfficeNo());
        }
        return address;
    }
}
