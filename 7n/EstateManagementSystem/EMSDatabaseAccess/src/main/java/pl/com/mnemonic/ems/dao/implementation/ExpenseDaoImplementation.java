package pl.com.mnemonic.ems.dao.implementation;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.com.mnemonic.ems.dao.interfaces.ExpenseDaoInterface;
import pl.com.mnemonic.ems.entity.Expense;

@Repository("expenseDao")
public class ExpenseDaoImplementation extends HibernateGenericDaoImplementation<Expense, Integer> implements ExpenseDaoInterface{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Expense> getFacilityExpenses(int idFacility) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Expense.class);
		criteria.add(Restrictions.eq("facility.idfacility", idFacility));
		return (List<Expense>) criteria.list();
	}
}
