package pl.com.mnemonic.services.implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.com.mnemonic.dao.interfaces.GenericDaoInterface;
import pl.com.mnemonic.dao.interfaces.UserRoleDaoInterface;
import pl.com.mnemonic.dto.UserDto;
import pl.com.mnemonic.dto.UserRole;
import pl.com.mnemonic.entity.SystemRoles;
import pl.com.mnemonic.entity.SystemUserRoles;
import pl.com.mnemonic.entity.SystemUsers;
import pl.com.mnemonic.mapper.SystemUserMapper;
import pl.com.mnemonic.services.interfaces.SpringRoleManagerInterface;

@Service("roleManager")
public class SpringRoleManagerImplementation implements SpringRoleManagerInterface {

	@Autowired
	GenericDaoInterface<SystemUsers, Integer> systemUserDao;

	@Autowired
	UserRoleDaoInterface userRoleDao;

	@Autowired
	GenericDaoInterface<SystemRoles, Integer> roleDao;

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
		userList = new ArrayList<UserDto>();

		List<SystemUsers> daoList = systemUserDao.list();

		if (daoList != null && !daoList.isEmpty()) {

			for (SystemUsers systemUsers : daoList) {
				userList.add(readUser(systemUsers.getIdSystemUser()));
			}
		}

		return userList;
	}

	@Override
	public void saveUser(UserDto userDto) {
		SystemUsers systemUser = SystemUserMapper.map(userDto);

		if (systemUser.getIdSystemUser() != null) {
			systemUserDao.update(systemUser);
		} else {
			systemUserDao.add(systemUser);
		}

		SystemUserRoles systemUserRole;
		SystemRoles systemRole;

		for (SystemUserRoles userRoles : userRoleDao.getUserRolesByUserId(systemUser.getIdSystemUser())) {
			userRoleDao.remove(userRoles);
		}

		if (userDto.getUserRoles() != null) {

			for (UserRole role : userDto.getUserRoles()) {
				systemRole = roleDao.find(role.getRoleCode());
				systemUserRole = new SystemUserRoles(systemRole, systemUser);
				userRoleDao.add(systemUserRole);
			}
		}

	}

	@Override
	public void deleteUser(Integer userId) {
		SystemUsers systemUser = systemUserDao.find(userId);

		for (SystemUserRoles userRoles : userRoleDao.getUserRolesByUserId(userId)) {
			userRoleDao.remove(userRoles);
		}

		systemUserDao.remove(systemUser);
	}

	@Override
	public UserDto readUser(Integer id) {
		Set<UserRole> userRole = new HashSet<>();
		UserDto userToEdit = SystemUserMapper.map((SystemUsers) systemUserDao.find(id));

		for (SystemUserRoles userRoles : userRoleDao.getUserRolesByUserId(id)) {
			userRole.add(UserRole.fromRoleCode(userRoles.getSystemRoles().getIdSystemRoles()));
		}

		userToEdit.setUserRoles(userRole);
		return userToEdit;
	}
}
