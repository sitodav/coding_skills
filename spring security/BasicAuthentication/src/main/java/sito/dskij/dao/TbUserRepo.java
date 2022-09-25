package sito.dskij.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sito.dskij.entity.TbUser;

public interface TbUserRepo extends JpaRepository<TbUser,Long>{
	public Optional<TbUser> findByUsername(String username);

}
