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
public class MailClient
{	
	private static final Logger log = LoggerFactory.getLogger(MailClient.class);
	
	@Value("${spring.mail.username}")
	private String applicationMailAccountAddress;
	
	@Autowired
	private JavaMailSender mailSender;

	public void sendEmail(UserDTO loggedUserDTO)
	{
		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy HH:MM:ss");
		
		 
		SimpleMailMessage message = new SimpleMailMessage();
		 
		message.setFrom(applicationMailAccountAddress);
		message.setTo(loggedUserDTO.getEmail());
		message.setSubject("Account logged");
		message.setText("Ciao "+loggedUserDTO.getUsername() + ", abbiamo notato una login da parte tua in data "+sdf.format(new Date()));
		 
		/*NB: QUESTI DATI NON ANDREBBERO LOGGATI PERCHE' SONO SENSIBILI
		 * E' SOLO PER MOSTRARE FUNZIONAMENTO MOCKATO
		 */
//		mailSender.send(message);
		log.info("[MOCK]Sending email to "+loggedUserDTO.getUsername() + ", address : "+loggedUserDTO.getEmail());
		
		
	}
}
