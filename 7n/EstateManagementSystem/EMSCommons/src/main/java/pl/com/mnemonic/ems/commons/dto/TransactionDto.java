package pl.com.mnemonic.ems.commons.dto;

import java.util.Date;


public class TransactionDto {
	private Integer idtransaction;
	private PlaceUserDto placeuser;
	private Date date;
	private String account;
	private String type;
	private Double amount;
	
	public TransactionDto(){}
	
	public TransactionDto(PlaceUserDto placeuser, Date date, String account, String type, Double amount) {
		this.placeuser = placeuser;
		this.date = date;
		this.account = account;
		this.type = type;
		this.amount = amount;
	}

	public Integer getIdtransaction() {
		return idtransaction;
	}

	public void setIdtransaction(Integer idtransaction) {
		this.idtransaction = idtransaction;
	}

	public PlaceUserDto getPlaceuser() {
		return placeuser;
	}

	public void setPlaceuser(PlaceUserDto placeuser) {
		this.placeuser = placeuser;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
}
