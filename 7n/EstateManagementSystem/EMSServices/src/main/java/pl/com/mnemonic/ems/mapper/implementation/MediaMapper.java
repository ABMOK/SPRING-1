package pl.com.mnemonic.ems.mapper.implementation;

import pl.com.mnemonic.ems.commons.dto.FacilityDto;
import pl.com.mnemonic.ems.commons.dto.MediaDto;
import pl.com.mnemonic.ems.commons.enums.MediaType;
import pl.com.mnemonic.ems.commons.parsers.EMSUtilsParser;
import pl.com.mnemonic.ems.entity.Facility;
import pl.com.mnemonic.ems.entity.Media;

import java.util.Date;

/**
 * Created by mnemonic on 2016-02-21.
 */
public class MediaMapper {

    /**
     * Prepares MediaDto from Media Entity data
     * @param entity Media Entity
     * @return mediumDto MediaDto
     */
    public static MediaDto mapEntityToMediaDto(Media entity) {
        MediaDto mediumDto = new MediaDto(entity.getIdmedia(), entity.getType(), entity.getMedianame(),
                String.valueOf(entity.getPrice()), entity.getMeasureunit(), entity.getDateregistered());
        return mediumDto;
    }

    /**
     * Prepares new Media definition for specified Estate
     * @param facility Facility
     * @param mediaType Media Type
     * @return new Media entity
     */
    public static Media mapNewMedia(Facility facility, Integer mediaType){
        Media medium = new Media();
        medium.setFacility(facility);
        medium.setDateregistered(new Date());
        if(mediaType!=null){
            medium.setMedianame(MediaType.fromInteger(mediaType).getMediaTypeName());
            medium.setType(MediaType.fromInteger(mediaType).name());
        }
        return medium;
    }

    /**
     * Prepares new Media entity based on MediaDto data for specified Facility
     * @param dto MediaDto
     * @param facility Facility entity
     * @return medium Media entity
     */
    public static Media mapMediaDtoToEntity(MediaDto dto, Facility facility) {
        Media medium = new Media();
        if(dto.getIdmedia()!=null && dto.getIdmedia()>0){
            medium.setIdmedia(dto.getIdmedia());
        }
        medium.setPrice(EMSUtilsParser.parseToDouble(dto.getPrice()));
        medium.setDateregistered(new Date());
        medium.setMeasureunit(dto.getMeasureunit());
        medium.setFacility(facility);
        medium.setMedianame(dto.getMedianame());
        medium.setType(dto.getType());
        return medium;
    }

    /**
     * Prepares Media for update using MediaDto data for specified Facility
     * @param dto MediaDto
     * @param medium Media entity
     * @param facility Facility
     * @return Media entity
     */
    public static Media mapMediaDtoToEntity(MediaDto dto, Media medium, Facility facility) {
        if (medium != null && medium.getIdmedia() != null && medium.getIdmedia() > 0) {
            medium.setPrice(EMSUtilsParser.parseToDouble(dto.getPrice()));
            medium.setMeasureunit(dto.getMeasureunit());
        } else {
            return mapMediaDtoToEntity(dto, facility);
        }
        return medium;
    }

    /**
     * Prepares MediaDto from Media entity data for specified FacilityDto
     * @param entity Media entity
     * @param facility FacilityDto
     * @return MediaDto
     */
    public static MediaDto mapEntityToMediaDto(Media entity, FacilityDto facility) {
        MediaDto mediumDto = new MediaDto(entity.getIdmedia(), facility, entity.getType(), entity.getMedianame(),
                String.valueOf(entity.getPrice()), entity.getMeasureunit(), entity.getDateregistered());
        return mediumDto;
    }
}
