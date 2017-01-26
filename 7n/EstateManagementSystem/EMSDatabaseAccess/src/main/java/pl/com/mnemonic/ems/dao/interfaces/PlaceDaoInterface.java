package pl.com.mnemonic.ems.dao.interfaces;

import java.util.List;

import pl.com.mnemonic.ems.entity.Place;

public interface PlaceDaoInterface extends GenericDaoInterface<Place,Integer>{
public List<Place> getFacilityPlaces(int idFacility);
}
