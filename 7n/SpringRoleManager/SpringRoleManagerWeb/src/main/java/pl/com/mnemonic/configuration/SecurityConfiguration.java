package pl.com.mnemonic.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService loginService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder manager) throws Exception {
		manager.userDetailsService(loginService);
		// +HARD-CODED ACCESS EXAMPLE:
		manager.inMemoryAuthentication().withUser("mnemonic@mnemonic.mn").password("mnemonic").roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity security) throws Exception {
		security.csrf().disable().authorizeRequests()
				.antMatchers("/public**").permitAll()
				.antMatchers("/resources/**").permitAll()
				.antMatchers("/administration/**").access("hasRole('ROLE_ADMIN')")
				.and().formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/index")
				.defaultSuccessUrl("/index")
				.failureUrl("/login?error")
				.usernameParameter("email")
				.passwordParameter("password")
				.and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login?out=1")
				.deleteCookies("JSESSIONID")
				.invalidateHttpSession(true)
				.and().sessionManagement()
				.invalidSessionUrl("/login?time=1")
				.maximumSessions(1);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

}
