package sito.davide.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDTO implements Serializable
{
	private static final long serialVersionUID = -6293208392436124099L;
	private String message;
	
	public ErrorDTO() {
		
	}
	
	public ErrorDTO(String msg)
	{
		this.message = msg;
	}
	
}
