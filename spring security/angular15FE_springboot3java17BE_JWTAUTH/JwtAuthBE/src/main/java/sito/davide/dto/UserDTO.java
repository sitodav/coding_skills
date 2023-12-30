package sito.davide.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO implements Serializable
{
	private static final long serialVersionUID = 3183680795584238869L;
	
	private Long id;
	private String firstname;
	private String lastname;
	private String username;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //to avoid going back to client
	private String password;
	
	private String jwt;
	
	public UserDTO() {}
	public UserDTO(Long id, String firstname, String lastname, String username, String password,String jwt)
	{
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.jwt = jwt;
	}
	
	
	@Override
	public String toString()
	{
		return "UserDTO [" + (id != null ? "id=" + id + ", " : "") + (firstname != null ? "firstname=" + firstname + ", " : "")
					+ (lastname != null ? "lastname=" + lastname + ", " : "") + (username != null ? "username=" + username + ", " : "")
					+ (password != null ? "password=" + password : "") + "]";
	}
 
	

 
	
	
}
