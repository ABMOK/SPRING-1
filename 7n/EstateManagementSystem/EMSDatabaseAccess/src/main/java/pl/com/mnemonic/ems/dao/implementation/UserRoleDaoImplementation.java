package pl.com.mnemonic.ems.dao.implementation;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.com.mnemonic.ems.dao.interfaces.UserRoleDaoInterface;
import pl.com.mnemonic.ems.entity.UserRole;

import java.util.List;

@Repository("userroleDao")
public class UserRoleDaoImplementation extends HibernateGenericDaoImplementation<UserRole, Integer> implements UserRoleDaoInterface {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<UserRole> getUserRolesByUserId(Integer idUser) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UserRole.class);
        criteria.add(Restrictions.eq("user.iduser", idUser));
        return (List<UserRole>) criteria.list();
    }
}
