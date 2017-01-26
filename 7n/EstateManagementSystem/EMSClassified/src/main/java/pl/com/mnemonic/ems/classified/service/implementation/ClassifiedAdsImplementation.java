package pl.com.mnemonic.ems.classified.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.mnemonic.ems.classified.mappers.AttributeMapper;
import pl.com.mnemonic.ems.classified.mappers.CategoryMapper;
import pl.com.mnemonic.ems.classified.mappers.ClassifiedAdMapper;
import pl.com.mnemonic.ems.classified.service.interfaces.ClassifiedAdsInterface;
import pl.com.mnemonic.ems.classified.service.interfaces.UserServiceInterface;
import pl.com.mnemonic.ems.commons.dto.*;
import pl.com.mnemonic.ems.commons.enums.SystemUserType;
import pl.com.mnemonic.ems.dao.interfaces.*;
import pl.com.mnemonic.ems.entity.*;

import java.util.ArrayList;
import java.util.List;

@Service("classyService")
public class ClassifiedAdsImplementation implements ClassifiedAdsInterface {

    @Autowired
    AttributeDaoInterface attributeDao;
    @Autowired
    CategoryAttributeDaoInterface catAtDao;
    @Autowired
    RoleDaoInterface roleDao;
    @Autowired
    CategoryDaoInterface categoryDao;
    @Autowired
    ClassifiedadDaoInterface classifiedadDao;
    @Autowired
    UserServiceInterface systemUserService;

    /**
     * Depending on user roles prepares his ClassifiedComponentsDto containing allowed for his roles categories and ads
     * @param loggedUserDto UserDto
     * @return classy ClassifiedComponentsDto
     */
    @Override
    public ClassifiedComponentsDto getUserAwareComponent(UserDto loggedUserDto) {

        ClassifiedComponentsDto classy = getUserAwareComponentCategories(loggedUserDto);
        List<ClassifiedadDto> loggedUserAds = null;

        if (!classy.getCategoryList().isEmpty()) {
            loggedUserAds = new ArrayList<>();
            for (CategoryDto categoryDto : classy.getCategoryList()) {
                ClassifiedComponentsDto source = getUserAwareComponentCategoryAds(loggedUserDto, categoryDto.getIdcategory());
                loggedUserAds.addAll(source.getClassyAdList());
            }
        }

        if (loggedUserAds != null) {
            classy.setClassyAdList(loggedUserAds);
        }

        return classy;
    }

    /**
     * Depending on user roles prepares his ClassifiedComponentsDto including category list limited to his roles
     * @param loggedUserDto UserDto
     * @return classy ClassifiedComponentsDto
     */
    @Override
    public ClassifiedComponentsDto getUserAwareComponentCategories(UserDto loggedUserDto) {
        ClassifiedComponentsDto classifiedComponentsDto = new ClassifiedComponentsDto();
        List<CategoryDto> allCategories = getAllCategoriesDefined();
        List<CategoryDto> userCategories = new ArrayList<>();

        if (allCategories != null && !allCategories.isEmpty()) {
            if (loggedUserDto != null && loggedUserDto.getMyRoles() != null) {
                List<SystemUserType> userType = loggedUserDto.getMyRoles();
                for (CategoryDto categoryDto : allCategories) {
                    if (userType.contains(SystemUserType.ADMIN) || userType.contains(SystemUserType.USER)) {
                        userCategories.add(categoryDto);
                    } else {
                        if (categoryDto.getCategoryAttributes() != null
                                && !categoryDto.getCategoryAttributes().isEmpty()) {
                            for (AttributeDto attribute : categoryDto.getCategoryAttributes()) {
                                if (userType.contains(SystemUserType.OWNER)
                                        && (SystemUserType.RENTER.name().equals(attribute.getRoleName())
                                        || SystemUserType.OWNER.name().equals(attribute.getRoleName())) ) {
                                    userCategories.add(categoryDto);
                                    break;
                                } else if (userType.contains(SystemUserType.RENTER)
                                        && SystemUserType.RENTER.name().equals(attribute.getRoleName())) {
                                    userCategories.add(categoryDto);
                                }
                            }
                        } else {
                            userCategories.add(categoryDto);
                        }
                    }
                }
                classifiedComponentsDto.setCategoryList(userCategories);

            } else {
                for (CategoryDto categoryDto : allCategories) {
                    if (categoryDto.getCategoryAttributes() != null
                            && !categoryDto.getCategoryAttributes().isEmpty()) {
                        for (AttributeDto attribute : categoryDto.getCategoryAttributes()) {
                            if (attribute.getRoleName() == null || attribute.getAttributeName().trim().isEmpty()) {
                                userCategories.add(categoryDto);
                            }
                        }
                    }
                }

                classifiedComponentsDto.setCategoryList(userCategories);
            }
        }


        return classifiedComponentsDto;
    }

