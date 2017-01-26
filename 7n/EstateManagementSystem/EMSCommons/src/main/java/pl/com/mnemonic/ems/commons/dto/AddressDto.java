package pl.com.mnemonic.ems.commons.dto;


import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AddressDto {

	private Integer idaddress;
	private String country;
	private String countryCode;

	@NotEmpty
	@NotNull
	private String cityName;
	@NotEmpty
	@NotNull
	@Size(min = 6)
	@Pattern(regexp =  "^(\\d{2}-?\\d{3})$" , message = "Province code validation failed.")
	private String cityCode;
	@NotEmpty
	@NotNull
	private String streetName;
	@NotEmpty
	private String districtName;
	@NotEmpty
	@NotNull
	@NumberFormat
	private String buildingNo;
	@NotEmpty
	@NotNull
	@NumberFormat
	private String officeNo;

	public AddressDto() {
	}

	//x
	public AddressDto(Integer id, String country, String countryCode, String cityCode, String cityName,
			String districtName, String streetName, String buildingNo, String officeNo) {
		this.idaddress = id;
		this.country = country;
		this.countryCode = countryCode;
		this.cityCode = cityCode;
		this.cityName = cityName;
		this.districtName = districtName;
		this.streetName = streetName;
		this.buildingNo = buildingNo;
		this.officeNo = officeNo;
	}

	public AddressDto(String country, String countryCode, String cityCode, String cityName, String districtName,
			String streetName, String buildingNo, String officeNo) {
		this.country = country;
		this.countryCode = countryCode;
		this.cityCode = cityCode;
		this.cityName = cityName;
		this.districtName = districtName;
		this.streetName = streetName;
		this.buildingNo = buildingNo;
		this.officeNo = officeNo;
	}
	
	public AddressDto(String country, String countryCode, String cityCode, String cityName, String districtName,
			String streetName, String buildingNo) {
		this.country = country;
		this.countryCode = countryCode;
		this.cityCode = cityCode;
		this.cityName = cityName;
		this.districtName = districtName;
		this.streetName = streetName;
		this.buildingNo = buildingNo;
	}

	public Integer getIdaddress() {
		return idaddress;
	}

	public void setIdaddress(Integer idaddress) {
		this.idaddress = idaddress;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getBuildingNo() {
		return buildingNo;
	}

	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
	}

	public String getOfficeNo() {
		return officeNo;
	}

	public void setOfficeNo(String officeNo) {
		this.officeNo = officeNo;
	}

}
