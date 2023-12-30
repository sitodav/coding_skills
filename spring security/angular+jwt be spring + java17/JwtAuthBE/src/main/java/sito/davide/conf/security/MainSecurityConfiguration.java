package sito.davide.conf.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sito.davide.dto.ErrorDTO;

@Configuration
@EnableWebSecurity
public class MainSecurityConfiguration
{
	
	;
	
	@Bean
	public AuthenticationEntryPoint registerInvalidCredentialBean()
	{
		/*lambda per classe anonima che implementa interfaccia funzionale AuthenticationEntryPoint di spring security */
		return (HttpServletRequest request, HttpServletResponse response, AuthenticationException authException ) -> {
			
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
	        new ObjectMapper().writeValue(response.getOutputStream(), new ErrorDTO("Invalid Credentials"));
		}; 	
	}
	
	
	@Bean
	public SecurityFilterChain registerSecurityFilterChain(
				@Autowired HttpSecurity httpSecurity, 
				@Autowired AuthenticationEntryPoint customInvalidCredentialHandlerBean ,
				@Autowired JWTHelper jwtHelper) throws Exception 
	{
		/*configuring custom http security */
		httpSecurity
		
			.exceptionHandling(customizer -> customizer.authenticationEntryPoint(customInvalidCredentialHandlerBean))
			.addFilterBefore(new CustomJWTFilter(jwtHelper) , BasicAuthenticationFilter.class)
			.csrf(AbstractHttpConfigurer::disable)
		    .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		    .authorizeHttpRequests((requests) -> requests
                		//open /auth/login and register (where we use our custom manual implementation )
		    			.requestMatchers("/api/v1/auth/**", "/v3/api-docs/**", "/swagger-ui/**").permitAll() //for openAPI
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll() 
                        //and cover all the others with default auth (that will be prevented by the jwt filter installed on top of chain)
                        .anyRequest().authenticated())
		    ; 
		
		return httpSecurity.build();
	}
	
	
	
}
