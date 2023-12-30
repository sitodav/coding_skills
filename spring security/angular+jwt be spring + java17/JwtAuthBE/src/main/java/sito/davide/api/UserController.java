package sito.davide.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import sito.davide.dto.UserDTO;
import sito.davide.service.UserService;

@RestController
@RequestMapping("/users")
@Tag(name = "User", description = "Auth management APIs")
public class UserController
{

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@GetMapping("")
	public ResponseEntity<List<UserDTO>> getAllUsers() throws Exception
	{
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getAllUsers(@PathVariable(name="userId") Long userID) throws Exception
	{
		return ResponseEntity.ok(userService.getUserById(userID));
	}
	
 
}
