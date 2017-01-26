package pl.com.mnemonic.ems.dao.implementation;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.com.mnemonic.ems.dao.interfaces.MediaCounterDaoInterface;
import pl.com.mnemonic.ems.entity.MediaCounter;

@Repository("counterDao")
public class MediaCounterDaoImplementation extends HibernateGenericDaoImplementation<MediaCounter, Integer> implements MediaCounterDaoInterface{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<MediaCounter> getPlaceMediaCounters(int idplace) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(MediaCounter.class);
		criteria.add(Restrictions.eq("place.idplace", idplace));
		return (List<MediaCounter>) criteria.list();
	}

	@Override
	public List<MediaCounter> getFacilityCommonMediaCounters(int idmedia) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(MediaCounter.class);
		criteria.add(Restrictions.eq("media.idmedia", idmedia));
		criteria.add(Restrictions.eq("counterType", "COMMON"));
		return (List<MediaCounter>) criteria.list();
	}

}
