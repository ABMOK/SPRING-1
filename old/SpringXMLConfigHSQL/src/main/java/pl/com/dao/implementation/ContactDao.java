package pl.com.dao.implementation;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import pl.com.dao.interfaces.ContactDaoInterface;
import pl.com.model.ContactDto;

public class ContactDao implements ContactDaoInterface {
	
private SessionFactory sessionFactory;
	
	public ContactDao (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	@Override
	@Transactional
	public void addContact(ContactDto contact) {
		sessionFactory.getCurrentSession().saveOrUpdate(contact);		
	}

	@Override
	@Transactional
	public List<ContactDto> getContacts() {
		return (List<ContactDto>) sessionFactory.getCurrentSession().createCriteria(ContactDto.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		
	}

	@Override
	@Transactional
	public ContactDto getContact(int Id) {
		return (ContactDto) sessionFactory.getCurrentSession().get(ContactDto.class, Id);  
	}

	@Override
	@Transactional
	public void deleteContact(int Id) {
		sessionFactory.getCurrentSession().createQuery("DELETE FROM ContactDto WHERE id = "+Id).executeUpdate();  
		
	}

	@Override
	@Transactional
	public void saveOrUpdate(ContactDto contact) {
		sessionFactory.getCurrentSession().saveOrUpdate(contact);
		
	}

}
