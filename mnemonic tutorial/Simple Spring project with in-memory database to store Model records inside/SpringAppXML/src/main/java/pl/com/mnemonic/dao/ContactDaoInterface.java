package pl.com.mnemonic.dao;

import java.util.List;

import pl.com.mnemonic.model.ContactDto;

public interface ContactDaoInterface {
	public void addContact(ContactDto contact);

	public List<ContactDto> getContacts();

	public ContactDto getContact(int Id);

	public void deleteContact(int Id);

	public void saveOrUpdate(ContactDto contact);
}
