package pl.com.dao.interfaces;

import java.util.List;

import pl.com.model.ContactDto;

public interface ContactDaoInterface {
	public void addContact(ContactDto contact);
	public List<ContactDto> getContacts();
	public ContactDto getContact(int Id);
	public void deleteContact(int Id);
	public void saveOrUpdate(ContactDto contact);
}
