package pl.com.mnemonic.ems.commons.dto;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by mnemonic on 2016-03-05.
 */
public class ReportingDto {

    List<ReportDto> existingReports;
    @Valid
    List<ReportDto> requiredReports;
    List<ReportDto> usersMissingReports;

    public List<ReportDto> getUsersMissingReports() {
        return usersMissingReports;
    }

    public void setUsersMissingReports(List<ReportDto> usersMissingReports) {
        this.usersMissingReports = usersMissingReports;
    }

    public List<ReportDto> getRequiredReports() {
        return requiredReports;
    }

    public void setRequiredReports(List<ReportDto> requiredReports) {
        this.requiredReports = requiredReports;
    }

    public List<ReportDto> getExistingReports() {

        return existingReports;
    }

    public void setExistingReports(List<ReportDto> existingReports) {
        this.existingReports = existingReports;
    }

    public ReportingDto(){}
}
