package pl.com.mnemonic.dao.implementation;

import org.springframework.stereotype.Repository;

import pl.com.mnemonic.entity.SystemRole;

@Repository("roleDao")
public class RoleDaoImplementation extends HibernateGenericDaoImplementation<SystemRole, Integer> {

}
