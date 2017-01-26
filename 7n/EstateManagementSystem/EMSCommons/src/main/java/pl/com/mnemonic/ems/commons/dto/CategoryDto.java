package pl.com.mnemonic.ems.commons.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

public class CategoryDto {
	private Integer idcategory;
	@NotEmpty
	@NotNull
	private String categoryName;
	@NotEmpty
	@NotNull
	private String categoryDescriptionShort;
	private String categoryDescriptionLong;
	private String categoryIcon;
	private List<AttributeDto> categoryAttributes;
	
	public CategoryDto(){}

	public Integer getIdcategory() {
		return idcategory;
	}

	public void setIdcategory(Integer idcategory) {
		this.idcategory = idcategory;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDescriptionShort() {
		return categoryDescriptionShort;
	}

	public void setCategoryDescriptionShort(String categoryDescriptionShort) {
		this.categoryDescriptionShort = categoryDescriptionShort;
	}

	public String getCategoryDescriptionLong() {
		return categoryDescriptionLong;
	}

	public void setCategoryDescriptionLong(String categoryDescriptionLong) {
		this.categoryDescriptionLong = categoryDescriptionLong;
	}

	public String getCategoryIcon() {
		return categoryIcon;
	}

	public void setCategoryIcon(String categoryIcon) {
		this.categoryIcon = categoryIcon;
	}

	public List<AttributeDto> getCategoryAttributes() {
		return categoryAttributes;
	}

	public void setCategoryAttributes(List<AttributeDto> categoryAttributes) {
		this.categoryAttributes = categoryAttributes;
	}
	
}
