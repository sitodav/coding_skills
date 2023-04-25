package sito.davide.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sito.davide.config.SecurityConf;
import sito.davide.dao.OrderDao;
import sito.davide.dao.UserDao;
import sito.davide.dto.OrderDTO;
import sito.davide.dto.SearchDTO;
import sito.davide.dto.UserDTO;
import sito.davide.entity.TbOrder;
import sito.davide.entity.TbUser;
import sito.davide.service.OrderService;
import sito.davide.utils.EOrderStatus;
import sito.davide.utils.GenericAtomicMapper;
import sito.davide.utils.exception.CustomException;

@Service
@Transactional(rollbackFor=Exception.class)
public class OrderServiceImpl implements OrderService
{
	private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
	private static final Double PILOTES_COST = 1.33; /*this should be on a DB, but for sake of simplicity , being the only property for pilotes in the 
	excercise, we don't create a separate table for it */
	
	@Autowired 
	OrderDao orderDao;
	@Autowired 
	UserDao userDao;
	
	
	@Override
	public OrderDTO findByOrderNumber(String orderNumber) throws Exception
	{
		try
		{	
			Optional<TbOrder> orderEntL = orderDao.findByOrderNumber(orderNumber);
			if(!orderEntL.isPresent())
			{
				throw new CustomException("Order number "+orderNumber+" not found");
			}
			TbOrder ord = orderEntL.get();
			TbUser user = ord.getUser();
			
			OrderDTO ordDTO = GenericAtomicMapper.copyObject(ord, OrderDTO.class);
			UserDTO usrDTO = GenericAtomicMapper.copyObject(user, UserDTO.class);
			ordDTO.setUser(usrDTO);
			return ordDTO;
		}
		catch(Exception ex)
		{
			log.error("Error: ",ex);
			throw ex;
		}
		
	}

	@Override
	public List<OrderDTO> searchOrders(SearchDTO searchRequest) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDTO createOrder(OrderDTO orderRequest) throws Exception
	{
		
 
		try
		{	
			String username = SecurityConf.getLoggedUsername();
			
			/*field mapping from client */
			TbOrder orderEntity = GenericAtomicMapper.copyObject(orderRequest, TbOrder.class);
			/*new fields generation */
			orderEntity.setOrderNumber(UUID.randomUUID().toString());
			orderEntity.setCreationDate(new Date());
			orderEntity.setTotalCost(orderRequest.getNumberOfPilotes() * PILOTES_COST);
			orderEntity.setOrderStatus(EOrderStatus.CREATED.name());
			/*user extraction */
			Optional<TbUser> userOpt = userDao.findByUsername(username);
			if(!userOpt.isPresent())
			{
				throw new CustomException("User not found "+username);
			}
			TbUser user = userOpt.get();
			List<TbOrder> userOrders = Optional.ofNullable(user.getOrders()).orElse(new ArrayList<>());
			userOrders.add(orderEntity);
			user.setOrders(userOrders);
			userDao.save(user);
			/*mapping back for the client response with the new fields */
			orderRequest = GenericAtomicMapper.copyObject(orderEntity, OrderDTO.class);
			UserDTO userDTO = GenericAtomicMapper.copyObject(user, UserDTO.class);
			orderRequest.setUser(userDTO);
			
			return orderRequest;
			
			
			
		}
		catch(Exception ex)
		{
			log.error("Error: ",ex);
			throw ex;
		}
		
	
		 
	}

	@Override
	public OrderDTO updateOrder(OrderDTO updateOrder) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteOrder(String orderNumber) throws Exception
	{
		// TODO Auto-generated method stub
		
	}

	
}
