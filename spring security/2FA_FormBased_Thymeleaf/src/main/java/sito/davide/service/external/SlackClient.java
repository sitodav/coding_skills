package sito.davide.service.external;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import sito.davide.dto.UserDTO;

@Component
public class SlackClient
{	
	private static final Logger log = LoggerFactory.getLogger(SlackClient.class);
 

	public void sendSlackMessage(UserDTO loggedUserDTO)
	{
		/*NB: QUESTI DATI NON ANDREBBERO LOGGATI PERCHE' SONO SENSIBILI
		 * E' SOLO PER MOSTRARE FUNZIONAMENTO MOCKATO
		 */
		log.info("[MOCK]Sending slack message to "+loggedUserDTO.getUsername() + ", slack account : "+loggedUserDTO.getEmail());
		
	}
}
