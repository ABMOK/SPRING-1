package pl.com.mnemonic.ems.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.mnemonic.ems.commons.dto.*;
import pl.com.mnemonic.ems.dao.interfaces.*;
import pl.com.mnemonic.ems.entity.Facility;
import pl.com.mnemonic.ems.entity.Media;
import pl.com.mnemonic.ems.entity.MediaCounter;
import pl.com.mnemonic.ems.entity.Place;
import pl.com.mnemonic.ems.mapper.implementation.MediaCounterMapper;
import pl.com.mnemonic.ems.mapper.implementation.MediaMapper;
import pl.com.mnemonic.ems.service.interfaces.*;

import java.util.*;

@Service("mediaService")
public class MediaServiceImplementation implements MediaServiceInterface {

    @Autowired
    FinanceServiceInterface financeService;
    @Autowired
    PlaceServiceInterface placeService;
    @Autowired
    EstateServiceInterface estateService;
    @Autowired
    FacilityDaoInterface facilityDao;
    @Autowired
    MediaDaoInterface mediaDao;
    @Autowired
    MediaCounterDaoInterface counterDao;
    @Autowired
    PlaceDaoInterface placeDao;
    @Autowired
    TenantsServiceInterface tenantService;


    /**
     * Prepares new MediaDto for specified Facility
     *
     * @param id Facility ID
     * @return medium MediaDto
     */
    @Override
    public MediaDto getNewMediaDtoByFacilityId(Integer id) {
        FacilityDto facilityDto = estateService.getFacilityDtoById(id);
        MediaDto medium = null;
        if (facilityDto != null) {
            medium = new MediaDto(facilityDto);
        }
        return medium;
    }

