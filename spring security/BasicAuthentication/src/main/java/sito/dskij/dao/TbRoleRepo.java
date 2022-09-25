package sito.dskij.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sito.dskij.entity.TbRole;

public interface TbRoleRepo extends JpaRepository<TbRole,Long>{
	public Optional<TbRole> findByName(String name);

}