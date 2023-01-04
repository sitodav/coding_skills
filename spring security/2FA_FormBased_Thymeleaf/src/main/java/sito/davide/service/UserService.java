package sito.davide.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;

import sito.davide.dto.UserDTO;

public interface UserService extends UserDetailsService
{
	public UserDTO createUser(UserDTO user,BindingResult bRes) throws Exception;
	public UserDTO getUserByUsername(String username) throws Exception;
	public UserDTO updateUserNotifyMethod(UserDTO userDTO) throws Exception;
}
