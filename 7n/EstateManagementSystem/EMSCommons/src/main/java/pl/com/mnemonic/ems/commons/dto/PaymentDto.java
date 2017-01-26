package pl.com.mnemonic.ems.commons.dto;

import java.util.Date;

public class PaymentDto {
	private Integer idpayment;
	private PlaceDto place;
	private String type;
	private Date duedate;
	private Date dateregistered;
	private String description;
	private String amount;
	private String account;
	
	public PaymentDto(){}

	public Integer getIdpayment() {
		return idpayment;
	}

	public void setIdpayment(Integer idpayment) {
		this.idpayment = idpayment;
	}

	public PlaceDto getPlace() {
		return place;
	}

	public void setPlace(PlaceDto place) {
		this.place = place;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDuedate() {
		return duedate;
	}

	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}

	public Date getDateregistered() {
		return dateregistered;
	}

	public void setDateregistered(Date dateregistered) {
		this.dateregistered = dateregistered;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
}
