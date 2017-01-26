package pl.com.mnemonic.ems.commons.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class ReportDto {
	private Integer idreport;
	private PlaceUserDto placeuser;
	private MediaCounterDto mediaCounterDto;
	private Date date;
	private String type;
	@NotNull
	@NotEmpty
	private String amount;
	private String comment;

	public ReportDto() {
	}
	
	public ReportDto(MediaCounterDto dto, String type, String comment) {
		this.mediaCounterDto = dto;
		this.type = type;
		this.comment = comment;
				
	}

	public ReportDto(Integer idreport, PlaceUserDto tenant, Date date, String type, String amount, String comment) {
		this.idreport = idreport;
		this.placeuser = tenant;
		this.date = date;
		this.type = type;
		this.amount = amount;
		this.comment = comment;
	}

	public Integer getIdreport() {
		return idreport;
	}

	public void setIdreport(Integer idreport) {
		this.idreport = idreport;
	}

	public PlaceUserDto getPlaceuser() {
		return placeuser;
	}

	public void setPlaceuser(PlaceUserDto placeuser) {
		this.placeuser = placeuser;
	}

	public MediaCounterDto getMediaCounterDto() {
		return mediaCounterDto;
	}

	public void setMediaCounterDto(MediaCounterDto mediaCounterDto) {
		this.mediaCounterDto = mediaCounterDto;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
