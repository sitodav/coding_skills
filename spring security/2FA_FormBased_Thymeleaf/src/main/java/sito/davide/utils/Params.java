package sito.davide.utils;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Component(value="Params") 
@Getter
public class Params
{
	public static final String ERROR_FLAG = "errorFlag";
	public static final String ERROR_MESSAGE = "errorMessage";
	public static final String SUCCESS_FLAG = "successFlag";
	public static final String SUCCESS_MESSAGE = "successMessage";
	
	
	
}
