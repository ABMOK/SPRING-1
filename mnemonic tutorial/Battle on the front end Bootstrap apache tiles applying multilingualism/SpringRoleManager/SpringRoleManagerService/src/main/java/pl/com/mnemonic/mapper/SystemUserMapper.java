package pl.com.mnemonic.mapper;

import pl.com.mnemonic.dto.UserDto;
import pl.com.mnemonic.entity.SystemUser;

public class SystemUserMapper {

	public static UserDto map(SystemUser user) {
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

	public static SystemUser map(UserDto userDto) {
		SystemUser systemUser = new SystemUser();

		if (userDto != null) {
			systemUser.setIdSystemUser(userDto.getUserId());
			systemUser.setEmail(userDto.getEmail());
			systemUser.setLastName(userDto.getLastName());
			systemUser.setName(userDto.getFirstName());
			systemUser.setPassword(userDto.getPassword());

		}

		return systemUser;
	}

}
