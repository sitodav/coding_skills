package sito.davide.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDTO implements Serializable
{
	private static final long serialVersionUID = 7234170190068793766L;
	
	@JsonIgnore
	private Long id;
	
	private String userNumber;
	private String username;
	private String firstName;
	private String secondName;
	private String telephone;
	List<OrderDTO> orders = new ArrayList<>();
	
	
	@Override
	public String toString()
	{
		return "UserDTO [id=" + id + ", userNumber=" + userNumber + ", username=" + username + ", firstName=" + firstName + ", secondName=" + secondName + "]";
	}
	
	
}
