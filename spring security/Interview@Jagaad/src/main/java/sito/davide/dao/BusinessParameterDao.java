package sito.davide.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sito.davide.entity.TbBusinessParameter;

public interface BusinessParameterDao extends JpaRepository<TbBusinessParameter, Long>
{
	Optional<TbBusinessParameter> findByNameIgnoreCase(String name);
}
