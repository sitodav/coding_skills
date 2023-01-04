package sito.davide.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

@Entity
@Table(name="TbUser")
@Data
public class TUser
{

	@Id
	@Column(name="ID_USER")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUser;
	
	@Column(name="USERNAME", nullable=false,unique=true)
	private String username;
	
	@Column(name="CODICE_FISCALE", nullable=false, unique=true)
	private String cf;
	
	@Column(name="EMAIL", nullable=false, unique=true)
	private String email;
	
	@Column(name="TELEPHONE", nullable=false , unique = true)
	private String telephone;
	
	@Column(name="PASSWORD", nullable=false)
	private String password;
	
	@Column(name="2FA")
	private boolean twoFactAuth;
	
	@Column(name="DOC_ALLEGATO", nullable=false)
	private byte[] doc;
	
	@Column(name="NOTIFY_MAIL")
	private Boolean notifyMail;

	@Column(name="NOTIFY_WHATSUP")
	private Boolean notifyWhatsup;
	
	@Column(name="NOTIFY_SLACK")
	private Boolean notifySlack;

	@Column(name="NOTIFY_SMS")
	private Boolean notifySms;
	
	@Column(name="OTP_SECRET")
	private String otpSecret;	
	
	
}
