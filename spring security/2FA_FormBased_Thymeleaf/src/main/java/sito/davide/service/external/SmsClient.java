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
public class SmsClient
{	
	private static final Logger log = LoggerFactory.getLogger(SmsClient.class);
 

	public void sendSms(UserDTO loggedUserDTO)
	{
		/*NB: QUESTI DATI NON ANDREBBERO LOGGATI PERCHE' SONO SENSIBILI
		 * E' SOLO PER MOSTRARE FUNZIONAMENTO MOCKATO
		 */
		log.info("[MOCK]Sending sms message to "+loggedUserDTO.getUsername() + ", telephone number: "+loggedUserDTO.getTelephone());
		
	}
}
