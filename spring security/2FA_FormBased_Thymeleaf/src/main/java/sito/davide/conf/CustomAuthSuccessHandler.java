package sito.davide.conf;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import sito.davide.dto.UserDTO;
import sito.davide.service.UserService;
import sito.davide.service.external.MailClient;
import sito.davide.service.external.SlackClient;
import sito.davide.service.external.SmsClient;
import sito.davide.service.external.WhatsupClient;
import sito.davide.utils.NotifyComponent;

/*
 * Visto che spring security si occupa di gestire lui la form based auth sulle chiamate post alla login,
  e mette lui in sessione il principal autenticato, dobbiamo creare un handler che 
  quando l'auth va a buon fine sulla post:login si occupi di mappare il principal messo in sessione da spring
  con un userDTO in quanto nel nostro FE lavoriamo con questo tipo di oggetto in sessione
 */
@Component
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler
{

	private static final Logger log = LoggerFactory.getLogger(CustomAuthSuccessHandler.class);
	@Autowired
	HttpSession session;

	@Autowired
	UserService userService;
	
	@Autowired
	NotifyComponent componentNotifiche;


	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
				throws IOException, ServletException
	{

		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	 
		String userName = "";

		if (authentication.getPrincipal() instanceof Principal)
		{
			userName = ((Principal) authentication.getPrincipal()).getName();

		}
		else
		{
			userName = ((User) authentication.getPrincipal()).getUsername();
		}

		try
		{

			UserDTO userDTO = userService.getUserByUsername(userName);
			if (null != userDTO)
				session.setAttribute("user", userDTO);
			
			/*
			 * inviamo le notifiche
			 */
			try
			{
				componentNotifiche.sendNotifiche(userDTO);
			}
			catch(Exception ex)
			{
				log.error("Errore notifiche",ex);
			}
			
			

		}
		catch (Exception ex)
		{
			log.error("Error", ex);
		}

		redirectStrategy.sendRedirect(request, response, "/home");

	}

}