package sito.davide.dto;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class ErrorDTO implements Serializable
{
	private static final long serialVersionUID = 809720853620130670L;
	private String message;
	
	public ErrorDTO() {}
	public ErrorDTO(String message) {this.message = message;}
	
	
}
