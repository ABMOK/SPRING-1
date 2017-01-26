package pl.com.mnemonic.ems.service.interfaces;

import pl.com.mnemonic.ems.commons.dto.PlaceUserDto;
import pl.com.mnemonic.ems.commons.enums.PlaceUserType;

import java.util.List;


public interface TenantsServiceInterface {
public List<PlaceUserDto> getAllTenantsAsDto();
public List<PlaceUserDto> getAllPlaceOwnersAsDto();
public List<PlaceUserDto> getAllPlaceRentersAsDto();
//public List<PlaceUserDto> getCurrentResidentsAsDto(Integer idplace);
public PlaceUserDto getTenantDtoById(Integer id);
public PlaceUserDto getTenantDtoById(Integer id, Integer ide, Integer idp );
//x
public void saveTenantToPlace(PlaceUserDto dto, Integer idf, Integer idp);
public void updateTenant(PlaceUserDto dto, int idt);
//public List<TransactionDto> getAllTenantTransactions(int idt);
//public ReportDto getReportDtoByTenantId(int idt);
//public void addNewReport(ReportDto dto, int idt);


    public PlaceUserDto newPlaceUser(Integer idf, Integer idp, PlaceUserType type);//newOwnerOfThePlace + newTenantInThePlace

public List<PlaceUserDto> getAllRentersAsDto(Integer ide, Integer idp);
public List<PlaceUserDto> getAllOwnersAsDto(Integer ide, Integer idp);
public PlaceUserDto getCurrentPlaceOwner(Integer idf, Integer idp);

    public void deletePlaceUser(Integer idt);
}
