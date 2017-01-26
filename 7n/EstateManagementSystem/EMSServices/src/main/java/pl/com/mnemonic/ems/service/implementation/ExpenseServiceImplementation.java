package pl.com.mnemonic.ems.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.mnemonic.ems.commons.dto.ExpenseDto;
import pl.com.mnemonic.ems.commons.dto.FacilityDto;
import pl.com.mnemonic.ems.commons.dto.MediaCounterDto;
import pl.com.mnemonic.ems.commons.enums.CommonExpenseType;
import pl.com.mnemonic.ems.dao.interfaces.ExpenseDaoInterface;
import pl.com.mnemonic.ems.dao.interfaces.FacilityDaoInterface;
import pl.com.mnemonic.ems.entity.Expense;
import pl.com.mnemonic.ems.entity.Facility;
import pl.com.mnemonic.ems.mapper.implementation.ExpenseMapper;
import pl.com.mnemonic.ems.service.interfaces.EstateServiceInterface;
import pl.com.mnemonic.ems.service.interfaces.ExpenseServiceInterface;
import pl.com.mnemonic.ems.service.interfaces.MediaServiceInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("expenseService")
public class ExpenseServiceImplementation implements ExpenseServiceInterface {


    @Autowired
    ExpenseDaoInterface expenseDao;
    @Autowired
    EstateServiceInterface estateService;
    @Autowired
    FacilityDaoInterface facilityDao;
    @Autowired
    MediaServiceInterface mediaService;

    /**
     * Retrieves list od Facility expenses for specified Facility
     *
     * @param ide Facility ID
     * @return expenseDtos List of Facility expenses
     */
    @Override
    public List<ExpenseDto> getCommonExpensesList(Integer ide) {
        List<ExpenseDto> expenseDtos = new ArrayList<ExpenseDto>();
        ExpenseDto expenseDto;
        FacilityDto facilityDto = estateService.getFacilityDtoById(ide);
        if (facilityDto != null) {
            List<Expense> facilityExpenses = expenseDao.getFacilityExpenses(ide);
            if (facilityExpenses != null && !facilityExpenses.isEmpty()) {
                for (Expense expense : facilityExpenses) {
                    expenseDto = ExpenseMapper.mapEntityToDto(expense, facilityDto);
                    expenseDtos.add(expenseDto);
                }
            }
        }
        return expenseDtos;
    }

