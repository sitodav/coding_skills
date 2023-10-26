package sito.davide.service.impl;

import java.nio.CharBuffer;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import sito.davide.config.jwt.JWTHelper;
import sito.davide.dao.TbUserRepository;
import sito.davide.dto.UserDTO;
import sito.davide.entity.TbUser;
import sito.davide.mappers.TbUserToUserDTOMapper;
import sito.davide.misc.BusinessException;
import sito.davide.service.UserService;


@Service
public class UserServiceImpl implements UserService
{
	
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	//simple encoder for user pw on DB (it has nothing to do with jwt encrypted signature)
	@Autowired
	PasswordEncoder dbPasswordEncoder;
	
	@Autowired
	JWTHelper jwtHelper;
	
	@Autowired
	private TbUserRepository userRepo;
	
	@Autowired
	TbUserToUserDTOMapper userMapper;
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public UserDTO registerUser(UserDTO user) throws Exception
	{
		
		try
		{
			log.info("called register user with "+user.toString());
			
			if(!StringUtils.hasLength(user.getUsername()) || !StringUtils.hasLength(user.getPassword()) || !StringUtils.hasLength(user.getEmail()))
			{
				throw new BusinessException("Invalid parameters", HttpStatus.BAD_REQUEST);
			}
			
			Optional<TbUser> alreadyExisting = userRepo.findByUsername(user.getUsername());
			if(alreadyExisting.isPresent())
			{
				throw new BusinessException("Username already present", HttpStatus.BAD_REQUEST);
			}
			
			alreadyExisting = userRepo.findByEmailIgnoreCase(user.getEmail());
			if(alreadyExisting.isPresent())
			{
				throw new BusinessException("Email already present", HttpStatus.BAD_REQUEST);
			}
			
			TbUser userDB = userMapper.dtoToTb(user); //password must be encrypter
			userDB.setPassword(dbPasswordEncoder.encode(CharBuffer.wrap(user.getPassword())));
			userDB = userRepo.save(userDB);
			 
			UserDTO userDTO =  userMapper.tbToDto(userDB);
			//creating the jwt token so after registration the user can access protected resources
			userDTO.setToken(jwtHelper.createJWTToken(userDTO));
			return userDTO;
			
			
		}
		catch(Exception ex)
		{
			log.error("Error", ex);
			throw ex;
		}
	}
	
	

	@Transactional(rollbackFor=Exception.class)
	@Override
	public UserDTO login(UserDTO user) throws Exception
	{
		
		try
		{
			log.info("called login user with "+user.toString());
			
			if(!StringUtils.hasLength(user.getUsername()) || !StringUtils.hasLength(user.getPassword()) )
			{
				throw new BusinessException("Invalid parameters", HttpStatus.BAD_REQUEST);
			}
			
			Optional<TbUser> alreadyExisting = userRepo.findByUsername(user.getUsername());
			if(!alreadyExisting.isPresent())
			{
				throw new BusinessException("Username not found", HttpStatus.UNAUTHORIZED);
			}
			
			TbUser userDB = alreadyExisting.get();
			if(dbPasswordEncoder.matches(CharBuffer.wrap(user.getPassword()),userDB.getPassword()))
			{
				UserDTO userDTO =  userMapper.tbToDto(userDB);
				//creating the jwt token for the login
				userDTO.setToken(jwtHelper.createJWTToken(userDTO));
				return userDTO;
			}
			else
			{
				throw new BusinessException("Invalid credentials at login", HttpStatus.UNAUTHORIZED);
			}
			 
		 
			
			
		}
		catch(Exception ex)
		{
			log.error("Error", ex);
			throw ex;
		}
	}

}
