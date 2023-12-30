package sito.davide.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import sito.davide.dto.UserDTO;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth", description = "Auth management APIs")
public class AuthController
{

	public static Logger log = LoggerFactory.getLogger(AuthController.class);
	
	
	@PostMapping("/login")
	public ResponseEntity<UserDTO> login(
				@RequestBody UserDTO user) throws Exception
	{
		return null;
	}
}
