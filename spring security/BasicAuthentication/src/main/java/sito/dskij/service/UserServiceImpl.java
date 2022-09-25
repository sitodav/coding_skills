package sito.dskij.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sito.dskij.dao.TbUserRepo;
import sito.dskij.entity.TbRole;
import sito.dskij.entity.TbUser;

/*
 * class to link between users on db and the spring user convention
 * The class extends implements the userdetails service
 */
@Service
public class UserServiceImpl implements UserDetailsService{

	@Autowired
	private TbUserRepo userRepo;
	
	
	/*
	 * we override this method, in which we get the user in the DB using the username
	 * and wrap it in the spring UserEntity 
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try
		{
			TbUser userDB = userRepo.findByUsername(username).orElse(null);
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
			return new User(userDB.getUsername(),userDB.getPassword(), springAuthorities);
			
		}
		catch(Exception ex)
		{
			throw ex;
		}
		
	}

}
