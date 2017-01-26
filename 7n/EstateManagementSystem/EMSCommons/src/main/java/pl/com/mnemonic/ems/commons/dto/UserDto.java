package pl.com.mnemonic.ems.commons.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;
import pl.com.mnemonic.ems.commons.enums.SystemUserType;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

public class UserDto {

	private Integer iduser;
	@NotEmpty
	@NotNull
	@Size(min=2)
	private String name;
	@NotEmpty
	@NotNull
	@Size(min=2)
	private String surname;
	@NotEmpty
	@Email
	private String mail;
	@NotEmpty
	@NotNull
	@Size(min=2)
	private String login;
	@NotEmpty
	@NotNull
	@Size(min=2)
	private String password;
	@NumberFormat
	@Digits(integer = 20, fraction =0)
	private String phoneNo;
	private Date lastLogDate;
	private Integer placeUserId;
	private Date regdate;
	@NotEmpty
	private List<SystemUserType> myRoles;

	public UserDto() {
	}

	public Integer getIduser() {
		return iduser;
	}

	public void setIduser(Integer iduser) {
		this.iduser = iduser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public Date getLastLogDate() {
		return lastLogDate;
	}

	public void setLastLogDate(Date lastLogDate) {
		this.lastLogDate = lastLogDate;
	}

	public List<SystemUserType> getMyRoles() {
		return myRoles;
	}

	public void setMyRoles(List<SystemUserType> myRoles) {
		this.myRoles = myRoles;
	}
	public Integer getPlaceUserId() {
		return placeUserId;
	}

	public void setPlaceUserId(Integer placeUserId) {
		this.placeUserId = placeUserId;
	}
}
