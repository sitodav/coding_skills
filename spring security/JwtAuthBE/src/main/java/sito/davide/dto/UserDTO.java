package sito.davide.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO implements Serializable
{
	 
	private static final long serialVersionUID = -4411835779411655582L;
	
	private Long id;
	
	private String username;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //to avoid going back to client
	private String password;
	
	private String email;
	
	private String token; //the jwt to send to the client (the client will send it back as AUTHORIZATION parameter of the form : Bearer JWT
	
	public UserDTO() {}
	
	public UserDTO(Long id, String username, String password, String email)
	{
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	
	@Override
	public String toString()
	{
		return "UserDTO [" + (id != null ? "id=" + id + ", " : "") + (username != null ? "username=" + username + ", " : "")
					+ (password != null ? "password="  + "***, " : "") + (email != null ? "email=" + email : "") + "]";
	} 
	
	
	
	
}
