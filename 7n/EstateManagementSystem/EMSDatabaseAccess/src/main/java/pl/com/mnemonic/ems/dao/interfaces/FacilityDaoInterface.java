package pl.com.mnemonic.ems.dao.interfaces;

import java.util.List;

import pl.com.mnemonic.ems.entity.Facility;

public interface FacilityDaoInterface extends GenericDaoInterface<Facility,Integer>{
public List<Facility> getFacilityByAddressId(Integer addressId);
}
