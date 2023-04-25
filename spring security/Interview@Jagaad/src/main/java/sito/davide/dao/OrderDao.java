package sito.davide.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sito.davide.entity.TbOrder;

public interface OrderDao extends JpaRepository<TbOrder, Long>
{
	Optional<TbOrder> findByOrderNumber(String orderNumber);
	
	@Query("select o from TbOrder o where o.orderStatus in :status")
	List<TbOrder> findOrdersInStatus(@Param("status") List<String> status);
}
