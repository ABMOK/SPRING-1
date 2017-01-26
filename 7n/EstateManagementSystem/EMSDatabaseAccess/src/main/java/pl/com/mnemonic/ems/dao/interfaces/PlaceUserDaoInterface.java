package pl.com.mnemonic.ems.dao.interfaces;


import java.util.List;

import pl.com.mnemonic.ems.entity.Placeuser;

public interface PlaceUserDaoInterface extends GenericDaoInterface<Placeuser,Integer>{
	public List<Placeuser> getOwners(int idPlace);
	public List<Placeuser> getTenants(int idPlace);
}
