package pl.com.mnemonic.ems.dao.interfaces;

import java.util.List;
import java.util.Map;

import pl.com.mnemonic.ems.entity.Address;
public interface AddressDaoInterface extends GenericDaoInterface<Address,Integer>{
public List<Address> getSimilarAddressByCriteria(Map<String, String> criteria);
}
