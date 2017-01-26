package pl.com.mnemonic.ems.commons.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.util.Date;


public class MediaDto {
	private Integer idmedia;
	private String type;
	private String medianame;
	@NotNull
	@NotEmpty
	@Digits(integer = 20, fraction =2)
	private String price;
	private String measureunit;
	private Date dateregistered;
	private FacilityDto facilityDto;
	
	public MediaDto(){}
	
	public MediaDto(FacilityDto facility){
		this.facilityDto = facility;
	}
	
	//x
	public MediaDto(Integer idmedia, FacilityDto facility, String type, String medianame, String price, String measureunit, 
			Date dateregistered) {
		this.type = type;
		this.medianame = medianame;
		this.price = price;
		this.measureunit = measureunit;
		this.dateregistered = dateregistered;
		this.facilityDto = facility;
		this.idmedia = idmedia;
	}
	
	public MediaDto(Integer idmedia, String type, String medianame, String price, String measureunit, Date dateregistered) {
		this.type = type;
		this.medianame = medianame;
		this.price = price;
		this.measureunit = measureunit;
		this.dateregistered = dateregistered;
		this.idmedia = idmedia;
	}

	public Integer getIdmedia() {
		return idmedia;
	}

	public void setIdmedia(Integer idmedia) {
		this.idmedia = idmedia;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMedianame() {
		return medianame;
	}

	public void setMedianame(String medianame) {
		this.medianame = medianame;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getMeasureunit() {
		return measureunit;
	}

	public void setMeasureunit(String measureunit) {
		this.measureunit = measureunit;
	}

	public Date getDateregistered() {
		return dateregistered;
	}

	public void setDateregistered(Date dateregistered) {
		this.dateregistered = dateregistered;
	}

	public FacilityDto getFacilityDto() {
		return facilityDto;
	}

	public void setFacilityDto(FacilityDto facility) {
		this.facilityDto = facility;
	}
	

}
