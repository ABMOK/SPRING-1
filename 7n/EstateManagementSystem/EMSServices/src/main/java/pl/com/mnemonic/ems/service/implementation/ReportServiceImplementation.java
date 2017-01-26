package pl.com.mnemonic.ems.service.implementation;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.mnemonic.ems.commons.dto.*;
import pl.com.mnemonic.ems.commons.enums.SystemUserType;
import pl.com.mnemonic.ems.commons.parsers.EMSUtilsParser;
import pl.com.mnemonic.ems.dao.interfaces.MediaCounterDaoInterface;
import pl.com.mnemonic.ems.dao.interfaces.PlaceDaoInterface;
import pl.com.mnemonic.ems.dao.interfaces.PlaceUserDaoInterface;
import pl.com.mnemonic.ems.dao.interfaces.ReportDaoInterface;
import pl.com.mnemonic.ems.entity.MediaCounter;
import pl.com.mnemonic.ems.entity.Place;
import pl.com.mnemonic.ems.entity.Placeuser;
import pl.com.mnemonic.ems.entity.Report;
import pl.com.mnemonic.ems.mapper.implementation.MediaCounterMapper;
import pl.com.mnemonic.ems.mapper.implementation.ReportMapper;
import pl.com.mnemonic.ems.service.interfaces.EstateServiceInterface;
import pl.com.mnemonic.ems.service.interfaces.MediaServiceInterface;
import pl.com.mnemonic.ems.service.interfaces.ReportServiceInterface;
import pl.com.mnemonic.ems.service.interfaces.TenantsServiceInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by mnemonic on 2016-03-05.
 */
@Service("reportService")
public class ReportServiceImplementation implements ReportServiceInterface {

    @Autowired
    ReportDaoInterface reportDao;
    @Autowired
    MediaCounterDaoInterface counterDao;
    @Autowired
    TenantsServiceInterface tenantService;
    @Autowired
    EstateServiceInterface estateService;
    @Autowired
    MediaServiceInterface mediaService;
    @Autowired
    PlaceDaoInterface placeDao;
    @Autowired
    PlaceUserDaoInterface placeUserDao;


    private final List<SystemUserType> SYSTEM_AUTHORITIES = Arrays.asList(SystemUserType.ADMIN, SystemUserType.USER);


    /**
     * Prepares List of required ReportDto for specified user
     *
     * @param userDto UserDto
     * @return requiredReports List of required reports
     */
    @Override
    public ReportingDto getRequiredReportsList(UserDto userDto) {
        ReportingDto reportingDto = new ReportingDto();
        List<MediaCounter> mediaCounters;
        List<ReportDto> reports = new ArrayList<>();
        List<ReportDto> requiredReports = null;
        List<ReportDto> userMissingReports = new ArrayList<>();
        PlaceUserDto placeUserDto = null;

        if (userDto != null && userDto.getPlaceUserId() != null && userDto.getPlaceUserId() > 0) {
            placeUserDto = tenantService.getTenantDtoById(userDto.getPlaceUserId());
        }


        if (placeUserDto != null && placeUserDto.getPlaceDto() != null) {

            mediaCounters = counterDao.getPlaceMediaCounters(placeUserDto.getPlaceDto().getId());
            reports = getAllTenantReports(placeUserDto);
            requiredReports = getPlaceUserRequiredReportsList(mediaCounters, reports, placeUserDto);
            reportingDto.setExistingReports(reports);
            reportingDto.setRequiredReports(requiredReports);

        } else if (userDto != null && userDto.getMyRoles() != null) {
            for (SystemUserType systemUser : userDto.getMyRoles()) {
                if (SYSTEM_AUTHORITIES.contains(systemUser)) {
                    requiredReports = new ArrayList<>();
                    for (FacilityDto facility : getAllFacilitiesWithCommonMediaCounters()) {
                        List<MediaCounterDto> countersFacility = facility.getMediaCounters();
                        if (countersFacility != null) {
                            for (MediaCounterDto facilityCounter : countersFacility) {
                                ReportDto reportFacility = new ReportDto(facilityCounter, facilityCounter.getMediaType(), facilityCounter.getRegistryNumber());
                                requiredReports.add(reportFacility);
                            }

                        }

                    }
                    reportingDto.setRequiredReports(requiredReports);

                    for (Place place : placeDao.list()) {
                        mediaCounters = counterDao.getPlaceMediaCounters(place.getIdplace());
                        for (PlaceUserDto user : tenantService.getAllOwnersAsDto(place.getFacility().getIdfacility(), place.getIdplace())) {
                            reports.addAll(getAllTenantReports(user));
                            List<ReportDto> userReports = getPlaceUserRequiredReportsList(mediaCounters, reports, user);
                            if (userReports != null) {
                                userMissingReports.addAll(userReports);
                            }
                        }
                    }
                }
            }
            reportingDto.setExistingReports(reports);
            reportingDto.setUsersMissingReports(userMissingReports);
        }
        return reportingDto;
    }


    /**
     * Prepares ReportDto List registered for specified PlaceUser
     *
     * @param placeUserDto PlaceUserDto
     * @return reports ReportDto List
     */
    @Override
    public List<ReportDto> getAllTenantReports(PlaceUserDto placeUserDto) {
        List<ReportDto> reports = new ArrayList<>();
        List<Report> userReports = reportDao.getPlaceUserReports(placeUserDto.getIdplaceuser());
        if (userReports != null && !userReports.isEmpty()) {
            for (Report report : userReports) {
                MediaCounterDto mediaCounterDto = null;
                if (report.getMediaCounter() != null && report.getMediaCounter().getIdmediaCounter() != null) {
                    mediaCounterDto = MediaCounterMapper.mapEntityToMediaCounterDto(counterDao.find(report.getMediaCounter().getIdmediaCounter()));
                }
                reports.add(ReportMapper.mapEntityToReportDto(report, placeUserDto, mediaCounterDto));
            }
        }

        return reports;
    }

