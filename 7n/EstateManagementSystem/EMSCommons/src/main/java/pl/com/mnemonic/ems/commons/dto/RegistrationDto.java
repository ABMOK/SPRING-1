package pl.com.mnemonic.ems.commons.dto;

import javax.validation.constraints.NotNull;

public class RegistrationDto {

	@NotNull
	private UserDto user;
	@NotNull
	private AddressDto address;
	
	public RegistrationDto(){}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public AddressDto getAddress() {
		return address;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
	}
}
