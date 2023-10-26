package sito.davide.misc;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessException extends Exception
{
	 
	private static final long serialVersionUID = 9017758548887754893L;
	private String message;
	private HttpStatus httpStatus;
	
	public BusinessException() {}
	public BusinessException(String msg, HttpStatus status)
	{
		super(msg);
		this.message = msg;
		this.httpStatus = status;
	}
}
