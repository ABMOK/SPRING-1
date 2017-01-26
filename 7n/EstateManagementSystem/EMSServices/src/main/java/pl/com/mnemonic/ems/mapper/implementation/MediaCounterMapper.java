package pl.com.mnemonic.ems.mapper.implementation;

import pl.com.mnemonic.ems.commons.dto.MediaCounterDto;
import pl.com.mnemonic.ems.commons.dto.MediaDto;
import pl.com.mnemonic.ems.commons.parsers.EMSUtilsParser;
import pl.com.mnemonic.ems.entity.Media;
import pl.com.mnemonic.ems.entity.MediaCounter;
import pl.com.mnemonic.ems.entity.Place;

import java.util.Date;

/**
 * Created by mnemonic on 2016-02-21.
 */
public class MediaCounterMapper {

    public static enum Type{
        PRIVATE, COMMON
    }

    /**
     * Prepares MediaCounterDto from MediaCounter entity and MediaDto data
     * @param entity MediaCounter
     * @param medium MediaDto
     * @return mediaCounterDto MediaCounterDto
     */
    public static MediaCounterDto mapEntityToMediaCounterDto(MediaCounter entity, MediaDto medium) {
        MediaCounterDto mediaCounterDto = new MediaCounterDto(entity.getIdmediaCounter(), medium,
                entity.getMediaType(),entity.getCounterType(), entity.getRegistryNumber(),
                entity.getDateRegistered(), String.valueOf(entity.getTotalAmount()));
        return mediaCounterDto;
    }

    /**
     * Prepares MediaCounterDto from MediaCounter entity
     * @param entity MediaCounter
     * @return mediaCounterDto MediaCounterDto
     */
    public static MediaCounterDto mapEntityToMediaCounterDto(MediaCounter entity) {
        MediaCounterDto mediaCounterDto = new MediaCounterDto(entity.getIdmediaCounter(),
                entity.getMediaType(),entity.getCounterType(), entity.getRegistryNumber(),
                entity.getDateRegistered(), String.valueOf(entity.getTotalAmount()));
        return mediaCounterDto;
    }

    /**
     * Prepares new Media Counter Entity from MediaCounterDto with Place, Media and type data
     * @param mediaCounterDto MediaCounterDto source data
     * @param place Place
     * @param medium Media
     * @param type Counter type
     * @return counterEntity Media Counter Entity
     */
    public static MediaCounter mapDtoToMediaCounterEntity(MediaCounterDto mediaCounterDto, Place place, Media medium, MediaCounterMapper.Type type) {
        MediaCounter counterEntity = mapNewMediaCounterEntity(place, medium, type);
        counterEntity.setRegistryNumber(mediaCounterDto.getRegistryNumber());
        counterEntity.setTotalAmount(EMSUtilsParser.parseToDouble(mediaCounterDto.getTotalAmount()));
        counterEntity.setDateUpdated(new Date());
        return counterEntity;
    }

    /**
     * Prepares new Media Counter Entity with Place, Media and type data
     * @param place Place
     * @param medium Media
     * @param type Counter type
     * @return counterEntity Media Counter Entity
     */
    public static MediaCounter mapNewMediaCounterEntity(Place place, Media medium, MediaCounterMapper.Type type) {
        MediaCounter counterEntity = new MediaCounter();
        counterEntity.setDateRegistered(new Date());
        counterEntity.setPlace(place);
        counterEntity.setMedia(medium);
        counterEntity.setCounterType(type.name());
        counterEntity.setMediaType(medium.getType());
        return counterEntity;
    }

    /**
     * Updates MediaCounter Entity from MediaCounterDto data
     * @param mediaCounterDto MediaCounterDto
     * @param counterEntity MediaCounter
     * @return counterEntity MediaCounter
     */
    public static MediaCounter mapDtoToMediaCounterEntity(MediaCounterDto mediaCounterDto, MediaCounter counterEntity) {
        counterEntity.setDateUpdated(new Date());
        counterEntity.setRegistryNumber(mediaCounterDto.getRegistryNumber());
        counterEntity.setTotalAmount(EMSUtilsParser.parseToDouble(mediaCounterDto.getTotalAmount()));
        return counterEntity;
    }
}
