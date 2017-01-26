package pl.com.mnemonic.ems.commons.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

public class AttributeDto {
    private Integer id;
    @NotEmpty
    @NotNull
    private String attributeName;
    private String roleName;
    private List<CategoryDto> categories;

    public AttributeDto() {
    }

    public AttributeDto(int id, String attributeName) {
        this.id = id;
        this.attributeName = attributeName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<CategoryDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDto> categories) {
        this.categories = categories;
    }


}
