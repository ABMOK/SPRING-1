package pl.com.mnemonic.ems.dao.implementation;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.com.mnemonic.ems.dao.interfaces.CategoryDaoInterface;
import pl.com.mnemonic.ems.entity.Category;
@Repository("categoryDao")
public class CategoryDaoImplementation extends HibernateGenericDaoImplementation<Category, Integer> implements CategoryDaoInterface  {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Category getCategoryByCategoryName(String categoryName) {
		
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Category.class);
			criteria.add(Restrictions.eq("categoryName", categoryName));
			return ((List<Category>) criteria.list()).iterator().next();
		
	}

}
