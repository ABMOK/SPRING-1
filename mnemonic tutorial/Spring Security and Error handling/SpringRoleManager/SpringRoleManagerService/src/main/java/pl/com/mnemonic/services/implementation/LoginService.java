package pl.com.mnemonic.services.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pl.com.mnemonic.dao.interfaces.UserDaoInterface;
import pl.com.mnemonic.dto.UserDto;
import pl.com.mnemonic.dto.UserRole;
import pl.com.mnemonic.entity.SystemUser;
import pl.com.mnemonic.services.interfaces.SpringRoleManagerInterface;

@Service
public class LoginService implements UserDetailsService {

	private static final String ROLE_PREFIX = "ROLE_";

	@Autowired
	UserDaoInterface systemUserDao;

	@Autowired
	SpringRoleManagerInterface roleManager;

	public LoginService() {
	}

	public LoginService(UserDaoInterface systemUserDao) {
		this.systemUserDao = systemUserDao;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		SystemUser user = systemUserDao.findByMail(username);

		if (user == null) {
			throw new UsernameNotFoundException("User not found!");
		}

		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();

		UserDto userDto = roleManager.readUser(user.getIdSystemUser());

		for (UserRole oneRole : userDto.getUserRoles()) {
			authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX.concat(oneRole.name())));
		}
		
		return new User(user.getEmail(), user.getPassword(), authorities);
	}

}
