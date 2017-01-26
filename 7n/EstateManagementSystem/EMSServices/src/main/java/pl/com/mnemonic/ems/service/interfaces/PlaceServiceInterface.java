package pl.com.mnemonic.ems.service.interfaces;

import pl.com.mnemonic.ems.commons.dto.FacilityDto;
import pl.com.mnemonic.ems.commons.dto.PlaceDto;
import pl.com.mnemonic.ems.entity.Address;
import pl.com.mnemonic.ems.entity.Facility;
import pl.com.mnemonic.ems.entity.Place;

import java.util.List;

public interface PlaceServiceInterface {
	public List<PlaceDto> getAllPlacesAsDtos();
	public PlaceDto getPlaceDtoByFacilityAndPlaceIds(Integer idf, Integer idp);
	public Boolean resolveFacilityPlaces(FacilityDto dto, Address address, Facility facility);
	void deletePlace(Integer ide, Integer idp);
	public Place retrievePlace(Integer idf, Integer idp);
	public PlaceDto getPlaceDtoByPlaceId(Integer id);
	public Place retrievePlace(int idp);
	public List<PlaceDto> getFacilityPlacesAsDtos(FacilityDto facilityDto);
	public void prepareFacilityPlacesUpdate(PlaceDto dto, Integer idFacility);
	public PlaceDto getPurePlaceDtoByFacilityId(Integer ide);
	public List<PlaceDto> validateDuplicatePlace(Integer ide, PlaceDto placeDto);
	
}
