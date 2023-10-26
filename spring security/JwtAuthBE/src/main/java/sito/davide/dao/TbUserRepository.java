package sito.davide.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sito.davide.entity.TbUser;

public interface TbUserRepository extends JpaRepository<TbUser,Long>
{
	Optional<TbUser> findByUsername(String username);
	Optional<TbUser> findByEmailIgnoreCase(String email);
}
