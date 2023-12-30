package sito.davide.service;

import java.util.List;

import sito.davide.dto.UserDTO;

public interface UserService
{
	public abstract List<UserDTO> getAllUsers() throws Exception;
	public abstract UserDTO getUserById(Long id) throws Exception;
	public abstract UserDTO login(UserDTO user) throws Exception;
}
