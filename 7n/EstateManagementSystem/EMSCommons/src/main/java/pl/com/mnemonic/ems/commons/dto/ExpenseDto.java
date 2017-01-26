package pl.com.mnemonic.ems.commons.dto;

import org.hibernate.validator.constraints.NotEmpty;
import pl.com.mnemonic.ems.commons.enums.HappeningOccurence;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.util.Date;


public class ExpenseDto {

	private Integer idexpense;
	private PaymentDto payment;
	private MediaCounterDto mediaCounter;
	private FacilityDto facility;
	private String type;
	private String name;
	private Date registered;
	@NotEmpty
	@NotNull
	@Digits(integer = 20, fraction =0)
	private String amount;
	private String description;
	private HappeningOccurence occurence;
	private Integer calculationPeriod;

	public ExpenseDto( Integer idexpense, FacilityDto facility, String type, String name, Date registered, String amount, 
			String description, HappeningOccurence occurence, Integer calculationPeriod) {
		this.idexpense = idexpense;
		this.facility = facility;
		this.type = type;
		this.name = name;
		this.registered = registered;
		this.amount = amount;
		this.description = description;
		this.occurence = occurence;
		this.calculationPeriod = calculationPeriod;
	}
	
	public ExpenseDto() {
	}

	public Integer getIdexpense() {
		return idexpense;
	}

	public void setIdexpense(Integer idexpense) {
		this.idexpense = idexpense;
	}

	public PaymentDto getPayment() {
		return payment;
	}

	public void setPayment(PaymentDto payment) {
		this.payment = payment;
	}

	public MediaCounterDto getMediaCounter() {
		return mediaCounter;
	}

	public void setMediaCounter(MediaCounterDto mediaCounter) {
		this.mediaCounter = mediaCounter;
	}

	public FacilityDto getFacility() {
		return facility;
	}

	public void setFacility(FacilityDto facility) {
		this.facility = facility;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getRegistered() {
		return registered;
	}

	public void setRegistered(Date registered) {
		this.registered = registered;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public HappeningOccurence getOccurence() {
		return occurence;
	}

	public void setOccurence(HappeningOccurence occurence) {
		this.occurence = occurence;
	}

	public Integer getCalculationPeriod() {
		return calculationPeriod;
	}

	public void setCalculationPeriod(Integer calculationPeriod) {
		this.calculationPeriod = calculationPeriod;
	}
	

}
