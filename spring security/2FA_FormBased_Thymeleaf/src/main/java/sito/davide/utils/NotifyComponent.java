package sito.davide.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sito.davide.dto.UserDTO;
import sito.davide.service.external.MailClient;
import sito.davide.service.external.SlackClient;
import sito.davide.service.external.SmsClient;
import sito.davide.service.external.WhatsupClient;

@Component
public class NotifyComponent
{
	
	@Autowired
	MailClient mailClient;
	
	@Autowired
	SlackClient slackClient;
	
	@Autowired
	WhatsupClient wzClient;
	
	@Autowired
	SmsClient smsClient;
	
	
	public void sendNotifiche(UserDTO userDTO) throws Exception
	{
		if(userDTO.getNotifyMail())
		{
			mailClient.sendEmail(userDTO);
		}
		if(userDTO.getNotifySlack())
		{
			slackClient.sendSlackMessage(userDTO);
		}
		if(userDTO.getNotifySms())
		{
			smsClient.sendSms(userDTO);
		}
		if(userDTO.getNotifyWhatsup())
		{
			wzClient.sendWhatsupMessage(userDTO);
		}
	}
}
