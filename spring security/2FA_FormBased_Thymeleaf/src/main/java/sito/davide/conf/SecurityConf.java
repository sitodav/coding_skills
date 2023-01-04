package sito.davide.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import sito.davide.conf.mfa.CustomAuthProviderFor2FAToken;
import sito.davide.service.UserService;
import sito.davide.utils.Urls;

@Configuration 
@EnableWebSecurity
@SuppressWarnings("deprecation") 
public class SecurityConf extends WebSecurityConfigurerAdapter
{

	@Autowired
	UserService myUserServiceImpl;
	
	/*questo è per aggiungere info dell'utente nella sessione con un handler di successo custom
	 */
	@Autowired
	AuthenticationSuccessHandler customAuthSuccessHandler;
	
	/*questo e' per far si che dietro le quinte la form validation di spring 
	 * security consideri anche il 2fa_code come parametro di validazione della request post
	 */
	@Autowired
	AuthenticationDetailsSource tokenDetailSource;
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.
			csrf().disable() /*per comoditàx di testing */
			.authorizeRequests()
			.antMatchers(Urls.CSS_PATH).permitAll()
				.antMatchers(Urls.REGISTER_PATH).permitAll()
				//.antMatchers(Urls.LOGIN_PATH).permitAll()
			.anyRequest()
				.authenticated()
				.and()
				.formLogin()
				.loginPage(Urls.LOGIN_PATH)
					.authenticationDetailsSource(tokenDetailSource)
					.failureUrl(Urls.LOGINERROR_PATH)
					.defaultSuccessUrl(Urls.HOME_PATH).permitAll()
					.successHandler(customAuthSuccessHandler).permitAll();
				/*.and()
				.logout()
					.invalidateHttpSession(true)
	                .clearAuthentication(true)
					.logoutSuccessUrl(Urls.LOGIN_PATH).permitAll(); */
				 
	}
	
 
	
	public void configureGlobal(@Autowired AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserServiceImpl)
			.passwordEncoder(passwordEncoder());
	}
	
	/*il nostro encoder */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authProvider() {
		CustomAuthProviderFor2FAToken authProvider = new CustomAuthProviderFor2FAToken();
	    authProvider.setUserDetailsService(myUserServiceImpl);
	    authProvider.setPasswordEncoder(passwordEncoder());
	    return authProvider;
	}

	 
}
