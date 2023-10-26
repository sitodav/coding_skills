package sito.davide.api;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import sito.davide.misc.BusinessException;

@ControllerAdvice
@Component
public class ErrorApi extends ResponseEntityExceptionHandler  {

	
	@ExceptionHandler(Exception.class )
	public ResponseEntity<String>  eccezioniGeneriche(Exception ex){ 
		String exMessage = "Unexpected Error";
		return new ResponseEntity<>(exMessage,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(BusinessException.class )
	public ResponseEntity<Object>  businessException(Exception ex,WebRequest request){ 
		BusinessException ex1 = (BusinessException)ex;
        return handleExceptionInternal(ex1, ex1.getMessage(), 
          new HttpHeaders(), ex1.getHttpStatus(), request);
	}
	 
	
	 
}
