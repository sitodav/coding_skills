package sito.davide.api;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sito.davide.dto.UserDTO;
import sito.davide.service.UserService;


/*
 * TWO TYPES OF ROLES :
 * 
 * 	USER_ADMIN
 *  USER_CUSTOMER
 */
@RequestMapping("/user")
@RestController
public class UserAPI
{
	public static final Logger log = LoggerFactory.getLogger(UserAPI.class);
	
	
	@Autowired
	UserService userService;
	
	/*
	 * A user can detail can be searched by :
	 * USER_CUSTOMER: can only retrieve his infos
	 * USER_ADMIN: can get infos about whatever user
	 */
	@PreAuthorize("hasAuthority('USER_ADMIN') or hasAuthority('USER_CUSTOMER')")
	@GetMapping("/{userNumber}")
	public ResponseEntity<UserDTO> getUserInfo(
				@PathVariable("userNumber") String userNumber ) throws Exception
	{
		log.info("Invoked getUserInfo API: "+userNumber);
		UserDTO userInfo = userService.getUserDetails(userNumber );
		log.info("Order details success");
		return ResponseEntity.ok(userInfo);
	}
	 
	
	
	
	
}
