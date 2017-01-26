package pl.com.mnemonic.ems.dao.implementation;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.com.mnemonic.ems.dao.interfaces.UserDaoInterface;
import pl.com.mnemonic.ems.dao.interfaces.UserRoleDaoInterface;
import pl.com.mnemonic.ems.entity.User;
import pl.com.mnemonic.ems.entity.UserRole;

import java.util.List;


@Repository("userDao")
public class UserDaoImplementation extends HibernateGenericDaoImplementation<User, Integer> implements UserDaoInterface {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    UserRoleDaoInterface userRoleDao;

    public UserDaoImplementation() {
    }

    public UserDaoImplementation(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void adduser(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    @Transactional
    public List<User> getUsers() {
        return (List<User>) sessionFactory.getCurrentSession().createCriteria(User.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    @Transactional
    public User getUser(int iduser) {
        int idusera = iduser;
        return (User) sessionFactory.getCurrentSession().get(User.class, iduser);
    }


    @Override
    @Transactional
    public void deleteUser(int iduser) {
        //sessionFactory.getCurrentSession().createQuery("DELETE FROM User WHERE id = "+iduser).executeUpdate();
        User user = getUser(iduser);
        List<UserRole> userRoles = userRoleDao.getUserRolesByUserId(iduser);
        if (user!=null && userRoles != null && !userRoles.isEmpty()) {
            for (UserRole usRole : userRoles) {
                userRoleDao.remove(usRole);
            }
            user.setUserRoles(null);
            saveOrUpdate(user);
        }
        sessionFactory.getCurrentSession().delete(getUser(iduser));
    }

    @Override
    @Transactional
    public boolean deleteUserNotify(int iduser) {
        //sessionFactory.getCurrentSession().createQuery("DELETE FROM User WHERE id = "+iduser).executeUpdate();
        try {
            sessionFactory.getCurrentSession().delete(getUser(iduser));
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    @Override
    public void saveOrUpdate(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);

    }

    @Override
    public User findByMail(String mail) {
        User user = (User) sessionFactory.getCurrentSession().createCriteria(User.class).add(Restrictions.eq("mail", mail))
                .uniqueResult();
        return user;
    }

    @Override
    public List<User> getUserAccountsByPlaceUserId(int idPlaceuser) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("placeuser.idplaceuser", idPlaceuser));
        criteria.addOrder(Order.desc("regdate"));
        return (List<User>) criteria.list();
    }

}
