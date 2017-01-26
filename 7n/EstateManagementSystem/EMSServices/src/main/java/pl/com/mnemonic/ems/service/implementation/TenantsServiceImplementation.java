package pl.com.mnemonic.ems.service.implementation;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.mnemonic.ems.classified.service.interfaces.UserServiceInterface;
import pl.com.mnemonic.ems.commons.dto.PlaceDto;
import pl.com.mnemonic.ems.commons.dto.PlaceUserDto;
import pl.com.mnemonic.ems.commons.dto.UserDto;
import pl.com.mnemonic.ems.commons.enums.PlaceUserType;
import pl.com.mnemonic.ems.dao.interfaces.PlaceUserDaoInterface;
import pl.com.mnemonic.ems.entity.Place;
import pl.com.mnemonic.ems.entity.Placeuser;
import pl.com.mnemonic.ems.mapper.implementation.PlaceUserMapper;
import pl.com.mnemonic.ems.service.interfaces.EstateServiceInterface;
import pl.com.mnemonic.ems.service.interfaces.FinanceServiceInterface;
import pl.com.mnemonic.ems.service.interfaces.PlaceServiceInterface;
import pl.com.mnemonic.ems.service.interfaces.TenantsServiceInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("tenantService")
public class TenantsServiceImplementation implements TenantsServiceInterface {
    static final Logger LOGGER = Logger.getLogger(TenantsServiceImplementation.class);

    @Autowired
    PlaceUserDaoInterface placeUserDao;
    @Autowired
    EstateServiceInterface estateService;
    @Autowired
    PlaceServiceInterface placeService;
    @Autowired
    FinanceServiceInterface financeService;
    @Autowired
    UserServiceInterface systemUserService;

    /**
     * Prepares list of all tenants, both Owners and Renters
     *
     * @return tenants list of PlaceUserDto's
     */
    @Override
    public List<PlaceUserDto> getAllTenantsAsDto() {
        List<PlaceUserDto> tenants = new ArrayList<PlaceUserDto>();
        List<Placeuser> placeusers = placeUserDao.list();
        if (placeusers != null && !placeusers.isEmpty()) {
            for (Placeuser user : placeusers) {
                PlaceDto placeDto = placeService.getPlaceDtoByPlaceId(user.getPlace().getIdplace());
                UserDto userDto = systemUserService.getCurrentPlaceUserAccount(user.getIdplaceuser());
                tenants.add(PlaceUserMapper.mapEntityToPlaceUserDto(user, placeDto, userDto));
            }
        }
        return tenants;
    }

    /**
     * Prepares list of all places Owners
     *
     * @return tenants list of PlaceUserDto's - Owners only
     */
    @Override
    public List<PlaceUserDto> getAllPlaceOwnersAsDto() {
        List<Placeuser> placeOwners = null;
        List<PlaceUserDto> tenants = new ArrayList<PlaceUserDto>();
        List<PlaceDto> allPlaces = placeService.getAllPlacesAsDtos();
        for (PlaceDto placeDto : allPlaces) {
            placeOwners = placeUserDao.getOwners(placeDto.getId());
            if (placeOwners != null && !placeOwners.isEmpty()) {
                for (Placeuser user : placeOwners) {
                    UserDto userDto = systemUserService.getCurrentPlaceUserAccount(user.getIdplaceuser());
                    tenants.add(PlaceUserMapper.mapEntityToPlaceUserDto(user, placeDto, userDto));
                }
            }
        }
        return tenants;
    }

    /**
     * Prepares list of all places Renters
     *
     * @return tenants list of PlaceUserDto's - Renters only
     */
    @Override
    public List<PlaceUserDto> getAllPlaceRentersAsDto() {
        List<Placeuser> placeRenters = null;
        List<PlaceUserDto> tenants = new ArrayList<>();
        List<PlaceDto> allPlaces = placeService.getAllPlacesAsDtos();
        for (PlaceDto placeDto : allPlaces) {
            placeRenters = placeUserDao.getTenants(placeDto.getId());
            if (placeRenters != null && !placeRenters.isEmpty()) {
                for (Placeuser user : placeRenters) {
                    UserDto userDto = systemUserService.getCurrentPlaceUserAccount(user.getIdplaceuser());
                    tenants.add(PlaceUserMapper.mapEntityToPlaceUserDto(user, placeDto, userDto));
                }
            }
        }
        return tenants;
    }

