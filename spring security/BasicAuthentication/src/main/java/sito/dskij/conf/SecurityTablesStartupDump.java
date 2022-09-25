package sito.dskij.conf;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import sito.dskij.dao.TbRoleRepo;
import sito.dskij.dao.TbUserRepo;
import sito.dskij.entity.TbRole;
import sito.dskij.entity.TbUser;

/*
 * This bean class is used to initialize the db with a set of basic users/roles
 * if not already present
 */
@Configuration
public class SecurityTablesStartupDump implements ApplicationListener<ContextRefreshedEvent>{
	
	private static final Logger log = LoggerFactory.getLogger(SecurityTablesStartupDump.class);

	@Autowired
	TbRoleRepo rolesRepo;
	
	@Autowired
	TbUserRepo userRepo;
	
	@Autowired
	BCryptPasswordEncoder pwEncoder;
	
	
	@Transactional
	public TbRole createRoleIfNotExist(String roleName)
	{
		TbRole role = rolesRepo.findByName(roleName).orElse(null);
		if(null == role)
		{
			role = new TbRole();
			role.setName(roleName);
			role = rolesRepo.save(role);
			log.info(">>>Saved role: "+role);
		}
		return role;
	}
	
	@Transactional
	public TbUser createUserIfNotExists(String username,String password, List<TbRole> roles)
	{
		TbUser userDB = userRepo.findByUsername(username).orElse(null);
		if(userDB == null)
		{
			userDB = new TbUser();
			userDB.setPassword(pwEncoder.encode(password));
			userDB.setUsername(username);
			Set<TbRole> rolesDB = new HashSet<TbRole>(roles);
			userDB.setRoles(rolesDB);
			userDB = userRepo.save(userDB);
			log.info(">>>Saved user: "+userDB);
		}
		return userDB;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		 
		
		TbRole role = createRoleIfNotExist("STUDENT_READ");
		TbRole role2 = createRoleIfNotExist("STUDENT_WRITE");
		TbUser admin = createUserIfNotExists("admin", "admin", Arrays.asList(role,role2));
		
		TbUser davide = createUserIfNotExists("davide","davide", Arrays.asList(role));
		
		
	}

}