    /**
     * Depending on user roles prepares his ClassifiedComponentsDto including ad list limited to his roles
     * @param loggedUserDto
     * @param idc
     * @return
     */
    @Override
    public ClassifiedComponentsDto getUserAwareComponentCategoryAds(UserDto loggedUserDto, Integer idc) {
        ClassifiedComponentsDto componentsDto = getUserAwareComponentCategories(loggedUserDto);
        List<ClassifiedadDto> categoryAds = new ArrayList<>();
        CategoryDto category = null;

        if (componentsDto.getCategoryList() != null && !componentsDto.getCategoryList().isEmpty()) {
            for (CategoryDto categoryDto : componentsDto.getCategoryList()) {
                if (categoryDto.getIdcategory() != null && categoryDto.getIdcategory() == idc) {
                    category = categoryDto;
                }
            }
        }

        if (category != null) {
            List<Classifiedad> classifiedads = classifiedadDao.getAdsByCategory(category.getIdcategory());
            if (classifiedads != null && !classifiedads.isEmpty()) {
                for (Classifiedad classifiedad : classifiedads) {
                    ClassifiedadDto dto = ClassifiedAdMapper.map(classifiedad, category);
                    categoryAds.add(dto);
                }
            }

            componentsDto.setClassyAdList(categoryAds);
        }
        return componentsDto;
    }

    /**
     * Prepares ClassifiedComponentsDto including empty ClassifiedadDto  with list of categories and attributes
     * TODO: limit categories
     * @param loggedUserDto
     * @return
     */
    @Override
    public ClassifiedComponentsDto getUserAwareComponentAd(UserDto loggedUserDto) {
        ClassifiedComponentsDto classy = new ClassifiedComponentsDto();
        List<CategoryDto> allCategories = getAllCategoriesDefined();
        List<AttributeDto> allAttributes = getAllAttributesDefined();
        if (allCategories != null && !allCategories.isEmpty()) {
            classy.setCategoryList(allCategories);
        }
        if (allAttributes != null && !allAttributes.isEmpty()) {
            classy.setAllAttributes(allAttributes);
        }
        classy.setClassyAd(new ClassifiedadDto());
        return classy;
    }

    /**
     * Prepares ClassifiedComponentsDto on Admin level if User has Admin role
     * @param loggedUserDto
     * @return
     */
    @Override
    public ClassifiedComponentsDto getUserAwareComponentAdministration(UserDto loggedUserDto) {
        ClassifiedComponentsDto dto = null;
        if (loggedUserDto != null && loggedUserDto.getMyRoles() != null) {
            if (loggedUserDto.getMyRoles().contains(SystemUserType.ADMIN)
                    || loggedUserDto.getMyRoles().contains(SystemUserType.USER)) {
                dto = prepareAdminAwareComponent();
            }
        }

        return dto;
    }

    /**
     * Prepares ClassifiedComponentsDto with all categories and attributes defined
     * TODO: check for ads?
     * @return
     */
    private ClassifiedComponentsDto prepareAdminAwareComponent() {
        ClassifiedComponentsDto adminDto = new ClassifiedComponentsDto();

        List<AttributeDto> allAttributes = getAllAttributesDefined();
        if (allAttributes != null) {
            adminDto.setAllAttributes(allAttributes);
        }

        List<CategoryDto> allCategories = getAllCategoriesDefined();
        if (allCategories != null) {
            adminDto.setCategoryList(allCategories);
        }

        return adminDto;
    }

    /**
     * Depending on data in ClassifiedComponentsDto parameter, saves Attribute or Category
     * @param classyDto ClassifiedComponentsDto
     */
    @Override
    public void saveAdminComponent(ClassifiedComponentsDto classyDto) {
        if (classyDto.getAttributeDto() != null) {
            saveAttribute(classyDto.getAttributeDto());
        }
        if (classyDto.getCategory() != null) {
            saveCategory(classyDto.getCategory(), classyDto.getCategoryAttributes());
        }

    }

