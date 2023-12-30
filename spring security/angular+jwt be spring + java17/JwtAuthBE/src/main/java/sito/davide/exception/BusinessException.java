package sito.davide.exception;

import org.springframework.http.HttpStatusCode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessException extends RuntimeException
{
	private static final long serialVersionUID = 3533792164466431950L;
	private String message;
	private HttpStatusCode httpStatus;
	
	public BusinessException () {}
	public BusinessException (String msg, HttpStatusCode httpStatus)
	{
		this.message = msg;
		this.httpStatus = httpStatus;
	}
}
