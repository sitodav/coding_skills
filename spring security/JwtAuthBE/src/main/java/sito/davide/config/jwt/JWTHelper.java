package sito.davide.config.jwt;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import sito.davide.dto.UserDTO;

@Component
public class JWTHelper implements InitializingBean
{

	private static final Logger log = LoggerFactory.getLogger(JWTHelper.class);
	
	@Value("${jwt.security.secret-key}")
	private String jwtSecretKey;
	
	@Value("${jwt.security.token.duration.seconds}")
	private Long durationMs;

	@Override
	public void afterPropertiesSet() throws Exception
	{
		//so we won't have the plain key in memory
		this.jwtSecretKey = Base64.getEncoder().encodeToString(this.jwtSecretKey.getBytes());
	}

	
	public Authentication validateJWTToken(String jwtToken) throws JWTVerificationException
	{
		try
		{
			Algorithm algo = Algorithm.HMAC256(this.jwtSecretKey);
			JWTVerifier verifier = JWT.require(algo).build();
			DecodedJWT decodedJWT = verifier.verify(jwtToken);
			//verify will throw exception if the token is invalid , the exception will be translated as InvalidCredentialsHandlerOnSpringSecurity...->403 and ErrorDTO
			//otherwise if we arrive here the token is valid
			//getting the claims
			String username = decodedJWT.getSubject();//decodedJWT.getClaim("username"); username is both subject and claim
			String mail = decodedJWT.getClaim("email").asString();
			UserDTO user = new UserDTO(null,username,null,mail);
			//wrapping the user in a class implementing Authentication
			//TODO: here we should find the list of PERMISSIONS for the user (from the db) and set them instead of empty collection
			return new UsernamePasswordAuthenticationToken(user,null, Collections.emptyList());
			
		}
		catch(JWTVerificationException ex)
		{
			log.warn("Attempt for invalid jwt login ");
			throw ex;
		}
		
	}
	
	
	public String createJWTToken(UserDTO user)
	{
		Date now = new Date();
		Date endDate = new Date( now.getTime() + (durationMs * 1000)); 
		
		Algorithm algo = Algorithm.HMAC256(this.jwtSecretKey);
		
		String jwtTokenString = JWT.create()
		   .withClaim("username",user.getUsername())
		   .withClaim("email", user.getEmail())
		   .withSubject(user.getUsername())
		   .withExpiresAt(endDate)
		   .withIssuedAt(now)
		   .sign(algo);
		
		return jwtTokenString;
	}
	
	
}
