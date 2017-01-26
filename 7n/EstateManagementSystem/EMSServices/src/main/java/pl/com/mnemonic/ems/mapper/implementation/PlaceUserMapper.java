package pl.com.mnemonic.ems.mapper.implementation;

import pl.com.mnemonic.ems.commons.dto.PlaceDto;
import pl.com.mnemonic.ems.commons.dto.PlaceUserDto;
import pl.com.mnemonic.ems.commons.dto.UserDto;
import pl.com.mnemonic.ems.entity.Place;
import pl.com.mnemonic.ems.entity.Placeuser;

import java.util.Date;

/**
 * Created by mnemonic on 2016-02-21.
 */
public class PlaceUserMapper {

    /**
     * Prepares PlaceUserDto with data from Placeuser entity including PlaceDto and UserDto
     *
     * @param entity     Placeuser entity
     * @param placeDto   PlaceDto
     * @param systemUser UserDto
     * @return PlaceUserDto
     */
    public static PlaceUserDto mapEntityToPlaceUserDto(Placeuser entity, PlaceDto placeDto, UserDto systemUser) {
        PlaceUserDto dto = new PlaceUserDto(entity.getIdplaceuser(),
                placeDto,
                entity.getUsageType(),
                entity.getStartdate(),
                entity.getEnddate(),
                systemUser);
        return dto;
    }

    /**
     * Updates Placeuser Entity from PlaceUserDto data
     *
     * @param dto
     * @param placeuser
     * @param place
     * @return
     */
    public static Placeuser mapPlaceUserDtoToEntity(PlaceUserDto dto, Placeuser placeuser, Place place) {
        if (placeuser == null || placeuser.getIdplaceuser() == null) {
            placeuser = new Placeuser();
        }
        if (dto != null) {
            if (dto.getIdplaceuser() != null && dto.getIdplaceuser() > 0) {
                placeuser.setIdplaceuser(dto.getIdplaceuser());
            }
            placeuser.setUsageType(dto.getUsageType());
            placeuser.setStartdate(dto.getStartdate());
            placeuser.setEnddate(dto.getEnddate());
            placeuser.setPlace(place);
        }

        return placeuser;
    }

    /**
     * Creates new Placeuser entity with data from PlaceUserDto and Place
     *
     * @param dto   PlaceUserDto
     * @param place Place
     * @return tenant Placeuser Entity
     */
    public static Placeuser mapPlaceUserDtoToEntity(PlaceUserDto dto, Place place) {
        Placeuser tenant = new Placeuser();
        if (dto.getIdplaceuser() != null && dto.getIdplaceuser() > 0) {
            tenant.setIdplaceuser(dto.getIdplaceuser());
        }
        tenant.setPlace(place);
        tenant.setUsageType(dto.getUsageType());
        tenant.setEnddate(dto.getEnddate());
        tenant.setStartdate(new Date());

        return tenant;
    }
}
