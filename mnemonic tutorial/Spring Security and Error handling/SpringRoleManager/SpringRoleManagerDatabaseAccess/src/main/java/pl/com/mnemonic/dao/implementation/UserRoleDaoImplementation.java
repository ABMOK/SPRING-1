package pl.com.mnemonic.dao.implementation;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.com.mnemonic.dao.interfaces.UserRoleDaoInterface;
import pl.com.mnemonic.entity.SystemUserRole;

@Repository("userRoleDao")
public class UserRoleDaoImplementation extends HibernateGenericDaoImplementation<SystemUserRole, Integer>
		implements UserRoleDaoInterface {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<SystemUserRole> getUserRolesByUserId(Integer idu) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SystemUserRole.class);
		criteria.add(Restrictions.eq("systemUsers.idSystemUser", idu));
		return (List<SystemUserRole>) criteria.list();
	}

}
