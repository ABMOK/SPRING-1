package pl.com.mnemonic.dao.implementation;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.com.mnemonic.dao.interfaces.UserRoleDaoInterface;
import pl.com.mnemonic.entity.SystemUserRoles;

@Repository("userRoleDao")
public class UserRoleDaoImplementation extends HibernateGenericDaoImplementation<SystemUserRoles, Integer>
		implements UserRoleDaoInterface {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<SystemUserRoles> getUserRolesByUserId(Integer idu) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SystemUserRoles.class);
		criteria.add(Restrictions.eq("systemUsers.idSystemUser", idu));
		return (List<SystemUserRoles>) criteria.list();
	}

}
