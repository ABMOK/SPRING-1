package pl.com.mnemonic.ems.classified.mappers;

import pl.com.mnemonic.ems.commons.dto.CategoryDto;
import pl.com.mnemonic.ems.commons.dto.ClassifiedadDto;
import pl.com.mnemonic.ems.entity.Category;
import pl.com.mnemonic.ems.entity.Classifiedad;

public class ClassifiedAdMapper {

	public static ClassifiedadDto map(Classifiedad ad, CategoryDto category) {
		ClassifiedadDto dto = new ClassifiedadDto();
		dto.setIdclassifiedAd(ad.getIdclassifiedAd());
		dto.setTitle(ad.getTitle());
		dto.setShortDescription(ad.getShortDescription());
		dto.setLongDescription(ad.getLongDescription());
		dto.setPhotolink(ad.getPhotolink());
		if(category!=null){
			dto.setCategory(category);
		}
		return dto;
	}
	
	public static Classifiedad map(ClassifiedadDto dto, Category category) {
		Classifiedad ad = new Classifiedad();
		ad.setTitle(dto.getTitle());
		ad.setShortDescription(dto.getShortDescription());
		ad.setLongDescription(dto.getLongDescription());
		ad.setPhotolink(dto.getPhotolink());
		if(category!=null){
			ad.setCategory(category);
		}
		return ad;
	}

}
