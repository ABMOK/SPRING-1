package pl.com.mnemonic.ems.classified.mappers;

import pl.com.mnemonic.ems.commons.dto.AttributeDto;
import pl.com.mnemonic.ems.commons.dto.CategoryDto;
import pl.com.mnemonic.ems.entity.Attribute;
import pl.com.mnemonic.ems.entity.Role;

import java.util.List;

public class AttributeMapper {
	
	public static AttributeDto map(Attribute attribute, Role role) {
		AttributeDto attributeDto = new AttributeDto();
		attributeDto.setId(attribute.getIdattribute());
		attributeDto.setAttributeName(attribute.getAttributeName());
		if(role!=null){
			attributeDto.setRoleName(role.getRoleCode());
		}
		return attributeDto;
	}
	
	public static AttributeDto map(Attribute attribute, List<CategoryDto> categories, Role role) {
		AttributeDto attributeDto = new AttributeDto();
		attributeDto.setId(attribute.getIdattribute());
		attributeDto.setAttributeName(attribute.getAttributeName());
		if(role !=null ){
			attributeDto.setRoleName(role.getRoleName());
		}
		attributeDto.setCategories(categories);		
		return attributeDto;
	}
}