    /**
     * Prepares Place User required Report List of ReportDto for specified MediaCounters
     *
     * @param mediaCounters MediaCounter List
     * @param reports       user previous reports
     * @param dto           PlaceUserDto
     * @return requiredReports ReportDto required reports
     */
    private List<ReportDto> getPlaceUserRequiredReportsList(List<MediaCounter> mediaCounters, List<ReportDto> reports, PlaceUserDto dto) {
        List<ReportDto> requiredReports = null;
        List<String> mediaTypes = null;

        if (mediaCounters != null && !mediaCounters.isEmpty()) {
            mediaTypes = new ArrayList<String>();
            for (MediaCounter counter : mediaCounters) {
                mediaTypes.add(counter.getMediaType());
            }
        }

        if (reports != null && mediaTypes != null) {
            for (ReportDto report : reports) {
                if (report.getType() != null && report.getDate() != null) {
                    if (mediaTypes.contains(report.getType()) && isReportedThisMonth(report.getDate())) {
                        mediaTypes.remove(report.getType());
                    }
                }
            }
        }

        if (mediaTypes != null && !mediaTypes.isEmpty()) {
            requiredReports = new ArrayList<ReportDto>();
            ReportDto reportDto;


            for (String type : mediaTypes) {
                for (MediaCounter mediaCounterDto : mediaCounters) {
                    if (mediaCounterDto.getMediaType().equals(type)) {
                        MediaCounterDto counterDto = MediaCounterMapper.mapEntityToMediaCounterDto(mediaCounterDto);
                        reportDto = ReportMapper.map(type, dto, counterDto);
                        requiredReports.add(reportDto);
                    }
                }

            }
        }

        return requiredReports;
    }

    /**
     * Prepares List of FacilityDto having Common Media Counters defined
     *
     * @return FacilityDto list
     */
    @Override
    public List<FacilityDto> getAllFacilitiesWithCommonMediaCounters() {
        List<FacilityDto> facilities = estateService.getFacilitiesAsDtos();
        List<MediaCounterDto> facilityMediaCounters;
        if (facilities != null && !facilities.isEmpty()) {
            for (FacilityDto facilityDto : facilities) {
                facilityMediaCounters = mediaService.getFacilityCommontMediaCountersAsDtos(facilityDto.getIdfacility());
                if (facilityMediaCounters != null) {
                    facilityDto.setMediaCounters(facilityMediaCounters);
                }
            }
        }
        return facilities;
    }


    @Override
    public void addNewReporting(ReportingDto dto, UserDto userDto) {
        List<MediaCounterDto> counters = new ArrayList<>();

        if (dto != null && dto.getRequiredReports() != null) {
            if (userDto != null) {
                if (userDto.getMyRoles() != null) {
                    for (SystemUserType systemUser : userDto.getMyRoles()) {
                        if (SYSTEM_AUTHORITIES.contains(systemUser)) {
                            updateMediaCouters(dto.getRequiredReports());
                            break;
                        }
                    }
                }
                if (userDto.getPlaceUserId() != null) {
                    Placeuser placeuser = placeUserDao.find(userDto.getPlaceUserId());
                    saveReports(dto.getRequiredReports(), placeuser);
                    counters = mediaService.getPlaceMediaCountersAsDtos(userDto);
                }
            }
            //TODO: update tenant counters
        }
    }

    /**
     * Saves report data from tenant
     *
     * @param reportsDto
     * @param placeuser
     */
    private void saveReports(List<ReportDto> reportsDto, Placeuser placeuser) {
        if (reportsDto != null && !reportsDto.isEmpty()) {
            for (ReportDto reportDto : reportsDto) {
                Report entity;
                MediaCounter counter = counterDao.find(reportDto.getMediaCounterDto().getIdmediaCounter());
                entity = ReportMapper.map(reportDto, placeuser, counter);
                reportDao.add(entity);
            }
        }
    }

    /**
     * Updates Common media counters from reports
     *
     * @param reportsDto
     */
    private void updateMediaCouters(List<ReportDto> reportsDto) {
        if (!reportsDto.isEmpty()) {
            for (ReportDto reportDto : reportsDto) {
                MediaCounter counter = counterDao.find(reportDto.getMediaCounterDto().getIdmediaCounter());
                if (counter != null) {
                    counter.setDateUpdated(new Date());
                    counter.setTotalAmount(EMSUtilsParser.parseToDouble(reportDto.getAmount()));
                    counterDao.update(counter);
                }
            }
        }

    }

    /**
     * Checks whether specified date is this months date
     *
     * @param date Date
     * @return boolean true if current month date
     */
    //TODO: move to utils
    private boolean isReportedThisMonth(Date date) {
        DateTime thisMonthStart = new DateTime().withDayOfMonth(1);
        // DateTime thisMonthEnd = thisMonthStart.plusMonths(1).minusDays(1);
        DateTime reported = new DateTime(date);
        if (reported.isAfter(thisMonthStart)) {
            return true;
        }
        return false;
    }
}
