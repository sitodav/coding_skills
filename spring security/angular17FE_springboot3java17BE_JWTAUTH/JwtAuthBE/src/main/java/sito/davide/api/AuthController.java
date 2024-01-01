package sito.davide.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import sito.davide.dto.UserDTO;
import sito.davide.service.UserService;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth", description = "Auth management APIs")
public class AuthController
{

	public static Logger log = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<UserDTO> login(
				@RequestBody UserDTO user) throws Exception
	{
		return ResponseEntity.ok(userService.login(user));
	}
	
	@PostMapping("/logout")
	public ResponseEntity<UserDTO> logout(
				@RequestBody UserDTO user) throws Exception
	{
		SecurityContextHolder.clearContext();
		return ResponseEntity.ok(user);
	}
}
