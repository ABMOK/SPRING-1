package pl.com.mnemonic.ems.dao.implementation;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.com.mnemonic.ems.dao.interfaces.CategoryAttributeDaoInterface;
import pl.com.mnemonic.ems.entity.CategoryAttribute;

@Repository("catAtDao")
public class CategoryAttributeDaoImplementation extends HibernateGenericDaoImplementation<CategoryAttribute, Integer> implements CategoryAttributeDaoInterface{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<CategoryAttribute> getListByAttributeId(Integer idAttribute) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CategoryAttribute.class);
		criteria.add(Restrictions.eq("attribute.idattribute", idAttribute.intValue()));
		return (List<CategoryAttribute>) criteria.list();
	}

	@Override
	public List<CategoryAttribute> getListByCategoryId(Integer idCategory) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CategoryAttribute.class);
		criteria.add(Restrictions.eq("category.idcategory", idCategory.intValue()));
		return (List<CategoryAttribute>) criteria.list();
	}
}
