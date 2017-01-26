package pl.com.mnemonic.ems.dao.interfaces;

import java.util.List;

import pl.com.mnemonic.ems.entity.Media;

public interface MediaDaoInterface  extends GenericDaoInterface<Media, Integer>{

	public List<Media> getFacilityMedia(int idFacility);

}
