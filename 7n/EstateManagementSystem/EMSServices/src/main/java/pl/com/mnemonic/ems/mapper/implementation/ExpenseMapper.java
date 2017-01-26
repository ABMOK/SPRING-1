package pl.com.mnemonic.ems.mapper.implementation;

import pl.com.mnemonic.ems.commons.dto.ExpenseDto;
import pl.com.mnemonic.ems.commons.dto.FacilityDto;
import pl.com.mnemonic.ems.commons.enums.CommonExpenseType;
import pl.com.mnemonic.ems.commons.enums.HappeningOccurence;
import pl.com.mnemonic.ems.commons.enums.HappeningType;
import pl.com.mnemonic.ems.commons.parsers.EMSUtilsParser;
import pl.com.mnemonic.ems.entity.Expense;
import pl.com.mnemonic.ems.entity.Facility;

import java.util.Date;

/**
 * Created by mnemonic on 2016-02-21.
 */
public class ExpenseMapper {

    /**
     * Prepares ExpenseDto including data from Expense entity for specified Facility
     *
     * @param entity      Expense entity
     * @param facilityDto FacilityDto
     * @return expenseDto ExpenseDto
     */
    public static ExpenseDto mapEntityToDto(Expense entity, FacilityDto facilityDto) {
        ExpenseDto expenseDto = new ExpenseDto(entity.getIdexpense(), facilityDto, entity.getType(), entity.getName(), entity.getRegistered(),
                String.valueOf(entity.getAmount()), entity.getDescription(), HappeningOccurence.fromString(entity.getOccurence()), entity.getCalculationPeriod());
        return expenseDto;
    }

    /**
     * Creates new Expense entity with data from ExpenseDto for specified Facility
     *
     * @param expenseDto ExpenseDto
     * @param facility   Facility entity
     * @return expense Expense entity
     */
    public static Expense mapExpenseDtoToEntity(ExpenseDto expenseDto, Facility facility) {
        Expense expense = new Expense();
        if (expenseDto.getIdexpense() != null && expenseDto.getIdexpense() > 0) {
            expense.setIdexpense(expenseDto.getIdexpense());
        }
        expense.setAmount(EMSUtilsParser.parseToDouble(expenseDto.getAmount()));
        expense.setCalculationPeriod(expenseDto.getCalculationPeriod());
        expense.setDescription(expenseDto.getDescription());
        expense.setFacility(facility);
        expense.setName(expenseDto.getName());
        expense.setOccurence(expenseDto.getOccurence().name());
        expense.setRegistered(new Date());
        expense.setType(expenseDto.getType());
        return expense;
    }

    /**
     * Updates Expense entity with data from ExpenseDto for specified Facility
     *
     * @param expenseDto ExpenseDto
     * @param facility   Facility entity
     * @return expense Expense entity
     */
    public static Expense mapExpenseDtoToEntity(ExpenseDto expenseDto, Expense expense, Facility facility) {
        if (expense == null || expense.getIdexpense() == null) {
            return mapExpenseDtoToEntity(expenseDto, facility);
        }
        if(expenseDto.getIdexpense()!=null && expenseDto.getIdexpense()>0){
            expense.setIdexpense(expenseDto.getIdexpense());
        }
        expense.setIdexpense(expenseDto.getIdexpense());
        expense.setFacility(facility);
        expense.setType(expenseDto.getType());
        expense.setName(expenseDto.getName());
        expense.setAmount(EMSUtilsParser.parseToDouble(expenseDto.getAmount()));
        expense.setDescription(expenseDto.getDescription());
        expense.setOccurence(expenseDto.getOccurence().name());
        expense.setCalculationPeriod(expenseDto.getCalculationPeriod());
        expense.setRegistered(new Date());
        return expense;
    }

    /**
     * Creates new Expense entity for specified Facility and ExpenseType
     * @param facility Facility entity
     * @param expenseType Integer value of ExpenseType
     * @return Expense entity
     */
    public static Expense mapNewExpense(Facility facility, Integer expenseType) {
        Expense expense = new Expense();
        expense.setFacility(facility);
        if(expenseType!=null){
            expense.setType(HappeningType.REGULAR.name());
            expense.setName(CommonExpenseType.fromInteger(expenseType).name());
        }
        expense.setRegistered(new Date());
        return expense;
    }
}
