package pl.com.mnemonic.ems.dao.implementation;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.com.mnemonic.ems.dao.interfaces.AddressDaoInterface;
import pl.com.mnemonic.ems.entity.Address;

@Repository("addressDao")
public class AddressDaoImplementation extends HibernateGenericDaoImplementation<Address, Integer> implements AddressDaoInterface  {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Address> getSimilarAddressByCriteria(Map<String, String> criterias) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Address.class);
		criteria.add(Restrictions.eq("cityName", criterias.get("cityName")));
		criteria.add(Restrictions.eq("streetName", criterias.get("streetName")));
		criteria.add(Restrictions.eq("buildingNo", criterias.get("buildingNo")));
	
		return (List<Address>) criteria.list();
	}
	

}
