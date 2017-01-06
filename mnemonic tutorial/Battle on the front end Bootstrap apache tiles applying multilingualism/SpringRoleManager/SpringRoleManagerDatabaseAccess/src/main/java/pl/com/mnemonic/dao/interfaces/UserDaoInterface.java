package pl.com.mnemonic.dao.interfaces;

import pl.com.mnemonic.entity.SystemUser;

public interface UserDaoInterface extends GenericDaoInterface<SystemUser, Integer> {
	SystemUser findByMail(String email);
}
