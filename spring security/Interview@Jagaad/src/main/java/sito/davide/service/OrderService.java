package sito.davide.service;

import java.security.Principal;
import java.util.List;

import sito.davide.dto.OrderDTO;
import sito.davide.dto.SearchDTO;

public interface OrderService
{
	public OrderDTO findByOrderNumber(String orderNumber) throws Exception;
	public List<OrderDTO> searchOrders(SearchDTO searchRequest) throws Exception;
	public OrderDTO createOrder(OrderDTO orderRequest) throws Exception;
	public OrderDTO updateOrder(OrderDTO updateOrder) throws Exception;
	public void deleteOrder(String orderNumber) throws Exception;
}
