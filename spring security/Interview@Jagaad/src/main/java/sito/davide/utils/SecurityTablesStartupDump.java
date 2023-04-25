package sito.davide.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import sito.davide.dao.RoleDao;
import sito.davide.dao.UserDao;
import sito.davide.entity.TbRole;
import sito.davide.entity.TbUser;

/*
 * This bean class is used to initialize the db with a set of basic users/roles
 * if not already present
 */
@Configuration
public class SecurityTablesStartupDump implements ApplicationListener<ContextRefreshedEvent>{
	
	private static final Logger log = LoggerFactory.getLogger(SecurityTablesStartupDump.class);

	@Autowired
	RoleDao roleDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	BCryptPasswordEncoder pwEncoder;
	
	
	@Transactional
	public TbRole createRoleIfNotExist(String roleName)
	{
		TbRole role = roleDao.findByNameIgnoreCase(roleName).orElse(null);
		if(null == role)
		{
			role = new TbRole();
			role.setName(roleName);
			role = roleDao.save(role);
			log.info(">>>Saved role: "+role);
		}
		return role;
	}
	
	@Transactional
	public TbUser createUserIfNotExists(
				String firstName, String secondName,  String telephone, String userName, String userNumber,String password, List<TbRole> roles)
	{
		TbUser userDB = userDao.findByUsername(userName).orElse(null);
		if(userDB == null)
		{
			userDB = new TbUser();
			userDB.setPassword(pwEncoder.encode(password));
			userDB.setUsername(userName);
			userDB.setFirstName(firstName);
			userDB.setSecondName(secondName);
			userDB.setTelephone(telephone);
			userDB.setUserNumber(userNumber);
			Set<TbRole> rolesDB = new HashSet<TbRole>(roles);
			userDB.setRoles(rolesDB);
			userDB = userDao.save(userDB);
			log.info(">>>Saved user: "+userDB);
		}
		return userDB;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		 
		
		TbRole adminRole = createRoleIfNotExist(EUserRoles.USER_ADMIN.name());
		TbRole customerRole = createRoleIfNotExist(EUserRoles.USER_CUSTOMER.name());
		createUserIfNotExists("Giorgio","Molinari","+393423334232","admin",UUID.randomUUID().toString(), "admin", Arrays.asList(adminRole));
		createUserIfNotExists("Davide","Sito","+3934237232132","d.sito", UUID.randomUUID().toString(),"d.sito", Arrays.asList(customerRole));
		
		
	}

}
