package pl.com.mnemonic.ems.service.implementation;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.mnemonic.ems.commons.dto.MediaCounterDto;
import pl.com.mnemonic.ems.commons.dto.MediaDto;
import pl.com.mnemonic.ems.commons.dto.PlaceUserDto;
import pl.com.mnemonic.ems.commons.dto.UserDto;
import pl.com.mnemonic.ems.dao.interfaces.MediaCounterDaoInterface;
import pl.com.mnemonic.ems.dao.interfaces.MediaDaoInterface;
import pl.com.mnemonic.ems.dao.interfaces.PlaceDaoInterface;
import pl.com.mnemonic.ems.entity.Media;
import pl.com.mnemonic.ems.entity.MediaCounter;
import pl.com.mnemonic.ems.entity.Place;
import pl.com.mnemonic.ems.mapper.implementation.MediaCounterMapper;
import pl.com.mnemonic.ems.mapper.implementation.MediaMapper;
import pl.com.mnemonic.ems.service.interfaces.FinanceServiceInterface;
import pl.com.mnemonic.ems.service.interfaces.TenantsServiceInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("financeService")
public class FinanceServiceImplementation implements FinanceServiceInterface {
    static final Logger LOGGER = Logger.getLogger(FinanceServiceImplementation.class);

    @Autowired
    PlaceDaoInterface placeDao;
    @Autowired
    MediaCounterDaoInterface counterDao;
    @Autowired
    MediaDaoInterface mediaDao;
    @Autowired
    TenantsServiceInterface tenantsService;

     /**
     * Generates list of MediaCounterDtos
     *
     * @param ide Facility ID
     * @param idp Place ID
     * @return mediaCounters MediaCounterDto list
     */
    @Override
    public List<MediaCounterDto> generateMediaCountersRestart(Integer ide, Integer idp) {
        List<MediaCounterDto> mediaCounters = new ArrayList<MediaCounterDto>();
        List<Media> facilityMediaList = mediaDao.getFacilityMedia(ide);
        MediaCounterDto mediaCounterDto;
        MediaDto mediumDto;
        List<MediaCounter> mediaCountersEntity = counterDao.getPlaceMediaCounters(idp);

        if (facilityMediaList != null && !facilityMediaList.isEmpty()) {
            for (Media medium : facilityMediaList) {
                if (mediaCountersEntity != null && !mediaCountersEntity.isEmpty()) {
                    for (MediaCounter counter : mediaCountersEntity) {
                        if (counter.getMedia().equals(medium)) {
                            mediumDto = MediaMapper.mapEntityToMediaDto(mediaDao.find(counter.getMedia().getIdmedia()));
                            mediaCounterDto = MediaCounterMapper.mapEntityToMediaCounterDto(counter, mediumDto);
                            mediaCounters.add(mediaCounterDto);
                        } else {
                            mediumDto = MediaMapper.mapEntityToMediaDto(medium);
                            mediaCounterDto = new MediaCounterDto();
                            // mediaCounter.setCounterType(counterType);
                            mediaCounterDto.setMediaType(medium.getType());
                            mediaCounterDto.setMediaDto(mediumDto);
                            mediaCounterDto.setDateRegistered(new Date());
                            mediaCounterDto.setDateUpdated(new Date());
                            mediaCounterDto.setTotalAmount("");
                            mediaCounters.add(mediaCounterDto);
                        }
                    }
                }
            }
        }

/*
        if (mediaCountersEntity != null && !mediaCountersEntity.isEmpty()) {
			for (MediaCounter counter : mediaCountersEntity) {
				mediumDto = mediaMapper.mapEntityToMediaDto(mediaDao.find(counter.getMedia().getIdmedia()));
				mediaCounterDto = counterMapper.mapEntityToMediaCounterDto(counter, mediumDto);
				mediaCounters.add(mediaCounterDto);
			}
		} else {
			for (Media medium : facilityMediaList) {
				mediumDto = mediaMapper.mapEntityToMediaDto(medium);
				mediaCounterDto = new MediaCounterDto();
				// mediaCounter.setCounterType(counterType);
				mediaCounterDto.setMediaType(medium.getType());
				mediaCounterDto.setMediaDto(mediumDto);
				mediaCounterDto.setDateRegistered(new Date());
				mediaCounterDto.setDateUpdated(new Date());
				mediaCounterDto.setTotalAmount("");
				mediaCounters.add(mediaCounterDto);
			}
		}
*/
        return mediaCounters;
    }

    /**
     * Performs update on Place Media Counters with data from MediaCounterDto List for specified Place
     *
     * @param mediaCounters MediaCounterDto List
     * @param ide           Facility ID
     * @param idp           Place ID
     */
    @Override
    public void updatePlaceCounters(List<MediaCounterDto> mediaCounters, Integer ide, Integer idp) {
        MediaCounter mediaCounter = null;
        if (mediaCounters != null && !mediaCounters.isEmpty()) {
            for (MediaCounterDto mediaCounterDto : mediaCounters) {
                if (mediaCounterDto.getIdmediaCounter() != null && mediaCounterDto.getIdmediaCounter() > 0) {
                    mediaCounter = MediaCounterMapper.mapDtoToMediaCounterEntity(mediaCounterDto, counterDao.find(mediaCounterDto.getIdmediaCounter()));
                    counterDao.update(mediaCounter);
                } else {
                    if (mediaCounterDto.getMediaDto() != null && mediaCounterDto.getMediaDto().getIdmedia() != null) {
                        Media medium = mediaDao.find(mediaCounterDto.getMediaDto().getIdmedia());
                        if (idp != null && idp > 0) {

                            Place place = placeDao.find(idp);
                            if (place != null) {
                                mediaCounter = MediaCounterMapper.mapDtoToMediaCounterEntity(mediaCounterDto, place, medium, MediaCounterMapper.Type.PRIVATE);
                            }

                        } else {
                            mediaCounter = MediaCounterMapper.mapDtoToMediaCounterEntity(mediaCounterDto, null, medium, MediaCounterMapper.Type.COMMON);
                        }

                        if (mediaCounter != null) {
                            counterDao.add(mediaCounter);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void updatePlaceCounters(List<MediaCounterDto> mediaCounters, UserDto userDto) {
        PlaceUserDto placeuser = tenantsService.getTenantDtoById(userDto.getPlaceUserId());
        Place place = placeDao.find(placeuser.getPlaceDto().getId());
        updatePlaceCounters(mediaCounters, place.getFacility().getIdfacility(), place.getIdplace());
    }


	/*@Override
    public List<ReportDto> getAllTenantReports(Integer idt) {
		return getAllTenantReports(tenantService.getTenantDtoById(idt));
	}*/

}