    /**
     * Prepares PlaceUserDto by PlaceUser ID
     * Used by FinanceService, Media Service, PlaceUsersController placeUserUpdate()
     *
     * @param id PlaceUser ID
     * @return PlaceUserDto for specified ID
     */
    @Override
    public PlaceUserDto getTenantDtoById(Integer id) {
        PlaceUserDto placeUserDto = null;
        Placeuser placeUser = null;
        if (id != null) {
            placeUser = placeUserDao.find(id);
            if (placeUser != null) {
                PlaceDto placeDto = placeService.getPlaceDtoByPlaceId(placeUser.getPlace().getIdplace());
                UserDto userDto = systemUserService.getCurrentPlaceUserAccount(placeUser.getIdplaceuser());
                placeUserDto = PlaceUserMapper.mapEntityToPlaceUserDto(placeUser, placeDto, userDto);
            }
        }

        return placeUserDto;
    }

    @Override
    public PlaceUserDto getTenantDtoById(Integer id, Integer ide, Integer idp) {
        Placeuser user = placeUserDao.find(id);
        PlaceDto placeDto = placeService.getPlaceDtoByFacilityAndPlaceIds(ide, idp);
        UserDto userDto = systemUserService.getCurrentPlaceUserAccount(user.getIdplaceuser());
        PlaceUserDto tenant = PlaceUserMapper.mapEntityToPlaceUserDto(user, placeDto, userDto);
        return tenant;
    }

    /**
     * Prepares PlaceUserDto with pre-specified Place and type
     *
     * @param idf  Facility ID
     * @param idp  Place ID
     * @param type PlaceUserType
     * @return tenant PlaceUserDto
     */
    @Override
    public PlaceUserDto newPlaceUser(Integer idf, Integer idp, PlaceUserType type) {
        PlaceDto placeDto = placeService.getPlaceDtoByFacilityAndPlaceIds(idf, idp);
        PlaceUserDto tenant = new PlaceUserDto(placeDto, new UserDto());
        tenant.setUsageType(type.name());
        return tenant;
    }

    /**
     * Prepares place tenant update from PlaceUserDto data
     *
     * @param dto PlaceUserDto
     * @param idf ID Facility
     * @param idp ID Place
     */
    @Override
    public void saveTenantToPlace(PlaceUserDto dto, Integer idf, Integer idp) {
        Place place = placeService.retrievePlace(idf, idp);
        Placeuser tenant;

        if (dto.getIdplaceuser() != null && dto.getIdplaceuser() > 0) {
            tenant = placeUserDao.find(dto.getIdplaceuser());
            Placeuser updated = PlaceUserMapper.mapPlaceUserDtoToEntity(dto, tenant, place);
            placeUserDao.update(updated);

        } else {
            tenant = PlaceUserMapper.mapPlaceUserDtoToEntity(dto, place);
            placeUserDao.add(tenant);
        }

        if (dto.getUserDto() != null && dto.getUsageType() != null) {
            systemUserService.updateUser(dto.getUserDto(), dto.getUsageType(), tenant);
        }

        List<Placeuser> placeUsers = null;
        if (PlaceUserType.RENTER.name().equals(dto.getUsageType())) {
            placeUsers = placeUserDao.getTenants(idp);
            //TODO: future implementation for placeusers count: if size <= counter then list.clear
        }
        if (PlaceUserType.OWNER.name().equals(dto.getUsageType())) {
            placeUsers = placeUserDao.getOwners(idp);
        }
        if (placeUsers != null && !placeUsers.isEmpty()) {
            for (Placeuser previousOwner : placeUsers) {
                if (PlaceUserType.OWNER.name().equals(tenant.getUsageType())
                        && !tenant.getIdplaceuser().equals(previousOwner.getIdplaceuser())
                        && previousOwner.getEnddate() == null) {
                    previousOwner.setEnddate(new Date());
                    placeUserDao.update(previousOwner);
                }
            }
        }

    }

