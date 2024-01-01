package sito.davide.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sito.davide.conf.security.JWTHelper;
import sito.davide.dao.TbUserRepository;
import sito.davide.dto.UserDTO;
import sito.davide.entity.TbUser;
import sito.davide.exception.BusinessException;
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
	
	@Autowired
	public BCryptPasswordEncoder pwEncoder;
	
	@Autowired
	public JWTHelper jwtHelper;
	
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
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public UserDTO login(UserDTO inputUser) throws Exception
	{
		TbUser userOnDb = 
					userRepo.findByUsername(inputUser.getUsername()).orElseThrow(() -> new BusinessException("Invalid username ",HttpStatus.UNAUTHORIZED));
		
		if(StringUtils.isBlank(inputUser.getUsername()) || StringUtils.isBlank(inputUser.getPassword()))
		{
			throw new BusinessException("Invalid parameters for login ",HttpStatus.UNAUTHORIZED);
		}
		 
		if(pwEncoder.matches(inputUser.getPassword(),userOnDb.getPassword() ))
		{
			UserDTO dto = userMapper.tbToDTO(userOnDb);
			dto.setJwt(jwtHelper.createJwtToken(dto));
			return dto;
		}
		else
			throw new BusinessException("Invalid Password", HttpStatus.UNAUTHORIZED);
		
		
	}

}
