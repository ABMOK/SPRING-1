package pl.com.mnemonic.ems.commons.dto;


import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;


public class ClassifiedadDto {
	private int idclassifiedAd;
	private CategoryDto category;	
	private UserDto user;
	@NotEmpty
	@NotNull
	private String title;
	@NotEmpty
	@NotNull
	private String shortDescription;
	@NotEmpty
	@NotNull
	private String longDescription;
	private String photolink;
	
	public ClassifiedadDto(){}
	
	public int getIdclassifiedAd() {
		return idclassifiedAd;
	}
	public void setIdclassifiedAd(int idclassifiedAd) {
		this.idclassifiedAd = idclassifiedAd;
	}
	public CategoryDto getCategory() {
		return category;
	}
	public void setCategory(CategoryDto category) {
		this.category = category;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getLongDescription() {
		return longDescription;
	}
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}
	public String getPhotolink() {
		return photolink;
	}
	public void setPhotolink(String photolink) {
		this.photolink = photolink;
	}
}
