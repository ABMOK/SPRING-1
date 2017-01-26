package pl.com.mnemonic.ems.commons.dto;

import org.springframework.format.annotation.NumberFormat;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlaceDto {
	private Integer placeid;
	@Valid
	@NotNull
	private AddressDto address;
	private FacilityDto facility;
	@Size(min = 0, max = 999)
	private String description;
	private List<MediaCounterDto> mediaCounters;
	private Set<PlaceUserDto> placeusers = new HashSet<PlaceUserDto>(0);
	@NumberFormat
	@Digits(integer = 20, fraction =0)
	private Integer area;
	@NumberFormat
	@Digits(integer = 20, fraction =0)
	private Integer residentCount;
	
	
	
	public PlaceDto(){}

	public PlaceDto(FacilityDto facility, AddressDto address){
		this.facility = facility;
		this.address = address;
	}

	public PlaceDto(Integer placeid, AddressDto addressDto, FacilityDto facilityDto, String description, Integer area, Integer residentCount){
		this.placeid = placeid;
		this.address = addressDto;
		this.facility = facilityDto;
		this.description = description;
		this.area = area;
		this.residentCount = residentCount;
	}
	
	public PlaceDto(Integer placeid, AddressDto addressDto, String description){
		this.placeid = placeid;
		this.address = addressDto;
		this.description = description;
	}
	
	public Integer getId() {
		return placeid;
	}
	public void setId(Integer id) {
		this.placeid = id;
	}
	public AddressDto getAddress() {
		return address;
	}
	public void setAddress(AddressDto address) {
		this.address = address;
	}
	public FacilityDto getFacility() {
		return facility;
	}
	public void setFacility(FacilityDto facility) {
		this.facility = facility;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<PlaceUserDto> getPlaceusers() {
		return placeusers;
	}
	public void setPlaceusers(Set<PlaceUserDto> placeusers) {
		this.placeusers = placeusers;
	}

	public Integer getPlaceid() {
		return placeid;
	}

	public void setPlaceid(Integer placeid) {
		this.placeid = placeid;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public Integer getResidentCount() {
		return residentCount;
	}

	public void setResidentCount(Integer residentCount) {
		this.residentCount = residentCount;
	}
	public List<MediaCounterDto> getMediaCounters() {
		return mediaCounters;
	}
	public void setMediaCounters(List<MediaCounterDto> mediaCounters) {
		this.mediaCounters = mediaCounters;
	}
	
}
