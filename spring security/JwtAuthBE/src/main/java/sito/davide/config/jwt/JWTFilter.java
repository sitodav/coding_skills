package sito.davide.config.jwt;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*Filtro che, messo a monte di tutti gli endpoint,
 * nel caso in cui stia arrivando authorization header contenente Bearer JWT, controlla che sia valido
 * e nel caso sia valido usa i claims per recuperare l'utente  e metterlo nel security context di spring, in tal modo l'utente sarà validato
 */
public class JWTFilter extends OncePerRequestFilter
{
	private static final String JWT_HEADER_PREFIX = "Bearer";

	private JWTHelper jwtHelper;
	
	public JWTFilter(JWTHelper jwtHelper) {
		this.jwtHelper = jwtHelper;
	}
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
	{
		 
		String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		if(StringUtils.hasLength(authHeader))
		{
			String[] tokens = authHeader.split(" ");
			
			if(tokens.length == 2)
			{
				String authHeaderPrefix = tokens[0];
				String jwtPart = tokens[1];
				
				try
				{
					if(JWT_HEADER_PREFIX.equals(authHeaderPrefix))
					{
						//questo da eccezione se non è valido jwt, se è valido invece setta utente nel security context di spring
						SecurityContextHolder.getContext().setAuthentication(jwtHelper.validateJWTToken(jwtPart)); 
					}
				}
				catch(Exception ex)
				{
					 SecurityContextHolder.clearContext(); //if validateJWTTOken throws exception (invalid jwt) we have to clear the security context
	                    throw ex;
				}
				
			}
			
			
		}
		filterChain.doFilter(request, response);
		
		
	}

}
