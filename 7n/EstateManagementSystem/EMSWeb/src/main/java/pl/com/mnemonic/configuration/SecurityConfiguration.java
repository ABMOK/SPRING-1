package pl.com.mnemonic.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import pl.com.mnemonic.service.LoginService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //authorization on method level
@Import({LoginService.class})
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    UserDetailsService loginService;

    /**
     * Enables application global security
     * Specifies in-memory authentication accounts for safety reasons
     * @param manager Spring Authentication Manager Builder
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder manager) throws Exception {
        manager.userDetailsService(loginService);
        //HARD-CODED ACCESS:
        manager.inMemoryAuthentication().withUser("super@super").password("super").roles("ADMIN");
        manager.inMemoryAuthentication().withUser("user@user").password("user").roles("USER");
    }

    /**
     * Provides application security configuration
     * @param security Spring Http Security implementation
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security.csrf().disable()
                .authorizeRequests()
                .antMatchers("/public**").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/bootstrap/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/upload/**").permitAll()
                .antMatchers("?locale=pl", "?locale=en").permitAll()

                //url-security
                .antMatchers("/administration/**").access("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
                .antMatchers("/estates/**").access("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
                .antMatchers("/tenants/**").access("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
                .antMatchers("/finances/reporting").access("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_OWNER','ROLE_RENTER')")
                .antMatchers("/finances/countersrestart").access("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_OWNER')")
                .antMatchers("/finances/**").access("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
                .antMatchers("/system/**").access("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/index")
                .defaultSuccessUrl("/welcome")
                .failureUrl("/login?error")
                .usernameParameter("mail")
                .passwordParameter("password")
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                //.logoutSuccessUrl("/login?out=1")
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .sessionManagement()
                //.invalidSessionUrl("/login?time=1")
                .maximumSessions(1);
    }


    /**
     * Provides application security configuration
     * @param web Spring Web Security implementation
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

}