    /**
     * Updates Placeuser Entity data from PlaceUserDto
     *
     * @param dto PlaceUserDto
     * @param idt Placeuser id
     */
    @Override
    public void updateTenant(PlaceUserDto dto, int idt) {
        Place place = null;
        if (dto.getPlaceDto() != null && dto.getPlaceDto().getPlaceid() != null) {
            place = placeService.retrievePlace(dto.getPlaceDto().getId());
        }

        Placeuser user = placeUserDao.find(idt);
        if (user != null && place != null) {
            Placeuser updated = PlaceUserMapper.mapPlaceUserDtoToEntity(dto, user, place);
            placeUserDao.update(updated);
        }
    }

    /**
     * Prepares list of all tenants, both Owners and Renters, for specified place
     *
     * @param ide Facility ID
     * @param idp Place ID
     * @return tenants List of PlaceUserDto's for Place
     */
    @Override
    public List<PlaceUserDto> getAllRentersAsDto(Integer ide, Integer idp) {
        List<Placeuser> placeusers = placeUserDao.getTenants(idp);
        return preparePlaceUserDtoList(placeusers, ide, idp);
    }


    /**
     * Prepares list of all Owners for specified place
     *
     * @param ide Facility ID
     * @param idp Place ID
     * @return tenants list of PlaceUserDto's - Owners only
     */
    @Override
    public List<PlaceUserDto> getAllOwnersAsDto(Integer ide, Integer idp) {
        List<Placeuser> placeOwners = placeUserDao.getOwners(idp);
        return preparePlaceUserDtoList(placeOwners, ide, idp);
    }

    /**
     * converts entity list to dto list based on provided in parameter
     *
     * @param placeusers place owners or place users
     * @param ide        ID estate
     * @param idp        Id place
     * @return list of DTO place user objects
     */
    private List<PlaceUserDto> preparePlaceUserDtoList(List<Placeuser> placeusers, Integer ide, Integer idp) {
        List<PlaceUserDto> tenants = new ArrayList<PlaceUserDto>();
        if (placeusers != null && !placeusers.isEmpty()) {
            for (Placeuser user : placeusers) {
                PlaceDto placeDto = placeService.getPlaceDtoByFacilityAndPlaceIds(ide, idp);
                UserDto userDto = systemUserService.getCurrentPlaceUserAccount(user.getIdplaceuser());
                tenants.add(PlaceUserMapper.mapEntityToPlaceUserDto(user, placeDto, userDto));
            }
        }
        return tenants;
    }

    /**
     * Retrieves current Place Owner as PlaceUserDto for specified place
     *
     * @param idf Facility ID
     * @param idp Place ID
     * @return owner PlaceUserDto
     */
    @Override
    public PlaceUserDto getCurrentPlaceOwner(Integer idf, Integer idp) {
        PlaceUserDto owner = null;
        PlaceDto place = placeService.getPlaceDtoByFacilityAndPlaceIds(idf, idp);
        for (Placeuser user : placeUserDao.getOwners(idp)) {
            if (user.getEnddate() == null) {
                UserDto userDto = systemUserService.getCurrentPlaceUserAccount(user.getIdplaceuser());
                owner = PlaceUserMapper.mapEntityToPlaceUserDto(user, place, userDto);
            }
        }
        return owner;
    }

    /**
     * Removes PlaceUser
     *
     * @param idt PlaceUser ID
     */
    @Override
    public void deletePlaceUser(Integer idt) {
        Placeuser placeuser = placeUserDao.find(idt);
        placeUserDao.remove(placeuser);
    }

 /*   @Override
    public List<PlaceUserDto> getCurrentResidentsAsDto(Integer idplace) {
        // TODO Auto-generated method stub
        return null;
    }*/


}
