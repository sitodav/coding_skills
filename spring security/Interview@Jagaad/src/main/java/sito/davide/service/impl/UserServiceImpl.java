package sito.davide.service.impl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sito.davide.config.SecurityConf;
import sito.davide.dao.UserDao;
import sito.davide.dto.OrderDTO;
import sito.davide.dto.UserDTO;
import sito.davide.entity.TbRole;
import sito.davide.entity.TbUser;
import sito.davide.service.UserService;
import sito.davide.utils.EUserRoles;
import sito.davide.utils.GenericAtomicMapper;
import sito.davide.utils.exception.CustomException;

@Service
@Transactional(rollbackFor=Exception.class)
public class UserServiceImpl implements UserService
{
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserDao userDao;
	
	/*
	 * we override this method, in which we get the user in the DB using the user number
	 * and wrap it in the spring UserEntity .
	 * This method is intented to be used by spring security layer to validate our basic authentication 
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try
		{
			TbUser userDB = userDao.findByUsername(username).orElse(null);
			if(userDB == null)
				throw new UsernameNotFoundException("User not found");
			
			Set<TbRole> userRolesDB = userDB.getRoles();
			List<GrantedAuthority> springAuthorities=new ArrayList<>();
			if(null != userRolesDB)
			{
				for(TbRole roleDB : userRolesDB)
				{
					/*
					 * in spring convention an authority/permission can be a role, a permission or whatever string
					 * identifies something a user can do
					 */
					 springAuthorities.add( new SimpleGrantedAuthority(roleDB.getName()) ); 
				}
			}
			/*We want to get the mapping, in the spring convention of the authorities/permission */
			return 
				new org.springframework.security.core.userdetails.User(userDB.getUsername(),userDB.getPassword(), springAuthorities);
			
		}
		catch(Exception ex)
		{
			throw ex;
		}
		
	}
	
	/*This is intended to be used for plain APIs (not by the authentication like the previous one ) */
	@Override
	public UserDTO getUserDetails(String requestedUserNumber ) throws Exception
	{
		 try
		 {
			 /*Check that if the logged user is not role USER_ADMIN, he can only search for his own infos */
			 
			 String loggedUsername = SecurityConf.getLoggedUsername();
			 List<String> loggedUserAuthorities = SecurityConf.getLoggedUserAuthorities();
			 
			 if(!loggedUserAuthorities.contains(EUserRoles.USER_ADMIN.name())) //not admin
			 {
				 /*we get the user number for the logged user */
				 Optional<TbUser> loggedUserEntityOpt = userDao.findByUsername(loggedUsername);
				 if(!loggedUserEntityOpt.isPresent()) //just a sanity check : this should never happen
				 {  
					 throw new CustomException("Invalid logged user");
				 }
				 if(!loggedUserEntityOpt.get().getUserNumber().equals(requestedUserNumber)) //the logged user is different from the searched one
				 {
					 throw new CustomException("Operation not allowed for the logged user");
				 }
			 }
			 //otherwise
			 
			 TbUser userDB = userDao.findByUserNumber(requestedUserNumber).orElse(null);
				if(userDB == null)
					throw new UsernameNotFoundException("User not found");
				
			 UserDTO userDTO = GenericAtomicMapper.copyObject(userDB, UserDTO.class);
			 List<OrderDTO> orders = Optional.ofNullable(userDB.getOrders()).orElse(new ArrayList<>())
						 				.stream()
						 				.map(orderDB -> {
						 					OrderDTO orderDTO = null;
						 					try
						 					{
						 						orderDTO = GenericAtomicMapper.copyObject(orderDB, OrderDTO.class);
						 					}
						 					catch(Exception ex)
						 					{
						 						log.info("Error mapping order entity to order dto for "+orderDB,ex);
						 					}
						 					
						 					return orderDTO;
						 				})
						 				.collect(Collectors.toList());
			 userDTO.setOrders(orders);
			 
			 return userDTO;
			 
		 }
		 catch(Exception ex)
		 {
			 log.error("Error",ex);
			 throw ex;
		 }
	}
 
	
 

	
}
