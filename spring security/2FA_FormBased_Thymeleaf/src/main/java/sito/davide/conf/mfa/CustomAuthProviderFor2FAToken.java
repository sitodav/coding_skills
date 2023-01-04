package sito.davide.conf.mfa;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import sito.davide.dao.TUserRepository;
import sito.davide.entity.TUser;
import sito.davide.utils.OTPManager;

/*
 * La form based auth di spring boot automaticamente controlla
 * i soli campi in input (post form) username, password.
 * Per controllare anche con il campo del token OTP, dobbiamo
 * creare un nuovo AuthenticationDetails wrappato in un authenticationDetailsSource
 * e registrarli sulla auth chain (e dobbiamo inoltre creare un nuovo 
 * authentication provider che usa questo nuovo details del token, e poi continua con il flusso normale)
 * Questa è la classe del custom auth provider
 */

public class CustomAuthProviderFor2FAToken extends DaoAuthenticationProvider {

    @Autowired
    private TUserRepository userRepository;
    
    @Autowired
    OTPManager otpManager;
    

    /*
     * Questo metodo deve fare il controllo rispetto al detail del token ricevuto
     * controllandolo rispetto al secret salvato per l'utente sul db 
     * (l'app del client usando il il qr precedentemente salvato in fase di registrazione
     * genera un codice, e questo viene paragonato rispetto al secret salvato per l'utente in fase di registrazione)
     * Nel caso in cui il check del token vada a buon fine (o non sia proprio abilitato per l'utente) si prosegue
     * comunque verso il controllo classico username/pw di spring security ritornando un UsernamePasswordAuthenticationToken
     */
    @Override
    public Authentication authenticate(Authentication auth)
      throws AuthenticationException {
        String tfaCode 
          = ((TokenValidationDetails) auth.getDetails())
            .getVerificationCode();
        Optional<TUser> u = userRepository.findByUsernameIgnoreCase(auth.getName());
        if ((!u.isPresent())) {
            throw new BadCredentialsException("Utente non valido");
        }
        TUser user = u.get();
        if (user.isTwoFactAuth()) {
        	boolean validOtp = otpManager.verifyOTPCode(tfaCode, user.getOtpSecret());
            if (!validOtp) {
                throw new BadCredentialsException("Codice 2FA non valido");
            }
        }
        
        /*dobbiamo rimappare a spring user per far continuare il flusso
         * con la classica auth user e pw
         */
        User springUser = new User(user.getUsername(), user.getPassword(), new ArrayList<>());
        Authentication result = super.authenticate(auth);
        return new UsernamePasswordAuthenticationToken(
        			springUser, result.getCredentials(), result.getAuthorities());
    }

    private boolean validLongNumber(String code) {
        try {
            Long.parseLong(code);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}