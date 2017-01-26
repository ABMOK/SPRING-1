package pl.com.mnemonic.ems.commons.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


public class PlaceUserDto {
	private Integer idplaceuser;
	private PlaceDto placeDto;
	private String usageType;
	private Date startdate;
	private Date enddate;
	@Valid
	@NotNull
	private UserDto userDto;
	private List<MediaCounterDto> mediaCounters;

	public PlaceUserDto(){}
	
	public PlaceUserDto(Integer idplaceuser, PlaceDto placeDto, String usageType, Date startdate, Date enddate, UserDto systemUser){
		this.idplaceuser = idplaceuser;
		this.placeDto = placeDto;
		this.usageType = usageType;
		this.startdate = startdate;
		this.enddate = enddate;
		this.userDto = systemUser;
	}
	
	//x
	public PlaceUserDto(PlaceDto placeDto, UserDto user){
		this.placeDto = placeDto;
		this.userDto =user;
	}
	
	public PlaceUserDto(PlaceDto placeDto){
		this.placeDto = placeDto;		
	}

	public Integer getIdplaceuser() {
		return idplaceuser;
	}

	public void setIdplaceuser(Integer idplaceuser) {
		this.idplaceuser = idplaceuser;
	}

	public PlaceDto getPlaceDto() {
		return placeDto;
	}

	public void setPlaceDto(PlaceDto place) {
		this.placeDto = place;
	}

	public String getUsageType() {
		return usageType;
	}

	public void setUsageType(String usageType) {
		this.usageType = usageType;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

	public List<MediaCounterDto> getMediaCounters() {
		return mediaCounters;
	}

	public void setMediaCounters(List<MediaCounterDto> mediaCounters) {
		this.mediaCounters = mediaCounters;
	}
	
	
}
