package sito.davide.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sito.davide.dto.UserDTO;
import sito.davide.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController
{

	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO user) throws Exception
	{
		 return ResponseEntity.ok(userService.registerUser(user));
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserDTO> loginUser(@RequestBody UserDTO user) throws Exception
	{
		 return ResponseEntity.ok(userService.login(user));
	}
	
	
}
