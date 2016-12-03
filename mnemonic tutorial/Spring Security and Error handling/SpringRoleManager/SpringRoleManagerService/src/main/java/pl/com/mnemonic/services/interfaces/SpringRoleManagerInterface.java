package pl.com.mnemonic.services.interfaces;

import pl.com.mnemonic.dto.UserDto;

public interface SpringRoleManagerInterface {

	String welcome();

	Object getAllUsers();

	void saveUser(UserDto userDto);

	void deleteUser(Integer userDto);

	UserDto readUser(Integer id);
}
