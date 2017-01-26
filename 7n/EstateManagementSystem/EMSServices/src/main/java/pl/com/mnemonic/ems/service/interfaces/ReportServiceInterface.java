package pl.com.mnemonic.ems.service.interfaces;

import pl.com.mnemonic.ems.commons.dto.*;

import java.util.List;

/**
 * Created by mnemonic on 2016-03-05.
 */
public interface ReportServiceInterface {
    public ReportingDto getRequiredReportsList(UserDto userDto);
    public List<ReportDto> getAllTenantReports(PlaceUserDto placeUserDto);
    public List<FacilityDto> getAllFacilitiesWithCommonMediaCounters();
    public void addNewReporting(ReportingDto dto, UserDto userDto);
}
