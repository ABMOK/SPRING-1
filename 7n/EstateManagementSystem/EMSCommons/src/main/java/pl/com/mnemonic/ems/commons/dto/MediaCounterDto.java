package pl.com.mnemonic.ems.commons.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class MediaCounterDto {
	private Integer idmediaCounter;
	private MediaDto mediaDto;
	private String mediaType;
	private String counterType;
	@NotEmpty
	@NotNull
	private String registryNumber;
	private Date dateRegistered;
	private Date dateUpdated;
	@NotEmpty
	@NotNull
	@Digits(integer = 20, fraction =2)
	private String totalAmount;
	private Set<ReportDto> reports = new HashSet<ReportDto>(0);

	public MediaCounterDto(){}
	
	public MediaCounterDto(Integer idmediaCounter, MediaDto mediaDto, String mediaType, String counterType, String registryNumber,
			Date dateRegistered, String totalAmount){
		this.idmediaCounter = idmediaCounter;
		this.mediaDto = mediaDto;
		this.mediaType = mediaType;
		this.counterType = counterType;
		this.registryNumber = registryNumber;
		this.dateRegistered = dateRegistered;
		this.totalAmount = totalAmount;
	}

	public MediaCounterDto(Integer idmediaCounter, String mediaType, String counterType, String registryNumber, Date dateRegistered, String totalAmount) {
		this.idmediaCounter = idmediaCounter;
		this.mediaType = mediaType;
		this.counterType = counterType;
		this.registryNumber = registryNumber;
		this.dateRegistered = dateRegistered;
		this.totalAmount = totalAmount;
	}

	public Integer getIdmediaCounter() {
		return idmediaCounter;
	}

	public void setIdmediaCounter(Integer idmediaCounter) {
		this.idmediaCounter = idmediaCounter;
	}

	public MediaDto getMediaDto() {
		return mediaDto;
	}

	public void setMediaDto(MediaDto mediaDto) {
		this.mediaDto = mediaDto;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public String getCounterType() {
		return counterType;
	}

	public void setCounterType(String counterType) {
		this.counterType = counterType;
	}

	public String getRegistryNumber() {
		return registryNumber;
	}

	public void setRegistryNumber(String registryNumber) {
		this.registryNumber = registryNumber;
	}

	public Date getDateRegistered() {
		return dateRegistered;
	}

	public void setDateRegistered(Date dateRegistered) {
		this.dateRegistered = dateRegistered;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Set<ReportDto> getReports() {
		return reports;
	}

	public void setReports(Set<ReportDto> reports) {
		this.reports = reports;
	}
	
}