    /**
     * Inserts to database new Medium for specified Facility
     *
     * @param dto MediaDto
     * @param ide Facility ID
     * @return boolean true if success
     */
    @Override
    public Boolean addNewMedium(MediaDto dto, Integer ide) {
        Facility facility = facilityDao.find(ide);
        if (facility != null) {
            Media newMedium = MediaMapper.mapMediaDtoToEntity(dto, facility);
            if (newMedium.getIdmedia() == null && !facilityContainsMedium(newMedium, ide)) {
                mediaDao.add(newMedium);
                addNewCommonMediaCounter(newMedium);
                initializePlaceMediaCounters(ide, newMedium);
                return true;
            } else {
                if (newMedium.getIdmedia() != null && prepareFacilityMediumUpdate(dto, ide, newMedium.getIdmedia())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Prepares List of MediaCounterDto's for specified place
     *
     * @param idp Place ID
     * @return mediaCounters List of MediaCounterDto's
     */
    @Override
    public List<MediaCounterDto> getPlaceMediaCountersAsDtos(Integer idp) {
        List<MediaCounterDto> mediaCounters = new ArrayList<MediaCounterDto>();
        MediaCounterDto mediaCounterDto;
        MediaDto mediumDto;
        Place place = placeDao.find(idp);
        List<Media> facilityMediaList = mediaDao.getFacilityMedia(place.getFacility().getIdfacility());
        List<MediaCounter> mediaCountersEntity = counterDao.getPlaceMediaCounters(idp);

        if (mediaCountersEntity != null && !mediaCountersEntity.isEmpty()
                && facilityMediaList.size() == mediaCountersEntity.size()) {
            for (MediaCounter counter : mediaCountersEntity) {
                mediumDto = MediaMapper.mapEntityToMediaDto(mediaDao.find(counter.getMedia().getIdmedia()));
                mediaCounterDto = MediaCounterMapper.mapEntityToMediaCounterDto(counter, mediumDto);
                mediaCounters.add(mediaCounterDto);
            }
        } else {
            mediaCounters = financeService.generateMediaCountersRestart(place.getFacility().getIdfacility(), idp);
        }
        return mediaCounters;
    }

    /**
     * Prepares List of MediaCounterDto for specified user
     *
     * @param dto UserDto
     * @return mediaCounters List of MediaCounterDto's
     */
    @Override
    public List<MediaCounterDto> getPlaceMediaCountersAsDtos(UserDto dto) {
        if (dto != null && dto.getPlaceUserId() != null) {
            PlaceUserDto placeuser = tenantService.getTenantDtoById(dto.getPlaceUserId());
            if (placeuser != null && placeuser.getPlaceDto() != null && placeuser.getPlaceDto().getPlaceid() != null) {
                return getPlaceMediaCountersAsDtos(placeuser.getPlaceDto().getPlaceid());
            }
        }
        return null;
    }

    /**
     * Prepares Media Counters data within database for specified facility places
     *
     * @param idfacility Integer Facility ID
     */
    @Override
    public void initializePlaceMediaCounters(Integer idfacility) {
        List<Place> facilityPlaces = null;
        if (idfacility != null) {
            facilityPlaces = placeDao.getFacilityPlaces(idfacility);
        }

        if (facilityPlaces != null && !facilityPlaces.isEmpty()) {
            for (Place place : facilityPlaces) {
                initializePlaceMediaCounters(place, idfacility);
            }
        }
    }

    private void initializePlaceMediaCounters(Integer idfacility, Media media) {
        List<Place> facilityPlaces = null;
        if (idfacility != null) {
            facilityPlaces = placeDao.getFacilityPlaces(idfacility);
        }

        if (facilityPlaces != null && !facilityPlaces.isEmpty()) {
            for (Place place : facilityPlaces) {
                MediaCounter counter = MediaCounterMapper.mapNewMediaCounterEntity(place, media, MediaCounterMapper.Type.PRIVATE);
                counterDao.add(counter);
            }
        }
    }

    /**
     * Prepares Media Counters data within database for Place in method parameter
     *
     * @param place      Place to be updated with counters
     * @param idfacility facility ID
     */
    @Override
    public void initializePlaceMediaCounters(Place place, Integer idfacility) {
        List<Media> facilityMedia = null;
        List<MediaCounter> counters = null;
        MediaCounter counter;
        if (idfacility != null) {
            facilityMedia = mediaDao.getFacilityMedia(idfacility);
        }

        if (place != null && place.getIdplace() != null
                && facilityMedia != null && !facilityMedia.isEmpty()) {
            counters = new ArrayList<MediaCounter>();

            for (Media medium : facilityMedia) {
                counter = MediaCounterMapper.mapNewMediaCounterEntity(place, medium, MediaCounterMapper.Type.PRIVATE);
                counters.add(counter);
            }

            if (!counters.isEmpty()) {
                for (MediaCounter newCounter : counters) {
                    counterDao.add(newCounter);
                }
            }

        }

    }

    /**
     * Prepares List of Media for specified Facility
     *
     * @param id Facility ID
     * @return mediaDtos List of Facility Media
     */
    @Override
    public List<MediaDto> getFacilityMediaAsDtos(Integer id) {
        FacilityDto facilityDto = estateService.getFacilityDtoById(id);
        List<MediaDto> mediaDtos = new ArrayList<MediaDto>();
        List<Media> facilityMedia = mediaDao.getFacilityMedia(id);
        if (facilityDto != null && facilityMedia != null && !facilityMedia.isEmpty()) {
            for (Media media : facilityMedia) {
                mediaDtos.add(MediaMapper.mapEntityToMediaDto(media, facilityDto));
            }
        }

        return mediaDtos;
    }

    /**
     * Prepares MediaDto for specified Medium
     *
     * @param ide Facility ID
     * @param idm Medium ID
     * @return mediaDto
     */
    @Override
    public MediaDto getMediaDtoByFacilityAndMediumIds(Integer ide, Integer idm) {
        MediaDto mediaDto = null;
        Media media = mediaDao.find(idm);
        if (media != null) {
            FacilityDto facilityDto = estateService.getFacilityDtoById(ide);
            if (facilityDto != null && facilityContainsMedium(media, ide)) {
                mediaDto = MediaMapper.mapEntityToMediaDto(media, facilityDto);
            }
        }

        return mediaDto;
    }

    /**
     * Prepares map of Facilities with their defined Media
     *
     * @return mediaMap Key: FacilityDto Value: List of Media for specified Facility
     */
    @Override
    public Map<FacilityDto, List<MediaDto>> getAllMediaDefined() {
        Map<FacilityDto, List<MediaDto>> mediaMap = new HashMap<>();
        for (FacilityDto facility : estateService.getFacilitiesAsDtos()) {
            mediaMap.put(facility, getFacilityMediaAsDtos(facility.getIdfacility()));
        }
        return mediaMap;
    }

    /**
     * Prepares List of maps including all Places and it's media counters
     *
     * @return allCountersMapsList
     * TODO: find better solution
     */
    @Override
    public List<Map<PlaceDto, List<MediaCounterDto>>> getAllCountersDefined() {
        List<Map<PlaceDto, List<MediaCounterDto>>> allCountersMapsList = new ArrayList<>();

        for (FacilityDto facility : estateService.getFacilitiesAsDtos()) {
            Map<PlaceDto, List<MediaCounterDto>> countersMap = new HashMap<>();
            for (PlaceDto place : placeService.getFacilityPlacesAsDtos(facility)) {
                countersMap.put(place, getPlaceMediaCountersAsDtos(place.getPlaceid()));
            }
            allCountersMapsList.add(countersMap);
        }

        return allCountersMapsList;
    }

    /**
     * delete medium
     *
     * @param idm Medium ID
     */
    @Override
    public void deleteMedium(Integer idm) {
        mediaDao.remove(mediaDao.find(idm));
    }

    /**
     * prepares Map of Facilities with their Media Counters for all Common counters
     *
     * @return commonMediaCounters Map
     */
    @Override
    public Map<FacilityDto, List<MediaCounterDto>> getAllCommonCountersDefined() {
        Map<FacilityDto, List<MediaCounterDto>> commonMediaCounters = new HashMap<>();
        for (FacilityDto facility : estateService.getFacilitiesAsDtos()) {
            commonMediaCounters.put(facility, getFacilityCommontMediaCountersAsDtos(facility.getIdfacility()));
        }
        return commonMediaCounters;
    }

    /**
     * Prepares List of Common Media Counters for specified Facility
     *
     * @param ide Facility ID
     * @return commonMediaCounters List
     */
    @Override
    public List<MediaCounterDto> getFacilityCommontMediaCountersAsDtos(Integer ide) {
        List<MediaCounterDto> commonMediaCounters = new ArrayList<>();
        List<Media> facilityMedia = mediaDao.getFacilityMedia(ide);
        if (facilityMedia != null && !facilityMedia.isEmpty()) {
            for (Media medium : facilityMedia) {
                MediaDto mediumDto = MediaMapper.mapEntityToMediaDto(medium);
                for (MediaCounter counter : counterDao.getFacilityCommonMediaCounters(medium.getIdmedia())) {
                    commonMediaCounters.add(MediaCounterMapper.mapEntityToMediaCounterDto(counter, mediumDto));
                }
            }
        }
        return commonMediaCounters;
    }

    /**
     * Prepares Facility Media definitions within database
     *
     * @param mediaTypes
     * @param facility
     * @return boolean success
     */
    @Override
    public Boolean resolveFacilityMedia(List<Integer> mediaTypes, Facility facility) {
        if (mediaTypes != null && !mediaTypes.isEmpty()) {
            Media medium;
            for (int i = 0; i < mediaTypes.size(); i++) {
                medium = MediaMapper.mapNewMedia(facility, mediaTypes.get(i));
                mediaDao.add(medium);
                addNewCommonMediaCounter(medium);
            }
            return true;
        }
        return false;
    }

    /**
     * Checks whether Facility has the same Media specified
     *
     * @param newMedium  Media checked
     * @param facilityId Facility ID
     * @return boolean true if facillity has the same media on it's list
     */
    private boolean facilityContainsMedium(Media newMedium, Integer facilityId) {
        List<Media> facilityMedia = mediaDao.getFacilityMedia(facilityId);
        if (facilityMedia != null && !facilityMedia.isEmpty()) {
            for (Media medium : facilityMedia) {
                if (newMedium.getIdmedia() != null && newMedium.getIdmedia().equals(medium.getIdmedia())
                        || newMedium.getType().equals(medium.getType())) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Generates new Media Counter for specified Media
     *
     * @param newMedium Media
     */
    private void addNewCommonMediaCounter(Media newMedium) {
        MediaCounter counter = new MediaCounter(newMedium, newMedium.getType());
        counter.setDateRegistered(new Date());
        counter.setCounterType(MediaCounterMapper.Type.COMMON.name());
        String registryNumber = newMedium.getType().concat(String.valueOf(newMedium.getIdmedia().intValue())
                .concat(String.valueOf(newMedium.getFacility().getIdfacility())).concat(new Date().toString())).replaceAll("\\s", "");
        counter.setRegistryNumber(registryNumber);
        counterDao.add(counter);
    }

    /**
     * Prepares update on Media definition for specified estate
     *
     * @param dto MediaDto
     * @param ide Facility ID
     * @param idm Media ID
     * @return boolean true if success
     */
    @Override
    public Boolean prepareFacilityMediumUpdate(MediaDto dto, Integer ide, Integer idm) {
        Media media = mediaDao.find(idm);
        List<Media> facilityMedias = mediaDao.getFacilityMedia(ide);
        Facility facility = facilityDao.find(ide);
        if (facility != null && media != null && facilityMedias != null && !facilityMedias.isEmpty()) {
            for (Media mediaFacility : facilityMedias) {
                if (media.getIdmedia().equals(mediaFacility.getIdmedia())) {
                    Media updated = MediaMapper.mapMediaDtoToEntity(dto, media, facility);
                    mediaDao.update(updated);
                    return true;
                }
            }
        }

        return false;
    }
}
