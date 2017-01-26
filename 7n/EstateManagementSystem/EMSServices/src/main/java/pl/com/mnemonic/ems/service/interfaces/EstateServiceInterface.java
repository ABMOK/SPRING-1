package pl.com.mnemonic.ems.service.interfaces;

import pl.com.mnemonic.ems.commons.dto.FacilityDto;

import java.util.List;

public interface EstateServiceInterface {
	public List<FacilityDto> validateDuplicateFacility(FacilityDto dto);
	public void addFacility(FacilityDto dto);
	public List<FacilityDto> getFacilitiesAsDtos();
	public FacilityDto getFacilityDtoById(int id);
	public void deleteEstate(Integer ide);
}
