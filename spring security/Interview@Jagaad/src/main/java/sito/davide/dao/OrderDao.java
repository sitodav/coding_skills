package sito.davide.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sito.davide.entity.TbOrder;

public interface OrderDao extends JpaRepository<TbOrder, Long>
{
	Optional<TbOrder> findByOrderNumber(String orderNumber);
}
