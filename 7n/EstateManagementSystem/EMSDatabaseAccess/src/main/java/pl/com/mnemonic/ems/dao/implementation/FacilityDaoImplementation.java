package pl.com.mnemonic.ems.dao.implementation;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.com.mnemonic.ems.dao.interfaces.FacilityDaoInterface;
import pl.com.mnemonic.ems.entity.Facility;

@Repository("facilityDao")
public class FacilityDaoImplementation extends HibernateGenericDaoImplementation<Facility, Integer> 
implements FacilityDaoInterface{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Facility> getFacilityByAddressId(Integer addressId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Facility.class);
		criteria.add(Restrictions.eq("address.idaddress", addressId));
		
		return (List<Facility>) criteria.list();
	}

}
