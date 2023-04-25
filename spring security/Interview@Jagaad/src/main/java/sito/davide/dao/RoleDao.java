package sito.davide.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sito.davide.entity.TbRole;

public interface RoleDao extends JpaRepository<TbRole, Long>
{
	Optional<TbRole> findByNameIgnoreCase(String name);
}
