package pl.com.mnemonic.ems.dao.implementation;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.com.mnemonic.ems.dao.interfaces.ReportDaoInterface;
import pl.com.mnemonic.ems.entity.Place;
import pl.com.mnemonic.ems.entity.Report;

@Repository("reportDao")
public class ReportDaoImplementation extends HibernateGenericDaoImplementation<Report, Integer> implements ReportDaoInterface{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Report> getPlaceUserReports(int idTenant) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Report.class);
		criteria.add(Restrictions.eq("placeuser.idplaceuser", idTenant));
		return (List<Report>) criteria.list();
	}
}
