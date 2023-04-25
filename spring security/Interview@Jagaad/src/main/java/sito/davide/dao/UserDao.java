package sito.davide.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sito.davide.entity.TbUser;

public interface UserDao extends JpaRepository<TbUser, Long>
{
	Optional<TbUser> findByUserNumber(String userNumber);
	Optional<TbUser> findByUsername(String username);
	
	
	List<TbUser> findByUsernameContainingIgnoreCase(String username); 
}
