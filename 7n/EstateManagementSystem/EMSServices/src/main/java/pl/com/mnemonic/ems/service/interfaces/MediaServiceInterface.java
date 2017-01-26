package pl.com.mnemonic.ems.service.interfaces;

import pl.com.mnemonic.ems.commons.dto.*;
import pl.com.mnemonic.ems.entity.Facility;
import pl.com.mnemonic.ems.entity.Place;

import java.util.List;
import java.util.Map;

public interface MediaServiceInterface {
	public Map<FacilityDto, List<MediaDto>> getAllMediaDefined();
	public List<MediaDto> getFacilityMediaAsDtos(Integer id);
	public MediaDto getMediaDtoByFacilityAndMediumIds(Integer ide, Integer idm);
	public MediaDto getNewMediaDtoByFacilityId(Integer ide);
	public Boolean addNewMedium(MediaDto dto, Integer ide);
	public void deleteMedium(Integer idm);
	public Boolean resolveFacilityMedia(List<Integer> mediaTypes, Facility facility);
	public Boolean prepareFacilityMediumUpdate(MediaDto dto, Integer ide, Integer idm);

	
	
	public List<Map<PlaceDto, List<MediaCounterDto>>> getAllCountersDefined();
	public List<MediaCounterDto> getPlaceMediaCountersAsDtos(Integer idp);
	public List<MediaCounterDto> getPlaceMediaCountersAsDtos(UserDto dto);
	public Map<FacilityDto, List<MediaCounterDto>> getAllCommonCountersDefined();
	public List<MediaCounterDto> getFacilityCommontMediaCountersAsDtos(Integer ide);
	public void initializePlaceMediaCounters(Integer idfacility);
	public void initializePlaceMediaCounters(Place place, Integer idfacility);

}
