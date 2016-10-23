package pl.com.mnemonic.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import pl.com.mnemonic.model.ContactDto;

public class ContactDao implements ContactDaoInterface {

	private SessionFactory sessionFactory;

	public ContactDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void addContact(ContactDto contact) {
		sessionFactory.getCurrentSession().save(contact);
	}

	@Transactional
	public List<ContactDto> getContacts() {
		return (List<ContactDto>) sessionFactory.getCurrentSession().createCriteria(ContactDto.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

	}

	@Transactional
	public ContactDto getContact(int Id) {
		return (ContactDto) sessionFactory.getCurrentSession().get(ContactDto.class, Id);
	}

	@Transactional
	public void deleteContact(int Id) {
		sessionFactory.getCurrentSession().createQuery("DELETE FROM ContactDto WHERE id = " + Id).executeUpdate();

	}

	@Transactional
	public void saveOrUpdate(ContactDto contact) {
		sessionFactory.getCurrentSession().saveOrUpdate(contact);
	}
}
