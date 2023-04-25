package sito.davide.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import sito.davide.service.impl.UserServiceImpl;

/*
 * Security configuration:
 * basic auth with session (for sake of simplicity)
 * 
 */

@SuppressWarnings("deprecation")
@EnableWebSecurity //to enable spring security customization
@EnableMethodSecurity(prePostEnabled = true) //to enable @preAuthorize on Apis
@Configuration
public class SecurityConf  {

	@Autowired
	UserServiceImpl myUserServiceImpl;

	

	/*
	 * here we disable csrf token , and use httpBasic 
	 */
	 @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		 	http
		 	.csrf().disable() //no csfr token control
			.authorizeHttpRequests()
			.anyRequest().authenticated()
			.and().httpBasic(); //basic auth
			 
	        
		 	http.headers().frameOptions().disable(); //to allow h2-console x frame
		 	return http.build();
	    }
	
	
	
	 

	/*
	 * Here we give as userDetailsService to spring, our custom implementation
	 * and the BCrypt for password encryption check (the password has to be saved as bcrypt on the 
	 * db)
	 */
	public void configureGlobal(@Autowired AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserServiceImpl)
			.passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public static String getLoggedUsername()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = ((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername();
		return username;
	}
	
	@SuppressWarnings("unchecked") 
	public static List<String> getLoggedUserAuthorities()
	{
		Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)    SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		
		return 
			Optional.ofNullable(authorities).orElse(new ArrayList<>())
			.stream()
			.map(auth -> auth.getAuthority())
			.collect(Collectors.toList());
		
		
		 
	}
	 

	 
}
