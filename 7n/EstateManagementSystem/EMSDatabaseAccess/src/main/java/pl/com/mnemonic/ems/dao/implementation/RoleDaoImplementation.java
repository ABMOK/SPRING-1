package pl.com.mnemonic.ems.dao.implementation;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.com.mnemonic.ems.dao.interfaces.RoleDaoInterface;
import pl.com.mnemonic.ems.entity.Placeuser;
import pl.com.mnemonic.ems.entity.Role;

@Repository("roleDao")
public class RoleDaoImplementation extends HibernateGenericDaoImplementation<Role, Integer> implements RoleDaoInterface  {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Role findRoleByCode(String usageType) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Role.class);
		criteria.add(Restrictions.eq("roleCode", usageType));
		return ((List<Role>) criteria.list()).get(0);
	}

}
