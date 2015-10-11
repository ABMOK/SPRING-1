package pl.com.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import pl.com.dao.interfaces.ContactDaoInterface;
import pl.com.model.ContactDto;


@Controller
@SessionAttributes("contact")
@RequestMapping(value = { "/contacts"})
public class HibernateContactController {
	
	@Autowired
	private ContactDaoInterface contactDao;
	private List<ContactDto> contactList;
	private ContactDto contactModel;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView contactListModel() {
		ModelAndView view = new ModelAndView("contacts");
		contactList = contactDao.getContacts();
		if (contactList != null) {
			view.addObject("contactList", contactList);
			if (contactModel != null && contactModel.getId()>0) {
				view.addObject("contact", contactModel);
			} else {
				view.addObject("contact", new ContactDto());
			}
		} else {
			contactList = new ArrayList<ContactDto>();
			view.addObject("contactList", contactList);
			view.addObject("contact", new ContactDto());
		}
		return view;
	}

	@RequestMapping(value = "/contactlist", method = RequestMethod.POST)
	public ModelAndView listModelSubmit(
			@ModelAttribute("contact") ContactDto contact, BindingResult result) {
		if (contact != null && contact.getId()>0) {
			contactDao.saveOrUpdate(contact);
		} else {
			contactDao.saveOrUpdate(contact);
		}
		contactList = contactDao.getContacts();
		ModelAndView view = new ModelAndView("contacts");
		view.addObject("contactList", contactList);
		view.addObject("contact", new ContactDto());
		view.setViewName("redirect:/contacts.html");
		return view;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editlistElement(@PathVariable Integer id) {
		ModelAndView view = new ModelAndView("contacts");
		ContactDto contact = contactDao.getContact(id.intValue());
		view.addObject("contact", contact);
		view.setViewName("redirect:/contacts.html");
		return view;

	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteFromList(@PathVariable Integer id) {
		ModelAndView view = new ModelAndView("contacts");
		ContactDto contact = new ContactDto();
		for (ContactDto contactDto : contactDao.getContacts()) {
			if (contactDto.getId() == id) {
				contact = contactDto;
			}
		}
		if (contact != null && contact.getId() > 0) {
			contactDao.deleteContact(id.intValue());
		}
		view.addObject("listAccounts", contactDao.getContacts());
		view.setViewName("redirect:/contacts.html");
		return view;

	}

}
