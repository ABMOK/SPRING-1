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
import pl.com.mnemonic.entity.SystemRole;
import pl.com.mnemonic.entity.SystemUserRole;
import pl.com.mnemonic.entity.SystemUser;
import pl.com.mnemonic.mapper.SystemUserMapper;
import pl.com.mnemonic.services.interfaces.SpringRoleManagerInterface;

@Service("roleManager")
public class SpringRoleManagerImplementation implements SpringRoleManagerInterface {

	@Autowired
	GenericDaoInterface<SystemUser, Integer> systemUserDao;

	@Autowired
	UserRoleDaoInterface userRoleDao;

	@Autowired
	GenericDaoInterface<SystemRole, Integer> roleDao;

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

		List<SystemUser> daoList = systemUserDao.list();

		if (daoList != null && !daoList.isEmpty()) {

			for (SystemUser systemUser : daoList) {
				userList.add(readUser(systemUser.getIdSystemUser()));
			}
		}

		return userList;
	}

	@Override
	public void saveUser(UserDto userDto) {
		SystemUser systemUser = SystemUserMapper.map(userDto);

		if (systemUser.getIdSystemUser() != null) {
			systemUserDao.update(systemUser);
		} else {
			systemUserDao.add(systemUser);
		}

		SystemUserRole systemUserRole;
		SystemRole systemRole;

		for (SystemUserRole userRoles : userRoleDao.getUserRolesByUserId(systemUser.getIdSystemUser())) {
			userRoleDao.remove(userRoles);
		}

		if (userDto.getUserRoles() != null) {

			for (UserRole role : userDto.getUserRoles()) {
				systemRole = roleDao.find(role.getRoleCode());
				systemUserRole = new SystemUserRole(systemRole, systemUser);
				userRoleDao.add(systemUserRole);
			}
		}

	}

	@Override
	public void deleteUser(Integer userId) {
		SystemUser systemUser = systemUserDao.find(userId);

		for (SystemUserRole userRoles : userRoleDao.getUserRolesByUserId(userId)) {
			userRoleDao.remove(userRoles);
		}

		systemUserDao.remove(systemUser);
	}

	@Override
	public UserDto readUser(Integer id) {
		Set<UserRole> userRole = new HashSet<>();
		UserDto userToEdit = SystemUserMapper.map((SystemUser) systemUserDao.find(id));

		for (SystemUserRole userRoles : userRoleDao.getUserRolesByUserId(id)) {
			userRole.add(UserRole.fromRoleCode(userRoles.getSystemRoles().getIdSystemRoles()));
		}

		userToEdit.setUserRoles(userRole);
		return userToEdit;
	}
}