    /**
     * Saves Category to the database
     * @param category
     * @param newCategoryAttributes
     */
    private void saveCategory(CategoryDto category, List<Integer> newCategoryAttributes) {
        Category categoryEntity;
        Category updatedCategory = null;
        if (category.getCategoryName() != null && category.getCategoryName().trim().length() > 0) {
            if (category.getIdcategory() != null && category.getIdcategory() > 0) {
                updatedCategory = categoryDao.find(category.getIdcategory());
            }
            if (updatedCategory != null) {
                categoryEntity = CategoryMapper.map(category, updatedCategory);
            } else {
                categoryEntity = CategoryMapper.map(category);
            }
            if (categoryEntity.getIdcategory() != null && categoryEntity.getIdcategory() > 0) {
                categoryDao.update(categoryEntity);
            } else {
                categoryDao.add(categoryEntity);
            }
            if (newCategoryAttributes != null && !newCategoryAttributes.isEmpty()) {
                List<CategoryAttribute> oldAttributes = catAtDao.getListByCategoryId(categoryEntity.getIdcategory());
                if (oldAttributes != null && !oldAttributes.isEmpty()) {
                    for (CategoryAttribute attr : oldAttributes) {
                        if (attr.getCategory().getIdcategory().equals(categoryEntity.getIdcategory())) {
                            attr.setCategory(null);
                            attr.setAttribute(null);
                            catAtDao.update(attr);
                        }
                        catAtDao.remove(attr);
                    }

                }

                Attribute attribute;
                Category categoryUpdate;
                if (categoryEntity.getIdcategory() != null && categoryEntity.getIdcategory() > 0) {
                    categoryUpdate = categoryDao.find(categoryEntity.getIdcategory());
                    for (Integer dtoId : newCategoryAttributes) {
                        attribute = attributeDao.find(dtoId);
                        CategoryAttribute catAt;
                        if (attribute != null) {
                            catAt = new CategoryAttribute();
                            catAt.setAttribute(attribute);
                            catAt.setCategory(categoryUpdate);
                            catAtDao.add(catAt);
                        }

                    }
                }

            }
        }
    }

    /**
     * Saves Attribute to the databasa
     * @param attributeDto
     */
    private void saveAttribute(AttributeDto attributeDto) {
        Role role = null;
        if (attributeDto.getAttributeName() != null && attributeDto.getAttributeName().trim().length() > 0) {
            if (attributeDto.getRoleName() != null && attributeDto.getRoleName().trim().length() > 0
                    && !SystemUserType.PUBLIC.name().equals(attributeDto.getRoleName())) {
                role = roleDao.findRoleByCode(attributeDto.getRoleName());
            }
            Attribute attribute = new Attribute();
            attribute.setRole(role);
            attribute.setAttributeName(attributeDto.getAttributeName());
            attributeDao.add(attribute);
        }
    }

    /**
     * Prepares list of all attributes defined
     * @return
     */
    private List<AttributeDto> getAllAttributesDefined() {
        List<AttributeDto> allAttributes = new ArrayList<>();
        List<CategoryAttribute> categoryBinding;
        List<CategoryDto> categories = null;
        CategoryDto categoryDto;
        AttributeDto attributeDto;
        for (Attribute attribute : attributeDao.list()) {
            Role role = null;
            categories = new ArrayList<>();
            if (attribute.getRole() != null) {
                role = roleDao.find(attribute.getRole().getIdrole());
            }
            categoryBinding = catAtDao.getListByAttributeId(attribute.getIdattribute());
            if (categoryBinding != null && !categoryBinding.isEmpty()) {

                for (CategoryAttribute catAt : categoryBinding) {
                    categoryDto = CategoryMapper.map(categoryDao.find(catAt.getCategory().getIdcategory()));
                    categories.add(categoryDto);
                }
            }
            attributeDto = AttributeMapper.map(attribute, categories, role);
            allAttributes.add(attributeDto);
        }
        return allAttributes;
    }

    /**
     * Prepares list of all categories defined
     * @return
     */
    private List<CategoryDto> getAllCategoriesDefined() {

        List<CategoryDto> allCategories = new ArrayList<>();
        List<CategoryAttribute> binding;
        List<AttributeDto> categoryAttributes = null;
        AttributeDto attributeDto;
        CategoryDto categoryDto;

        List<Category> categoryEntities = categoryDao.list();
        if (categoryEntities != null && !categoryEntities.isEmpty()) {
            for (Category category : categoryEntities) {
                binding = catAtDao.getListByCategoryId(category.getIdcategory());
                if (binding != null && !binding.isEmpty()) {
                    categoryAttributes = new ArrayList<>();
                    for (CategoryAttribute catAt : binding) {
                        if (catAt.getAttribute() != null && catAt.getAttribute().getIdattribute() != null) {
                            Attribute attribute = attributeDao.find(catAt.getAttribute().getIdattribute());
                            Role role = null;
                            if (attribute.getRole() != null) {
                                role = roleDao.find(attribute.getRole().getIdrole());
                            }
                            attributeDto = AttributeMapper.map(attribute, role);
                            categoryAttributes.add(attributeDto);
                        }

                    }

                }
                categoryDto = CategoryMapper.map(category, categoryAttributes);
                allCategories.add(categoryDto);
            }
        }

        return allCategories;
    }

