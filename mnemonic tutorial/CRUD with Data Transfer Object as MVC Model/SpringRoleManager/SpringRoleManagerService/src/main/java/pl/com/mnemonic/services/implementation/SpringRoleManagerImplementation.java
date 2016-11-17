package pl.com.mnemonic.services.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import pl.com.mnemonic.dto.UserDto;
import pl.com.mnemonic.services.interfaces.SpringRoleManagerInterface;

@Service("roleManager")
public class SpringRoleManagerImplementation implements SpringRoleManagerInterface {

	private List<UserDto> userList;

	public List<UserDto> getUserList() {
		return userList;
	}

	public void setUserList(List<UserDto> userList) {
		this.userList = userList;
	}

	@Override
	public String welcome() {
		return "H E L L O from service layer";
	}

	@Override
	public List<UserDto> getAllUsers() {

		if (userList == null) {
			userList = new ArrayList<UserDto>();
		}

		return userList;
	}

	@Override
	public void saveUser(UserDto userDto) {

		if (userDto.getUserId() != null) {
			deleteUser(userDto.getUserId());
		} else {
			Random generator = new Random();
			int i = generator.nextInt(10000);
			userDto.setUserId(i);
		}

		getAllUsers().add(userDto);
	}

	@Override
	public void deleteUser(Integer userId) {
		UserDto userToDelete = null;

		for (UserDto user : getAllUsers()) {

			if (user.getUserId().equals(userId)) {
				userToDelete = user;
			}
		}

		getAllUsers().remove(userToDelete);
	}

	@Override
	public UserDto readUser(Integer id) {
		UserDto userToEdit = null;

		for (UserDto user : getAllUsers()) {

			if (user.getUserId().equals(id)) {
				userToEdit = user;
			}
		}

		return userToEdit;
	}
}