    /**
     * Creates new Expense entity for specified Facility with data from ExpenseDto
     *
     * @param expenseDto ExpenseDto
     * @param ide        Facility ID
     * @return boolean true if success
     */
    @Override
    public Boolean saveNewExpense(ExpenseDto expenseDto, Integer ide) {
        Facility facility = facilityDao.find(ide);
        Expense expense;
        if (facility != null && expenseDto != null) {
            if (expenseDto.getIdexpense() != null && expenseDto.getIdexpense() > 0) {
                Expense forUpdate = expenseDao.find(expenseDto.getIdexpense());
                expense = ExpenseMapper.mapExpenseDtoToEntity(expenseDto, forUpdate, facility);
            } else {
                expense = ExpenseMapper.mapExpenseDtoToEntity(expenseDto, facility);
            }

            if (expense != null) {
                if (!facilityContainsUniqueExpense(expense, ide)) {
                    expenseDao.add(expense);
                    return true;
                }

                if (expense.getIdexpense() != null && expense.getIdexpense() > 0 && isUpdateOnUniqueExpense(expense, ide)) {
                    expenseDao.update(expense);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks whether Facility has the same expense already specified
     *
     * @param newExpense Expense expense checked
     * @param ide        Facility ID
     * @return boolean true if expense exists on facility list
     */
    private boolean facilityContainsUniqueExpense(Expense newExpense, Integer ide) {
        List<Expense> facilityExpenses = expenseDao.getFacilityExpenses(ide);
        if (facilityExpenses != null && !facilityExpenses.isEmpty()) {
            for (Expense expense : facilityExpenses) {
                if (newExpense.getIdexpense() != null && newExpense.getIdexpense().equals(expense.getIdexpense())
                        || newExpense.getName() != null
                        && (!newExpense.getName().equals(CommonExpenseType.ANOTHER.name()) && newExpense.getName().equals(expense.getName()))) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Determines whether Expense is about to be updated for specified Facility
     *
     * @param newExpense Expense controlled
     * @param ide        Facility ID
     * @return boolean true if Facility contains same expense
     */
    private boolean isUpdateOnUniqueExpense(Expense newExpense, Integer ide) {
        List<Expense> facilityExpenses = expenseDao.getFacilityExpenses(ide);
        Expense uniqueExpense = null;
        if (facilityExpenses != null && !facilityExpenses.isEmpty()) {
            for (Expense expense : facilityExpenses) {
                if (expense.getName().equals(newExpense.getName())) {
                    uniqueExpense = expense;
                }
            }
            if (uniqueExpense == null) {
                return true;
            }
            if (newExpense.getIdexpense().equals(uniqueExpense.getIdexpense())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Creates map of all Facilities and their expenses
     *
     * @return expenseMap Key:FacilityDto value:List<ExpenseDto>
     */
    @Override
    public Map<FacilityDto, List<ExpenseDto>> getAllExpensesDefined() {
        Map<FacilityDto, List<ExpenseDto>> expenseMap = new HashMap<>();
        for (FacilityDto facility : estateService.getFacilitiesAsDtos()) {
            expenseMap.put(facility, getCommonExpensesList(facility.getIdfacility()));
        }
        return expenseMap;
    }

    /**
     * Prepares Facility Expenses based on expenseTypes list
     * @param expenseTypes list of Integer values for ExpenseTypes
     * @param facility Facility entity
     * @return boolean true if created
     */
    @Override
    public Boolean resolveFacilityServices(List<Integer> expenseTypes, Facility facility) {
        if (expenseTypes != null && !expenseTypes.isEmpty() && facility != null) {
            Expense expense;
            for (int i = 0; i < expenseTypes.size(); i++) {
                expense = ExpenseMapper.mapNewExpense(facility, expenseTypes.get(i));
                expenseDao.add(expense);
            }
            return true;
        }
        return false;
    }

    /**
     * Prepares ExpenseDto for specified Expense ID
     * @param ide Estate ID
     * @param idexp Expense ID
     * @return expenseDto ExpenseDto
     */
    @Override
    public ExpenseDto getExpenseDto(Integer ide, Integer idexp) {
        ExpenseDto expenseDto = null;
        FacilityDto facilityDto = estateService.getFacilityDtoById(ide);
        Expense expense = expenseDao.find(idexp);
        if (expense != null && facilityDto != null && facilityContainsUniqueExpense(expense, ide)) {
            expenseDto = ExpenseMapper.mapEntityToDto(expense, facilityDto);
        }
        return expenseDto;
    }

    @Override
    public void deleteExpense(Integer idexp) {
        expenseDao.remove(expenseDao.find(idexp));
    }

    //TODO: specify if needed
    private List<ExpenseDto> getFacilityMediaCountersAsExpense(Integer idfacility) {
        List<ExpenseDto> expenses = null;
        List<MediaCounterDto> commonCounters = mediaService.getFacilityCommontMediaCountersAsDtos(idfacility);
        if (commonCounters != null && !commonCounters.isEmpty()) {
            ExpenseDto expense;
            expenses = new ArrayList<>();
            for (MediaCounterDto mediaCounter : commonCounters) {
               // expense = ExpenseMapper.mapCounterToExpense(mediaCounter);
               // expenses.add(expense);
            }
        }
        return expenses;
    }

	/*
    @Override
	public List<TransactionDto> getAllTenantTransactions(int idt) {
		List<TransactionDto> transactions = new ArrayList<TransactionDto>();
		PlaceUserDto tenant = getTenantDtoById(idt);
		for(Transaction transaction:transactionDao.listUserTransactions(idt)){
			transactions.add(transactionMapper.mapEntityToTransactionDto(transaction, tenant));
		}
		return transactions;
	}

	



	*/


}
