package sito.davide.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class UserDTO implements Serializable  
{
	private static final long serialVersionUID = -3277820937398801902L;

	private Long idUser;
	
	@NotEmpty(message="Username obbligatoria")
	private String username;
	
	@Email
	@NotEmpty(message="Email obbligatoria")
	private String email;
	
	@Pattern(regexp="^[0-9]*$",message="Codice fiscale non valido")
	@NotEmpty(message="Numero di cellulare obbligatorio")
	private String telephone;
	
	@NotEmpty(message="Password obbligatoria")
	private String password;
	
	@NotEmpty(message="Conferma password obbligatoria")
	private String passwordConfirmation;
	
	@Pattern(regexp="^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$",message="Codice fiscale non valido")
	private String cf;
	
	@NotEmpty(message="Documento allegato obbligatorio")
	private byte[] doc;
	
	/*se 2FA o no */
	private boolean twoFactAuth;
	
	private String otpQR;
//	private String otpSecret;
	
	/*tipo di notifica */
	private Boolean notifyMail;
	private Boolean notifySms;
	private Boolean notifyWhatsup;
	private Boolean notifySlack;

}
