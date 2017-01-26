package pl.com.mnemonic.ems.dao.interfaces;

import java.util.List;

import pl.com.mnemonic.ems.entity.MediaCounter;

public interface MediaCounterDaoInterface extends GenericDaoInterface<MediaCounter, Integer>{
	public List<MediaCounter> getPlaceMediaCounters(int idplace);
	public List<MediaCounter> getFacilityCommonMediaCounters(int idmedia);
}
