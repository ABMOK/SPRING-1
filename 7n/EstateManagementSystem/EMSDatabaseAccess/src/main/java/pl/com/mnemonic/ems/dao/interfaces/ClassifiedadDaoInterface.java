package pl.com.mnemonic.ems.dao.interfaces;

import java.util.List;

import pl.com.mnemonic.ems.entity.Classifiedad;


public interface ClassifiedadDaoInterface extends GenericDaoInterface<Classifiedad,Integer>{

	List<Classifiedad> getAdsByCategory(Integer categoryId);

}
