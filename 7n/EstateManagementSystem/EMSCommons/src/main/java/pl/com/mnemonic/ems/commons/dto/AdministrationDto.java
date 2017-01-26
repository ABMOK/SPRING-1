package pl.com.mnemonic.ems.commons.dto;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by mnemonic on 2016-01-30.
 */
public class AdministrationDto {

    List<UserDto> allUsers;
    @Valid
    @NotNull
    UserDto userDto;
    PlaceDto placeDto;

    public List<UserDto> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(List<UserDto> allUsers) {
        this.allUsers = allUsers;
    }

    public PlaceDto getPlaceDto() {
        return placeDto;
    }

    public void setPlaceDto(PlaceDto placeDto) {
        this.placeDto = placeDto;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public AdministrationDto(){}

}
