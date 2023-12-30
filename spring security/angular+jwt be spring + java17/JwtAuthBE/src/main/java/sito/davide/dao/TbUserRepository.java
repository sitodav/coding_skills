package sito.davide.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import sito.davide.entity.TbUser;

public interface TbUserRepository extends JpaRepository<TbUser, Long>
{
	
}
