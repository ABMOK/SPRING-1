package pl.com.mnemonic.dao.implementation;

import org.springframework.stereotype.Repository;

import pl.com.mnemonic.entity.SystemUsers;

@Repository("systemUserDao")
public class SystemUserDaoImplementation extends HibernateGenericDaoImplementation<SystemUsers, Integer> {

}
