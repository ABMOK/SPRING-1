package pl.com.mnemonic.ems.classified.mappers;

import java.util.List;

import pl.com.mnemonic.ems.commons.dto.AttributeDto;
import pl.com.mnemonic.ems.commons.dto.CategoryDto;
import pl.com.mnemonic.ems.entity.Category;

public class CategoryMapper {

	public static CategoryDto map(Category entity){
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setIdcategory(entity.getIdcategory());
		categoryDto.setCategoryDescriptionShort(entity.getCategoryDescriptionShort());
		categoryDto.setCategoryDescriptionLong(entity.getCategoryDescriptionLong());
		categoryDto.setCategoryName(entity.getCategoryName());
		return categoryDto;
	}
	
	public static Category map(CategoryDto dto){
		Category categoryEntity = new Category();
		if(dto.getIdcategory()!=null && dto.getIdcategory()>0){
			categoryEntity.setIdcategory(dto.getIdcategory());
		}
		categoryEntity.setCategoryDescriptionShort(dto.getCategoryDescriptionShort());
		categoryEntity.setCategoryDescriptionLong(dto.getCategoryDescriptionLong());
		categoryEntity.setCategoryName(dto.getCategoryName());
		return categoryEntity;
	}
	
	public static Category map(CategoryDto dto, Category categoryEntity){
		categoryEntity.setCategoryDescriptionShort(dto.getCategoryDescriptionShort());
		categoryEntity.setCategoryDescriptionLong(dto.getCategoryDescriptionLong());
		categoryEntity.setCategoryName(dto.getCategoryName());
		return categoryEntity;
	}
	
	public static CategoryDto map(Category entity, List<AttributeDto> attributes){
		CategoryDto categoryDto = map(entity);
		categoryDto.setCategoryAttributes(attributes);		
		return categoryDto;
	}
}
