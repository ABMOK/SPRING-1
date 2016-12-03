package pl.com.mnemonic.dao.implementation;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.com.mnemonic.dao.interfaces.UserDaoInterface;
import pl.com.mnemonic.entity.SystemUser;

@Repository("systemUserDao")
public class SystemUserDaoImplementation extends HibernateGenericDaoImplementation<SystemUser, Integer>
		implements UserDaoInterface {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional(value = "transactionManager")
	public SystemUser findByMail(String email) {
		SystemUser user = (SystemUser) sessionFactory.getCurrentSession().createCriteria(SystemUser.class)
				.add(Restrictions.eq("email", email)).uniqueResult();

		return user;
	}
}
