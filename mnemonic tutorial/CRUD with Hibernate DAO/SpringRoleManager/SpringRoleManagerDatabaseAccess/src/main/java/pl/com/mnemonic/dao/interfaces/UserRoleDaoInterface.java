package pl.com.mnemonic.dao.interfaces;

import java.util.List;

import pl.com.mnemonic.entity.SystemUserRoles;

public interface UserRoleDaoInterface extends GenericDaoInterface<SystemUserRoles, Integer>{
	List<SystemUserRoles> getUserRolesByUserId(Integer idu);
}
