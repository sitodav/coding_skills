package sito.davide.utils.exception;

public class CustomException extends Exception
{
	private static final long serialVersionUID = -2583796253477150533L;

	public CustomException () {
		
	}
	
	public CustomException (String msg)
	{
		super(msg);
	}
}
