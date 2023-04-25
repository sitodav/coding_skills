package sito.davide.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import sito.davide.utils.exception.CustomException;

/*Class used to remap the exception bubbling up from controllers
 * to both INTERNAL SERVER ERROR (for generic exceptions) or BAD REQUEST for the
 * Custom Exceptions
 */
@ControllerAdvice
@Component
public class ExceptionHandlerComponent extends ResponseEntityExceptionHandler  {

 
	@ExceptionHandler(CustomException.class)
	protected ResponseEntity<String> handleEccezioneApi(Exception ex) {
		// TODO Auto-generated method stub
		String exMessage = StringUtils.hasLength(ex.getMessage()) ? ex.getMessage() : "Invalid Parameter";
		return new ResponseEntity(exMessage,new HttpHeaders(),HttpStatus.BAD_REQUEST);
	}
	
	 
	
	@ExceptionHandler(Exception.class )
	public ResponseEntity<String>  eccezioniGeneriche(Exception ex){ 
		String exMessage = "Errore Inatteso";
		return new ResponseEntity(exMessage,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	 
	
	 
}
