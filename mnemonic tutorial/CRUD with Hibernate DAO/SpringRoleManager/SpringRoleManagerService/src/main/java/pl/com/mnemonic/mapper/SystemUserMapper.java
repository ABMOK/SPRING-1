package pl.com.mnemonic.mapper;

import pl.com.mnemonic.dto.UserDto;
import pl.com.mnemonic.entity.SystemUsers;

public class SystemUserMapper {

	public static UserDto map(SystemUsers user) {
		UserDto userDto = new UserDto();

		if (user != null) {

			userDto.setUserId(user.getIdSystemUser());
			userDto.setEmail(user.getEmail());
			userDto.setFirstName(user.getName());
			userDto.setLastName(user.getLastName());
			userDto.setPassword(user.getPassword());

		}

		return userDto;
	}

	public static SystemUsers map(UserDto userDto) {
		SystemUsers systemUsers = new SystemUsers();

		if (userDto != null) {
			systemUsers.setIdSystemUser(userDto.getUserId());
			systemUsers.setEmail(userDto.getEmail());
			systemUsers.setLastName(userDto.getLastName());
			systemUsers.setName(userDto.getFirstName());
			systemUsers.setPassword(userDto.getPassword());

		}

		return systemUsers;
	}

}
