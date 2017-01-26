package pl.com.mnemonic.ems.dao.implementation;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.com.mnemonic.ems.dao.interfaces.PlaceUserDaoInterface;
import pl.com.mnemonic.ems.entity.Placeuser;

@Repository("placeUserDao")
public class PlaceUserDaoImplementation extends HibernateGenericDaoImplementation<Placeuser, Integer> 
implements PlaceUserDaoInterface{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Placeuser> getOwners(int idPlace) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Placeuser.class);
		criteria.add(Restrictions.eq("place.idplace", idPlace));
		criteria.add(Restrictions.eq("usageType", "OWNER"));
		return (List<Placeuser>) criteria.list();
	}

	@Override
	public List<Placeuser> getTenants(int idPlace) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Placeuser.class);
		criteria.add(Restrictions.eq("place.idplace", idPlace));
		criteria.add(Restrictions.eq("usageType", "RENTER"));
		return (List<Placeuser>) criteria.list();
	}
}
