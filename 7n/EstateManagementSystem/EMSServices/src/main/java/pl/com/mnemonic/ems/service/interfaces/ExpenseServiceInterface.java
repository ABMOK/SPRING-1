package pl.com.mnemonic.ems.service.interfaces;

import pl.com.mnemonic.ems.commons.dto.ExpenseDto;
import pl.com.mnemonic.ems.commons.dto.FacilityDto;
import pl.com.mnemonic.ems.entity.Facility;

import java.util.List;
import java.util.Map;

public interface ExpenseServiceInterface {
public Map<FacilityDto, List<ExpenseDto>> getAllExpensesDefined();
public List<ExpenseDto> getCommonExpensesList(Integer ide);
public Boolean saveNewExpense(ExpenseDto expenseDto, Integer ide);
public Boolean resolveFacilityServices(List<Integer> expenseTypes, Facility facility);
public ExpenseDto getExpenseDto(Integer ide, Integer idexp);

    public void deleteExpense(Integer idexp);
}
