package sito.davide.service;

import java.security.Principal;

import org.springframework.security.core.userdetails.UserDetailsService;

import sito.davide.dto.UserDTO;

public interface UserService extends UserDetailsService
{
	public UserDTO getUserDetails(String userNumber ) throws Exception;
	/*other cruds omitted for sake of semplicity ... */
}
