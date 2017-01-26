package pl.com.mnemonic.ems.commons.dto;

import javax.validation.Valid;
import java.util.List;

public class ClassifiedComponentsDto {

    @Valid
    private ClassifiedadDto classyAd;
    @Valid
    private AttributeDto attributeDto;
    @Valid
    private CategoryDto category;

    private List<ClassifiedadDto> classyAdList;
    private List<AttributeDto> allAttributes;
    private List<Integer> categoryAttributes;

    private List<CategoryDto> categoryList;

    public ClassifiedComponentsDto() {
    }

    public ClassifiedadDto getClassyAd() {
        return classyAd;
    }

    public void setClassyAd(ClassifiedadDto classyAd) {
        this.classyAd = classyAd;
    }

    public List<ClassifiedadDto> getClassyAdList() {
        return classyAdList;
    }

    public void setClassyAdList(List<ClassifiedadDto> classyAdList) {
        this.classyAdList = classyAdList;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public List<CategoryDto> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<CategoryDto> categoryList) {
        this.categoryList = categoryList;
    }

    public List<AttributeDto> getAllAttributes() {
        return allAttributes;
    }

    public void setAllAttributes(List<AttributeDto> allAttributes) {
        this.allAttributes = allAttributes;
    }

    public AttributeDto getAttributeDto() {
        return attributeDto;
    }

    public void setAttributeDto(AttributeDto attributeDto) {
        this.attributeDto = attributeDto;
    }

    public List<Integer> getCategoryAttributes() {
        return categoryAttributes;
    }

    public void setCategoryAttributes(List<Integer> categoryAttributes) {
        this.categoryAttributes = categoryAttributes;
    }


}
