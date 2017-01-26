package pl.com.mnemonic.ems.commons.dto;

import org.springframework.format.annotation.NumberFormat;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class FacilityDto {
	private Integer idfacility;
	@Size(min = 0, max = 999)
	private String description;
	private String type;
	private List<PlaceDto> places;

	@NotNull
	@Valid
	private AddressDto addressDto;
	@NumberFormat
	@Digits(integer = 20, fraction =0)
	private Integer area;
	@NumberFormat
	@Digits(integer = 20, fraction =0)
	private Integer numberOfPlaces;
	private List<Integer> mediaTypes;
	private List<Integer> expenseTypes;
	private List<MediaCounterDto> mediaCounters;

	//public String getFullInfo() {
//		return this.addressDto.getStreetName()+" , "
//				+this.addressDto.getBuildingNo() ;
//	}

//	private String fullInfo;
	

	public FacilityDto() {
	}

	//x
	public FacilityDto(Integer id, String description, String type, Integer area) {
		this.idfacility = id;
		this.description = description;
		this.type = type;
		this.area = area;
	}

	public Integer getIdfacility() {
		return idfacility;
	}

	public void setIdfacility(Integer idfacility) {
		this.idfacility = idfacility;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	public List<PlaceDto> getPlaces() {
		return places;
	}

	public void setPlaces(List<PlaceDto> places) {
		this.places = places;
	}

	public AddressDto getAddressDto() {
		return addressDto;
	}

	public void setAddressDto(AddressDto addressDto) {
		this.addressDto = addressDto;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public List<Integer> getMediaTypes() {
		return mediaTypes;
	}

	public void setMediaTypes(List<Integer> mediaTypes) {
		this.mediaTypes = mediaTypes;
	}

	public List<Integer> getExpenseTypes() {
		return expenseTypes;
	}

	public void setExpenseTypes(List<Integer> expenseTypes) {
		this.expenseTypes = expenseTypes;
	}

	public Integer getNumberOfPlaces() {
		return numberOfPlaces;
	}

	public void setNumberOfPlaces(Integer numberOfPlaces) {
		this.numberOfPlaces = numberOfPlaces;
	}
	public List<MediaCounterDto> getMediaCounters() {
		return mediaCounters;
	}

	public void setMediaCounters(List<MediaCounterDto> mediaCounters) {
		this.mediaCounters = mediaCounters;
	}
}