    /**
     * Prepares ClassifiedComponentsDto with specifieed Category
     * @param loggedUserDto UserDto
     * @param idc Category ID
     * @return ClassifiedComponentsDto
     */
    @Override
    public ClassifiedComponentsDto getUserAwareComponentAdministration(UserDto loggedUserDto, Integer idc) {
        ClassifiedComponentsDto dto = getUserAwareComponentAdministration(loggedUserDto);
        if (idc != null && dto.getCategoryList() != null && !dto.getCategoryList().isEmpty()) {
            for (CategoryDto category : dto.getCategoryList()) {
                if (category.getIdcategory().equals(idc)) {
                    dto.setCategory(category);
                }
            }
        }
        return dto;
    }

    /**
     * Deletes category
     * TODO: find better solution for PUBLIC
     * @param idc
     */
    @Override
    public void deleteCategory(Integer idc) {
        List<Classifiedad> bindingAds;
        List<CategoryAttribute> bindingCatAt;
        Category category = null;
        if (idc != null) {
            category = categoryDao.find(idc);
        }
        if (category != null && !category.getCategoryName().equals("PUBLIC")) {
            bindingAds = classifiedadDao.getAdsByCategory(idc);
            bindingCatAt = catAtDao.getListByCategoryId(category.getIdcategory());
            if (bindingCatAt != null && !bindingCatAt.isEmpty()) {
                for (CategoryAttribute catAt : bindingCatAt) {
                    catAtDao.remove(catAt);
                }
            }
            if (bindingAds != null && !bindingAds.isEmpty()) {
                List<Category> allCategories = categoryDao.list();
                Category publicCategory = null;
                for (Category category1 : allCategories) {
                    if (category1.getCategoryName().equals("PUBLIC")) {
                        publicCategory = category1;
                    }
                }
                if (publicCategory == null) {
                    publicCategory = new Category();
                    publicCategory.setCategoryName("PUBLIC");
                    categoryDao.add(publicCategory);
                }
                for (Classifiedad ad : bindingAds) {
                    ad.setCategory(publicCategory);
                    classifiedadDao.update(ad);
                }
            }
            categoryDao.remove(category);
        }
    }

    /**
     * Deletes attribute
     * @param ida
     */
    @Override
    public void deleteAttribute(Integer ida) {
        if (ida != null) {
            attributeDao.remove(attributeDao.find(ida));
        }
    }


    /**
     * Saves new Ad
     * TODO: use mapper
     * @param classyDto
     */
    @Override
    public void saveAdComponent(ClassifiedComponentsDto classyDto) {
        Classifiedad ad = null;
        ClassifiedadDto adDto = null;
        if (classyDto != null && classyDto.getClassyAd() != null) {
            adDto = classyDto.getClassyAd();
            Category category = categoryDao.find(adDto.getCategory().getIdcategory());
            ad = ClassifiedAdMapper.map(adDto, category);
        }
        UserDto userDto = systemUserService.getLoggedUserDto();
        if (userDto != null && userDto.getIduser() != null) {
            User user = new User();
            user.setIduser(userDto.getIduser());
            ad.setUser(user);
            classifiedadDao.add(ad);
        }


    }

    /**
     * Retrieves Ad with specified ID
     * @param idc
     * @param idad
     * @return
     */
    @Override
    public ClassifiedComponentsDto getUserAwareComponentAd(Integer idc, Integer idad) {
        ClassifiedComponentsDto classyDto = new ClassifiedComponentsDto();
        ClassifiedadDto classyAd = null;
        Classifiedad adEntity = classifiedadDao.find(idad);
        Category category = categoryDao.find(idc);
        if (adEntity != null && category != null) {
            CategoryDto catDto = CategoryMapper.map(category);
            classyAd = ClassifiedAdMapper.map(adEntity, catDto);
        }
        classyDto.setClassyAd(classyAd);
        classyDto.setCategoryList(getUserAwareComponentCategories(systemUserService.getLoggedUserDto()).getCategoryList());
        return classyDto;
    }

    /**
     * Deletes ad
     * @param idad
     */
    @Override
    public void deleteAd(Integer idad) {
        classifiedadDao.remove(classifiedadDao.find(idad));

    }

    /**
     * Retrieves Ad with specified ID
     * @param idc
     * @param idad
     * @return
     */
    @Override
    public ClassifiedComponentsDto getUserAwareComponentAd(UserDto loggedUserDto, Integer idc, Integer idad) {
        ClassifiedComponentsDto adDto = getUserAwareComponent(loggedUserDto);
        ClassifiedadDto classyAd = getUserAwareComponentAd(idc, idad).getClassyAd();
        adDto.setClassyAd(classyAd);
        return adDto;
    }
}
