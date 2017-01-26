package pl.com.mnemonic.ems.dao.interfaces;

import pl.com.mnemonic.ems.entity.Category;



public interface CategoryDaoInterface extends GenericDaoInterface<Category,Integer>{
	public Category getCategoryByCategoryName(String categoryName);
}
