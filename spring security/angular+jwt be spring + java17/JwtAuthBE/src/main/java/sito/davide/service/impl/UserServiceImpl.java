package sito.davide.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sito.davide.dao.TbUserRepository;
import sito.davide.dto.UserDTO;
import sito.davide.mappers.TbUserToUserDTOMapper;
import sito.davide.service.UserService;

@Service
public class UserServiceImpl implements UserService
{
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private TbUserRepository userRepo;
	
	@Autowired
	public TbUserToUserDTOMapper userMapper;
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public List<UserDTO> getAllUsers() throws Exception
	{
		try
		{
			return userRepo.findAll().stream().map(userDb -> userMapper.tbToDTO(userDb)).collect(Collectors.toList());
		}
		catch(Exception ex)
		{
			log.error("Error",ex);
			throw ex;
		}
	}

	@Transactional(rollbackFor=Exception.class)
	@Override
	public UserDTO getUserById(Long id) throws Exception
	{
		try
		{
			return userRepo.findById(id).map(userDB -> userMapper.tbToDTO(userDB)).orElseThrow( () -> new Exception("User with id not found"));
		}
		catch(Exception ex)
		{
			log.error("Error",ex);
			throw ex;
		}
	}

}
