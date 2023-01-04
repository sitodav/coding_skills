package sito.davide.conf.mfa;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

/*
 * La form based auth di spring boot automaticamente controlla
 * i soli campi in input (post form) username, password.
 * Per controllare anche con il campo del token OTP, dobbiamo
 * creare un nuovo AuthenticationDetails wrappato in un authenticationDetailsSource
 * e registrarli sulla auth chain (e dobbiamo inoltre creare un nuovo 
 * authentication provider che usa questo nuovo details del token, e poi continua con il flusso normale)
 * Questa è la classe del detail source.
 */

@Component
public class TokenValidationDetailsSource implements 
  AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> {
    
    @Override
    public WebAuthenticationDetails buildDetails(HttpServletRequest context) {
        return new TokenValidationDetails(context);
    }
}
 
