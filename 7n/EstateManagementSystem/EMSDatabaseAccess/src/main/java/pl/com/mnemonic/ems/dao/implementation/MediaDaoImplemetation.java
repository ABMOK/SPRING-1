package pl.com.mnemonic.ems.dao.implementation;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.com.mnemonic.ems.dao.interfaces.MediaDaoInterface;
import pl.com.mnemonic.ems.entity.Media;
import pl.com.mnemonic.ems.entity.Place;

@Repository("mediaDao")
public class MediaDaoImplemetation extends HibernateGenericDaoImplementation<Media, Integer> implements MediaDaoInterface{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Media> getFacilityMedia(int idFacility) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Media.class);
		criteria.add(Restrictions.eq("facility.idfacility", idFacility));
		return (List<Media>) criteria.list();
	}
}
