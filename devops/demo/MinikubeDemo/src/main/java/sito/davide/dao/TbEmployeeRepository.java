package sito.davide.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sito.davide.entity.TbEmployee;

public interface TbEmployeeRepository extends JpaRepository<TbEmployee,Long>
{
	public Optional<TbEmployee> findByLogicalCodeIgnoreCase(String logicalCode);
}
