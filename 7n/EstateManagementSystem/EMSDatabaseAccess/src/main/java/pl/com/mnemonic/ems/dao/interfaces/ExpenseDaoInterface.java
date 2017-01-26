package pl.com.mnemonic.ems.dao.interfaces;

import java.util.List;

import pl.com.mnemonic.ems.entity.Expense;

public interface ExpenseDaoInterface extends GenericDaoInterface<Expense,Integer>{

	List<Expense> getFacilityExpenses(int idFacility);

}
