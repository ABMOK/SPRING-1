package pl.com.mnemonic.dao.interfaces;

import java.util.List;

import pl.com.mnemonic.entity.SystemUserRole;

public interface UserRoleDaoInterface extends GenericDaoInterface<SystemUserRole, Integer>{
	List<SystemUserRole> getUserRolesByUserId(Integer idu);
}
