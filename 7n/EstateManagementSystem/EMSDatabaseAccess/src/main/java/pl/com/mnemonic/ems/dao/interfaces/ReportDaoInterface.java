package pl.com.mnemonic.ems.dao.interfaces;

import java.util.List;

import pl.com.mnemonic.ems.entity.Report;

public interface ReportDaoInterface extends GenericDaoInterface<Report, Integer> {

	List<Report> getPlaceUserReports(int idTenant);

}
