package sito.davide.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import lombok.RequiredArgsConstructor;
import sito.davide.config.jwt.JWTFilter;
import sito.davide.config.jwt.JWTHelper;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    InvalidCredentialsHandlerOnSpringSecurityEndpoints authEntryPoint;

    @Autowired
    JWTHelper jwtHelper;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        		//we create and handler on exception, that translate to forbidden 403 when:
        		//the user tries to access a protected endpoint, without giving a jwt in the authorization header (that would set its security context)
        		//the user tries to access a protected endpoint giving an invalid jwt in the authorization header
        		.exceptionHandling(customizer -> customizer.authenticationEntryPoint(authEntryPoint)) 
        		//adding our JWT filter as a basic authentication filter (which is the default spring security we left)
        		.addFilterBefore(new JWTFilter(jwtHelper), BasicAuthenticationFilter.class)
        		//disabling csrf token
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((requests) -> requests
                		//open /auth/login and register (where we use our custom manual implementation )
                        .requestMatchers(HttpMethod.POST, "/auth/login", "/auth/register").permitAll() 
                        //and cover all the others with default auth (that will be prevented by the jwt filter installed on top of chain)
                        .anyRequest().authenticated()); 
        
        return http.build();
    }
}
