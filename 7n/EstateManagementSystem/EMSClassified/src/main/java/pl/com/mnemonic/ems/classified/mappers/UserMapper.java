package pl.com.mnemonic.ems.classified.mappers;

import pl.com.mnemonic.ems.commons.dto.UserDto;
import pl.com.mnemonic.ems.commons.enums.SystemUserType;
import pl.com.mnemonic.ems.entity.Placeuser;
import pl.com.mnemonic.ems.entity.User;

import java.util.Date;
import java.util.List;

public class UserMapper {

    public static User mapUserDtoToEntity(UserDto userDto) {
        User user = new User();
        if (userDto.getIduser() != null && userDto.getIduser().intValue() > 0) {
            user.setIduser(userDto.getIduser());
        }
        user.setLastLogDate(new Date());
        user.setLogin(userDto.getLogin());
        user.setMail(userDto.getMail());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setPassword(userDto.getPassword());
        user.setPhoneNo(userDto.getPhoneNo());
        user.setRegdate(new Date());
        return user;
    }

    public static User mapUserDtoToEntityAsTenant(UserDto userDto, Placeuser tenant) {
        User user = new User();
        if (userDto.getIduser() != null && userDto.getIduser() > 0) {
            user.setIduser(userDto.getIduser());
        }
        user.setPlaceuser(tenant);
        user.setLogin(userDto.getLogin());
        user.setMail(userDto.getMail());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setPassword(userDto.getPassword());
        user.setPhoneNo(userDto.getPhoneNo());
        user.setRegdate(new Date());
        return user;
    }

    public static UserDto mapUserEntityToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setIduser(user.getIduser());
        userDto.setLogin(user.getLogin());
        userDto.setMail(user.getMail());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setPassword(user.getPassword());
        userDto.setPhoneNo(user.getPhoneNo());
        userDto.setRegdate(user.getRegdate());
        if (user.getPlaceuser() != null) {
            userDto.setPlaceUserId(user.getPlaceuser().getIdplaceuser());
        }
        return userDto;
    }

    public static UserDto mapUserEntityToDto(User user, List<SystemUserType> myRoles) {
        UserDto userDto = mapUserEntityToDto(user);
        userDto.setMyRoles(myRoles);
        return userDto;
    }

    public static User mapUserDtoToEntityAsTenant(UserDto userDto, Placeuser tenant, User systemUser) {
        systemUser.setPlaceuser(tenant);
        systemUser.setLastLogDate(new Date());
        systemUser.setLogin(userDto.getLogin());
        systemUser.setMail(userDto.getMail());
        systemUser.setName(userDto.getName());
        systemUser.setSurname(userDto.getSurname());
        systemUser.setPassword(userDto.getPassword());
        systemUser.setPhoneNo(userDto.getPhoneNo());
        systemUser.setRegdate(userDto.getRegdate());
        return systemUser;
    }
}
