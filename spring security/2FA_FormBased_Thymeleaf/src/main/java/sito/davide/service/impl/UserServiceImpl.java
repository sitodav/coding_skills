package sito.davide.service.impl;

import java.util.ArrayList;
import java.util.Optional;

import org.apache.commons.codec.binary.Base32;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import sito.davide.dao.TUserRepository;
import sito.davide.dto.UserDTO;
import sito.davide.entity.TUser;
import sito.davide.service.UserService;
import sito.davide.service.external.MailClient;
import sito.davide.utils.BusinessException;
import sito.davide.utils.OTPManager;

@Service 
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService
{
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	TUserRepository userRepo;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	MailClient mailComponent;
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	OTPManager otpManager;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		try
		{
			TUser userDB = userRepo.findByUsernameIgnoreCase(username).orElse(null);
			if (userDB == null)
				throw new UsernameNotFoundException("User not found");

			/* Non c'e' bisogno dei ruoli */
			return new User(userDB.getUsername(), userDB.getPassword(), new ArrayList<>());

		}
		catch (Exception ex)
		{
			log.error("Error", ex);
			throw ex;
		}

	}

	@Override @SuppressWarnings("deprecation")
	public UserDTO createUser(UserDTO user, BindingResult bRes) throws Exception
	{
		try
		{
			 

			Optional<TUser> u = userRepo.findByUsernameIgnoreCase(user.getUsername());
			if (u.isPresent())
			{
				bRes.rejectValue("username", null, "Username già in uso");
			}
			
			u = userRepo.findByCfIgnoreCase(user.getCf());
			if (u.isPresent())
			{
				bRes.rejectValue("cf", null, "Codice Fiscale già in uso");
			}
			
			if (!user.getPasswordConfirmation().equals(user.getPassword()))
			{
				bRes.rejectValue("passwordConfirmation", null, "Le password non corrispondono");
			}
			
			u = userRepo.findByEmailIgnoreCase(user.getEmail());
			if(u.isPresent())
			{
				bRes.rejectValue("email", null, "Email già in uso");
			}
			
			u = userRepo.findByTelephone(user.getTelephone());
			if(u.isPresent())
			{
				bRes.rejectValue("telephone",null, "Numero di telefono già in uso");
			}
			
			if (bRes.hasErrors())
			{
				return null;
			}

			TUser userDB = mapper.map(user,TUser.class);
			userDB.setPassword(passwordEncoder.encode(user.getPassword()));
			userDB.setOtpSecret(otpManager.generateSecretKey()); //il secret lo ha in ogni caso, che usi auth a 2 fattori o no
			userDB.setNotifyMail(true);
			userDB = userRepo.save(userDB);
			
			user.setOtpQR(otpManager.getQRCode(userDB.getOtpSecret()));
//			user.setOtpSecret(null);
			user.setIdUser(userDB.getIdUser());
			user.setPassword(null);
			return user;
		}
		catch (Exception ex)
		{
			log.error("Error", ex);
			throw ex;
		}

	}

	@Override
	public UserDTO getUserByUsername(String username) throws Exception
	{
		try
		{
			 

			Optional<TUser> u = userRepo.findByUsernameIgnoreCase(username);
			if(u.isPresent())
			{
				TUser userDB = u.get();
				UserDTO uDTO = mapper.map(userDB,UserDTO.class);
				uDTO.setPassword(null);
				
				return uDTO;
			}
			return null;
		}
		catch (Exception ex)
		{
			log.error("Error", ex);
			throw ex;
		}

	}

	@Override
	public UserDTO updateUserNotifyMethod(UserDTO userDTO) throws Exception
	{
		try
		{
			 

			Optional<TUser> u = userRepo.findByUsernameIgnoreCase(userDTO.getUsername());
			if (!u.isPresent())
			{
				throw new BusinessException("Utente non trovato");
			}
			TUser userDB = u.get();
			userDB.setNotifyMail(userDTO.getNotifyMail());
			userDB.setNotifySlack(userDTO.getNotifySlack());
			userDB.setNotifyWhatsup(userDTO.getNotifyWhatsup());
			userDB.setNotifySms(userDTO.getNotifySms());
			userDB=userRepo.save(userDB);
			return userDTO;
		}
		catch (Exception ex)
		{
			log.error("Error", ex);
			throw ex;
		}

	}
	
	

}
