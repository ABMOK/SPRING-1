package pl.com.mnemonic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.com.mnemonic.ems.dao.interfaces.UserDaoInterface;
import pl.com.mnemonic.ems.entity.Role;
import pl.com.mnemonic.ems.entity.User;
import pl.com.mnemonic.ems.entity.UserRole;

@Service("loginService")
@Transactional
public class LoginService implements UserDetailsService {

	static final Logger LOGGER = Logger.getLogger(LoginService.class);
	@Autowired
	UserDaoInterface userDao;
	
	public LoginService(){}
	
	public LoginService(UserDaoInterface userDao) {
		this.userDao = userDao;
	}
	
	

	@Override
	public UserDetails loadUserByUsername(String mail)	throws UsernameNotFoundException {
		User user = userDao.findByMail(mail);
		if( user == null )
            throw new UsernameNotFoundException( "User not found!" );
		LOGGER.info("Login service looking for user: "+user.getMail()+" "+user.getName()+" "+user.getSurname());
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		for(UserRole userRole:(Set<UserRole>) user.getUserRoles()){
			Role role = userRole.getRole();
			LOGGER.info("ROLE: " +role.getRoleName());
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		return new org.springframework.security.core.userdetails.User(user.getMail(), user.getPassword(), authorities);
	}

}
