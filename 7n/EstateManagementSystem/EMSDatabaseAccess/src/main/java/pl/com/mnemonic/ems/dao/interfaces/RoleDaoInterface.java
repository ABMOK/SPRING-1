package pl.com.mnemonic.ems.dao.interfaces;

import pl.com.mnemonic.ems.entity.Role;


public interface RoleDaoInterface extends GenericDaoInterface<Role,Integer>{

	Role findRoleByCode(String usageType);

}
