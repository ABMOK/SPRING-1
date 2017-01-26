package pl.com.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class ContactDto implements Serializable{  

	private static final long serialVersionUID = 1L;  
	@Id
    @GeneratedValue	
	private Integer id;
	private String firstName;
	private String lastName;
	private int phoneNo;
	private String email;
	
	public ContactDto(){}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(int phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
