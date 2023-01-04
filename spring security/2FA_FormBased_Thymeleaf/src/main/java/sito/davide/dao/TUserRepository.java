package sito.davide.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sito.davide.entity.TUser;

public interface TUserRepository extends JpaRepository<TUser,Long>
{
	 Optional<TUser> findByUsernameIgnoreCase(String username) ;
	 Optional<TUser> findByCfIgnoreCase(String cf);
	 Optional<TUser> findByEmailIgnoreCase(String cf);
	 Optional<TUser> findByTelephone(String cf);
}
