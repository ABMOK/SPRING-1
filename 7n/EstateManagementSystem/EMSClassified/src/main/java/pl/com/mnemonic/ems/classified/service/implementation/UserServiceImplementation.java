package pl.com.mnemonic.ems.classified.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.com.mnemonic.ems.classified.mappers.UserMapper;
import pl.com.mnemonic.ems.classified.service.interfaces.UserServiceInterface;
import pl.com.mnemonic.ems.commons.dto.AdministrationDto;
import pl.com.mnemonic.ems.commons.dto.PlaceDto;
import pl.com.mnemonic.ems.commons.dto.UserDto;
import pl.com.mnemonic.ems.commons.enums.SystemUserType;
import pl.com.mnemonic.ems.dao.interfaces.*;
import pl.com.mnemonic.ems.entity.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("systemUserService")
public class UserServiceImplementation implements UserServiceInterface {

    @Autowired
    UserDaoInterface userDao;
    @Autowired
    RoleDaoInterface roleDao;
    @Autowired
    UserRoleDaoInterface userroleDao;
    @Autowired
    PlaceDaoInterface placeDao;
    @Autowired
    PlaceUserDaoInterface placeUserDao;

    /**
     * Using SecurityContextHolder identificates logged-in user and creates his UserDto
     *
     * @return UserDto
     */
    @Override
    public UserDto getLoggedUserDto() {
        UserDto userDto = null;
        List<SystemUserType> userRoles = null;
        org.springframework.security.core.userdetails.User userSecurity = null;
        if (SecurityContextHolder.getContext() != null
                && SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null
                && SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof org.springframework.security.core.userdetails.User) {
            userSecurity = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }

        if (userSecurity != null) {
            User user = userDao.findByMail(userSecurity.getUsername());

            if (userSecurity.getAuthorities() != null && !userSecurity.getAuthorities().isEmpty()) {
                userRoles = new ArrayList<>();
                for (GrantedAuthority authority : userSecurity.getAuthorities()) {
                    userRoles.add(SystemUserType.fromString(authority.toString()));
                }
            }

            userDto = UserMapper.mapUserEntityToDto(user, userRoles);
        }
        return userDto;
    }

    /**
     * Updated user data using data from UserDto
     *
     * @param user
     * @param roleCode
     * @param tenant
     */
    @Override
    public User updateUser(UserDto user, String roleCode, Placeuser tenant) {
        User systemUser = null;

        if (user.getIduser() != null && user.getIduser() > 0) {
            systemUser = userDao.getUser(user.getIduser());
        }
        if (systemUser != null) {
            if(systemUser.getPlaceuser() != null && systemUser.getPlaceuser().getIdplaceuser() != null){
                placeUserDao.remove(placeUserDao.find(systemUser.getPlaceuser().getIdplaceuser()));
            }
            systemUser = UserMapper.mapUserDtoToEntityAsTenant(user, tenant, systemUser);
        } else {
            user.setIduser(null);
            systemUser = UserMapper.mapUserDtoToEntityAsTenant(user, tenant);
        }

        if (systemUser != null) {
            userDao.saveOrUpdate(systemUser);
        }
        return systemUser;
        /*
        if (roleCode != null) {
            Role role = roleDao.findRoleByCode(roleCode);
            if (role != null) {
                if (roleCode.equals("OWNER")) {
                    userroleDao.add(new UserRole(systemUser, role, tenant.getIdplaceuser(), null));
                }
                if (roleCode.equals("RENTER")) {
                    userroleDao.add(new UserRole(systemUser, role, null, tenant.getIdplaceuser()));
                }
            }
        }*/
    }

    private void updateRoles(User systemUser, String roleCode, Placeuser tenant) {
        if (roleCode != null) {
            Role role = roleDao.findRoleByCode(roleCode);
            if (role != null) {
                if (roleCode.equals("OWNER")) {
                    userroleDao.add(new UserRole(systemUser, role, tenant.getIdplaceuser(), null));
                }
                if (roleCode.equals("RENTER")) {
                    userroleDao.add(new UserRole(systemUser, role, null, tenant.getIdplaceuser()));
                }
            }
        }
    }

    /**
     * Prepares List of all System Users
     *
     * @return
     */
    @Override
    public AdministrationDto getAllSystemUsers() {
        AdministrationDto administrationDto = new AdministrationDto();
        List<User> users = userDao.getUsers();
        List<UserDto> userDtos = null;
        UserDto userDto;
        if (users != null && !users.isEmpty()) {
            userDtos = new ArrayList<>();
            for (User user : users) {
                userDto = UserMapper.mapUserEntityToDto(user);
                userDtos.add(userDto);
            }
        }
        administrationDto.setAllUsers(userDtos);
        return administrationDto;
    }

