package sito.davide.conf.security;

import java.io.IOException;
import java.util.Collections;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sito.davide.dto.UserDTO;

//THIS CANNOT BE A COMPONENT BECAUSE OTHERWISE WOULD BE USED FOR EVERY CALL BEING TAKEN AS OncePerRequestFilter
public class CustomJWTFilter extends OncePerRequestFilter
{
	
	public JWTHelper jwtHelper;
	
	public CustomJWTFilter(JWTHelper jwtHelper) {
		this.jwtHelper = jwtHelper;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
	{
		
		String authHeaderValue = request.getHeader("AUTHORIZATION");
		if(!StringUtils.isBlank(authHeaderValue))
		{
			if(authHeaderValue.split(" ").length == 2)
			{
				if("Bearer".equals(authHeaderValue.split(" ")[0]))
				{
					String jwtValue = authHeaderValue.split(" ")[1];
					
					try
					{
						UserDTO validUserDTO = this.jwtHelper.validateTokenAndReturnUserDTO(jwtValue);
						//if we arrive here, no exception on validate so the token is valid
						//we have to set the valid user in memory as logged principal
						AbstractAuthenticationToken authPrincipal = new UsernamePasswordAuthenticationToken(validUserDTO,null, Collections.emptyList());
						SecurityContextHolder.getContext().setAuthentication(authPrincipal); 
					}
					catch(Exception ex) //exception thrown from validate token
					{
						SecurityContextHolder.clearContext(); //if validateJWTTOken throws exception (invalid jwt) we have to clear the security context
						//we bubble up the exception so it will be catched from the exception handler registered in security and thrown back as invalid credentials
						throw ex;
					}
				}
			}
		}
		
		filterChain.doFilter(request, response);
		
	}
	
}
