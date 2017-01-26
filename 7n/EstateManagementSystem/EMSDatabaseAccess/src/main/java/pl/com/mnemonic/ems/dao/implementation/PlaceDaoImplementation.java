package pl.com.mnemonic.ems.dao.implementation;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.com.mnemonic.ems.dao.interfaces.PlaceDaoInterface;
import pl.com.mnemonic.ems.entity.Place;

@Repository("placeDao")
public class PlaceDaoImplementation extends HibernateGenericDaoImplementation<Place, Integer> 
implements PlaceDaoInterface{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Place> getFacilityPlaces(int idFacility) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Place.class);
		criteria.add(Restrictions.eq("facility.idfacility", idFacility));
		return (List<Place>) criteria.list();
	}
}
