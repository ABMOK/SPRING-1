package pl.com.mnemonic.ems.dao.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pl.com.mnemonic.ems.dao.interfaces.GenericDaoInterface;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;


@Transactional
public class HibernateGenericDaoImplementation<E, K extends Serializable> implements GenericDaoInterface<E, K> {

    @Autowired
    private SessionFactory sessionFactory;
    protected Class<? extends E> entityDaoType;

    @SuppressWarnings("unchecked")
    public HibernateGenericDaoImplementation() {
        entityDaoType = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }


    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void add(E entity) {
        currentSession().save(entity);
    }

    @Override
    public void update(E entity) {
        currentSession().saveOrUpdate(entity);
    }

    @Override
    public void remove(E entity) {
        currentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public E find(K key) {
        return (E) currentSession().get(entityDaoType, key);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> list() {
        return currentSession().createCriteria(entityDaoType).list();

    }


}
