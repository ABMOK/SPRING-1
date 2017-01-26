package pl.com.mnemonic.ems.mapper.implementation;

import pl.com.mnemonic.ems.commons.dto.MediaCounterDto;
import pl.com.mnemonic.ems.commons.dto.PlaceUserDto;
import pl.com.mnemonic.ems.commons.dto.ReportDto;
import pl.com.mnemonic.ems.commons.parsers.EMSUtilsParser;
import pl.com.mnemonic.ems.entity.MediaCounter;
import pl.com.mnemonic.ems.entity.Placeuser;
import pl.com.mnemonic.ems.entity.Report;

import java.util.Date;

/**
 * Created by mnemonic on 2016-02-21.
 */
public class ReportMapper {

    /**
     * Prepares ReportDto based on Report entity for specified PlaceUser
     * @param entity Report entity
     * @param tenant PlaceUserDto
     * @return reportDto
     */
    public static ReportDto mapEntityToReportDto(Report entity, PlaceUserDto tenant) {
        ReportDto reportDto = new ReportDto(entity.getIdreport(), tenant, entity.getDate(), entity.getType(),
                String.valueOf(entity.getAmount()), entity.getComment());
        return reportDto;
    }

    /**
     * Prepares ReportDto based on Report entity for specified PlaceUser
     * @param entity Report entity
     * @param tenant PlaceUserDto
     * @param mediaCounterDto
     * @return
     */
    public static ReportDto mapEntityToReportDto(Report entity, PlaceUserDto tenant, MediaCounterDto mediaCounterDto) {
        ReportDto reportDto = mapEntityToReportDto(entity, tenant);
        if(mediaCounterDto!=null){
            reportDto.setMediaCounterDto(mediaCounterDto);
        }
        return reportDto;
    }

    /**
     * Prepares ReportDto based on Report entity for specified PlaceUser
     * @param tenant PlaceUserDto
     * @param type String type
     * @return reportDto
     */
    public static ReportDto map(String type, PlaceUserDto tenant, MediaCounterDto mediaCounterDto) {
        ReportDto reportDto = new ReportDto();
        reportDto.setType(type);
        reportDto.setPlaceuser(tenant);
        reportDto.setMediaCounterDto(mediaCounterDto);
        return reportDto;
    }

    /**
     * Prepares new Report entity
     * @param reportDto ReportDto
     * @param placeuser Placeuser reporting
     * @return Report entity
     */
    public static Report map(ReportDto reportDto, Placeuser placeuser, MediaCounter counter) {
        Report report = new Report();
        report.setPlaceuser(placeuser);
        report.setAmount(EMSUtilsParser.parseToDouble(reportDto.getAmount()));
        report.setComment(reportDto.getComment());
        report.setDate(new Date());
        report.setType(reportDto.getType());
        report.setMediaCounter(counter);
        return report;
    }

    /**
     * Prepares new Report entity without placeuser(common)
     * @param reportDto ReportDto
     * @return Report entity
     */
    public static Report map(ReportDto reportDto, MediaCounter counter) {
        Report report = new Report();
        report.setAmount(EMSUtilsParser.parseToDouble(reportDto.getAmount()));
        report.setComment(reportDto.getComment());
        report.setDate(new Date());
        report.setType(reportDto.getType());
        report.setMediaCounter(counter);
        return report;
    }
}
