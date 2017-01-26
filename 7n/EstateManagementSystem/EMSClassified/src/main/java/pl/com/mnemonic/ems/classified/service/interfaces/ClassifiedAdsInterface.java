package pl.com.mnemonic.ems.classified.service.interfaces;

import pl.com.mnemonic.ems.commons.dto.ClassifiedComponentsDto;
import pl.com.mnemonic.ems.commons.dto.UserDto;

public interface ClassifiedAdsInterface {

    public ClassifiedComponentsDto getUserAwareComponent(UserDto loggedUserDto);

    public ClassifiedComponentsDto getUserAwareComponentCategories(UserDto loggedUserDto);

    public ClassifiedComponentsDto getUserAwareComponentCategoryAds(UserDto loggedUserDto, Integer idc);

    public ClassifiedComponentsDto getUserAwareComponentAd(UserDto loggedUserDto);

    public ClassifiedComponentsDto getUserAwareComponentAd(UserDto loggedUserDto, Integer idc, Integer idad);

    public ClassifiedComponentsDto getUserAwareComponentAd(Integer idc, Integer idad);

    public ClassifiedComponentsDto getUserAwareComponentAdministration(UserDto loggedUserDto);

    public ClassifiedComponentsDto getUserAwareComponentAdministration(UserDto loggedUserDto, Integer idc);

    public void saveAdminComponent(ClassifiedComponentsDto classyDto);

    public void saveAdComponent(ClassifiedComponentsDto classyDto);

    public void deleteCategory(Integer idc);

    public void deleteAttribute(Integer ida);

    public void deleteAd(Integer idad);
}
