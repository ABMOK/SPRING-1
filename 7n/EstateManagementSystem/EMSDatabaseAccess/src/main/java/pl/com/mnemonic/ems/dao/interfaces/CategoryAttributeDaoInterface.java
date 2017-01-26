package pl.com.mnemonic.ems.dao.interfaces;

import java.util.List;

import pl.com.mnemonic.ems.entity.CategoryAttribute;

public interface CategoryAttributeDaoInterface extends GenericDaoInterface<CategoryAttribute,Integer>{
public List<CategoryAttribute> getListByAttributeId(Integer idAttribute);
public List<CategoryAttribute> getListByCategoryId(Integer idCategory);
}