    /**
     * Saves new user
     *
     * @param adminDto
     */
    @Override
    public void saveNewUser(AdministrationDto adminDto) {
        UserDto userDto = adminDto.getUserDto();
        if (userDto != null) {
            User user = null;
            if (userDto.getPlaceUserId() != null) {
                Placeuser placeuser = placeUserDao.find(userDto.getPlaceUserId());
                user = UserMapper.mapUserDtoToEntityAsTenant(userDto, placeuser);
            } else {
                user = UserMapper.mapUserDtoToEntity(userDto);
            }
            userDao.saveOrUpdate(user);
            if (userDto.getMyRoles() != null && !userDto.getMyRoles().isEmpty()) {
                if (userDto.getMyRoles().contains(SystemUserType.OWNER)
                        || userDto.getMyRoles().contains(SystemUserType.RENTER)) {
                    adminDto.getUserDto().setIduser(user.getIduser());
                    adminDto.getUserDto().setRegdate(user.getRegdate());
                    resolvePlaceUser(adminDto);
                } else {
                    Role role;
                    for (SystemUserType type : userDto.getMyRoles()) {
                        if (SystemUserType.PUBLIC.equals(type)) {
                            continue;
                        }
                        role = roleDao.findRoleByCode(type.name());
                        userroleDao.add(new UserRole(user, role));
                    }
                }
            }
        }
    }

    /**
     * Saves Place User
     *
     * @param adminDto
     */
    private void resolvePlaceUser(AdministrationDto adminDto) {
        UserDto userDto = adminDto.getUserDto();
        Place place;
        Placeuser placeuser = null;
        PlaceDto placeDto = adminDto.getPlaceDto();
        if (userDto.getPlaceUserId() != null) {
            placeUserDao.remove(placeUserDao.find(userDto.getPlaceUserId()));
            //TO USUWA USERA!
        }
        if (placeDto != null && placeDto.getPlaceid() != null) {
            place = placeDao.find(placeDto.getId());
            if (place != null) {
                if (userDto.getMyRoles().contains(SystemUserType.OWNER)) {
                    placeuser = new Placeuser(place, SystemUserType.OWNER.name(), new Date());
                } else {
                    placeuser = new Placeuser(place, SystemUserType.RENTER.name(), new Date());
                }
            }
            if (placeuser != null) {
                placeUserDao.add(placeuser);
                User user = updateUser(userDto, placeuser.getUsageType(), placeuser);
                updateRoles(user, placeuser.getUsageType(), placeuser);
            }
        }
    }

    /**
     * Deletes user
     *
     * @param id
     */
    @Override
    public void deleteUser(Integer id) {
        if (id != null) {
            User user = userDao.getUser(id);
            if (user != null) {
                if (user.getPlaceuser() != null && user.getPlaceuser().getIdplaceuser() != null) {
                    placeUserDao.remove(placeUserDao.find(user.getPlaceuser().getIdplaceuser()));
                } else {
                    userDao.deleteUser(id);
                }
            }


        }
    }

    /**
     * Prepares UserDto for editing
     *
     * @param idu user ID
     * @return AdministrationDto with UserDto
     */
    @Override
    public AdministrationDto getUser(Integer idu) {
        UserDto userDto = null;
        List<SystemUserType> userRoles = null;
        AdministrationDto administrationDto = new AdministrationDto();
        if (idu != null) {
            User user = userDao.getUser(idu);
            if (user != null) {
                List<UserRole> userRoleList = userroleDao.getUserRolesByUserId(idu);
                if (userRoleList != null && !userRoleList.isEmpty()) {
                    userRoles = new ArrayList<>();
                    for (UserRole userRole : userRoleList) {
                        Role role = roleDao.find(userRole.getRole().getIdrole());
                        SystemUserType type = SystemUserType.fromString(role.getRoleName());
                        userRoles.add(type);
                    }
                }

                userDto = UserMapper.mapUserEntityToDto(user, userRoles);
            }
        }
        administrationDto.setUserDto(userDto);
        return administrationDto;
    }


    @Override
    public UserDto getCurrentPlaceUserAccount(Integer idPlaceUser) {
        List<User> userAccounts = userDao.getUserAccountsByPlaceUserId(idPlaceUser);
        UserDto currentAccount = null;
        if (userAccounts != null && !userAccounts.isEmpty()) {
            currentAccount = UserMapper.mapUserEntityToDto(userAccounts.get(0), null);
        }
        return currentAccount;
    }
}
