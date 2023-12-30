package sito.davide.conf.security;

import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
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
	@Value("${jwt.secret}")
	private String jwtSecretKey;
	
	
	@Override
	public void afterPropertiesSet() throws Exception
	{
		this.jwtSecretKey = Base64.getEncoder().encodeToString(this.jwtSecretKey.getBytes());	
	}
	
	
	public UserDTO validateTokenAndReturnUserDTO(String jwtToken) throws JWTVerificationException
	{
		Algorithm algo = Algorithm.HMAC256(this.jwtSecretKey);
		JWTVerifier verifier = JWT.require(algo).build();
		DecodedJWT decodedJWT = verifier.verify(jwtToken);
		//verify will throw exception if the token is invalid , the exception will be translated as InvalidCredentialsHandlerOnSpringSecurity...->403 and ErrorDTO
		//otherwise if we arrive here the token is valid
		//getting the claims
		String username = decodedJWT.getSubject();//decodedJWT.getClaim("username"); username is both subject and claim
		String firstname = decodedJWT.getClaim("firstname").asString();
		String lastname = decodedJWT.getClaim("lastname").asString();
		UserDTO validUserDTO = new UserDTO(null,firstname,lastname,username,null,jwtToken);
		return validUserDTO;
	 
	}
	
	public String createJwtToken(UserDTO user)
	{
		Date now = new Date();
		Date endDate = new Date( now.getTime() + (360 * 1000)); 
		
		Algorithm algo = Algorithm.HMAC256(this.jwtSecretKey);
		
		String jwtTokenString = JWT.create()
		   .withClaim("username",user.getUsername())
		   .withClaim("firstname", user.getFirstname())
		   .withClaim("lastname",user.getLastname())
		   .withSubject(user.getUsername())
		   .withExpiresAt(endDate)
		   .withIssuedAt(now)
		   .sign(algo);
		
		return jwtTokenString;
	}
	
}
