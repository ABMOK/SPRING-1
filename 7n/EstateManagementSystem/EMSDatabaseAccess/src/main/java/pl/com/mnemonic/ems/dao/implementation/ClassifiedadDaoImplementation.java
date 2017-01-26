package pl.com.mnemonic.ems.dao.implementation;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.com.mnemonic.ems.dao.interfaces.ClassifiedadDaoInterface;
import pl.com.mnemonic.ems.entity.Classifiedad;
import pl.com.mnemonic.ems.entity.Expense;

@Repository("classifiedadDao")
public class ClassifiedadDaoImplementation extends HibernateGenericDaoImplementation<Classifiedad, Integer> implements ClassifiedadDaoInterface  {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Classifiedad> getAdsByCategory(Integer categoryId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Classifiedad.class);
		criteria.add(Restrictions.eq("category.idcategory", categoryId));
		return (List<Classifiedad>) criteria.list();
	}

}
