package sito.davide.service;

import sito.davide.dto.UserDTO;

public interface UserService
{
	public UserDTO registerUser(UserDTO user) throws Exception;
	public UserDTO login(UserDTO user) throws Exception;
}
