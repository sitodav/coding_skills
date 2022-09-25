package sito.dskij.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import sito.dskij.service.UserServiceImpl;

@SuppressWarnings("deprecation")
@EnableWebSecurity //to enable spring security customization
@EnableGlobalMethodSecurity(prePostEnabled = true) //to enable @preAuthorize on Apis
@Configuration
public class SecurityConf extends WebSecurityConfigurerAdapter {

	@Autowired
	UserServiceImpl myUserServiceImpl;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/*
	 * here we disable csrf token , and use httpBasic with no session on every api
	 * endpoint, and we disable the session
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable() //no csfr token control
		.authorizeRequests()
//		.antMatchers(HttpMethod.POST,"/api/v1/student/**").hasAuthority("STUDENT_WRITE") //centralized role based permission on api
//		.antMatchers(HttpMethod.GET,"/api/v1/student/**").hasAuthority("STUDENT_READ")
		.anyRequest().authenticated()
		.and().httpBasic() //basic auth
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //no session
        
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

	 
}
