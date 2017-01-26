package pl.com.mnemonic.ems.classified.service.interfaces;

import pl.com.mnemonic.ems.commons.dto.AdministrationDto;
import pl.com.mnemonic.ems.commons.dto.UserDto;
import pl.com.mnemonic.ems.entity.Placeuser;
import pl.com.mnemonic.ems.entity.User;

public interface UserServiceInterface {
	public UserDto getLoggedUserDto();
	public UserDto getCurrentPlaceUserAccount(Integer idPlaceUser);
	public User updateUser(UserDto user, String role, Placeuser tenant);
	public AdministrationDto getAllSystemUsers();

	public void saveNewUser(AdministrationDto adminDto);

	public void deleteUser(Integer id);

	public AdministrationDto getUser(Integer idu);
}
