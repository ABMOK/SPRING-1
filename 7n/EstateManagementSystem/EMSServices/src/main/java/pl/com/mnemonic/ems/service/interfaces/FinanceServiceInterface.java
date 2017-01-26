package pl.com.mnemonic.ems.service.interfaces;

import pl.com.mnemonic.ems.commons.dto.*;

import java.util.List;

public interface FinanceServiceInterface {
    public List<MediaCounterDto> generateMediaCountersRestart(Integer ide, Integer idp);

    public void updatePlaceCounters(List<MediaCounterDto> mediaCounters, Integer ide, Integer idp);

    public void updatePlaceCounters(List<MediaCounterDto> mediaCounters, UserDto userDto);


}
