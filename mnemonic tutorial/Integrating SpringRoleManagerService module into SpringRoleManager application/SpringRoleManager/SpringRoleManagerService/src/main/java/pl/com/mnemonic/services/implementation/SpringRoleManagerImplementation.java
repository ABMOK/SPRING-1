package pl.com.mnemonic.services.implementation;

import org.springframework.stereotype.Service;

import pl.com.mnemonic.services.interfaces.SpringRoleManagerInterface;

@Service("roleManager")
public class SpringRoleManagerImplementation implements SpringRoleManagerInterface{
	

	@Override
	public String welcome() {
		return "H E L L O from service layer";
	}

}
