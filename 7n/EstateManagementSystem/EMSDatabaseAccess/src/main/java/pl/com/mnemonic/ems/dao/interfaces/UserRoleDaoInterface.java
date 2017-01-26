package pl.com.mnemonic.ems.dao.interfaces;

import pl.com.mnemonic.ems.entity.UserRole;

import java.util.List;

public interface UserRoleDaoInterface extends GenericDaoInterface<UserRole,Integer>{

   public List<UserRole> getUserRolesByUserId(Integer idu);
}
