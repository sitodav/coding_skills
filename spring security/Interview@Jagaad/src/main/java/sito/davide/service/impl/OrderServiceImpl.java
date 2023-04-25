package sito.davide.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.util.StringUtils;

import sito.davide.config.SecurityConf;
import sito.davide.dao.BusinessParameterDao;
import sito.davide.dao.OrderDao;
import sito.davide.dao.UserDao;
import sito.davide.dto.OrderDTO;
import sito.davide.dto.SearchDTO;
import sito.davide.dto.UserDTO;
import sito.davide.entity.TbBusinessParameter;
import sito.davide.entity.TbOrder;
import sito.davide.entity.TbUser;
import sito.davide.service.OrderService;
import sito.davide.utils.EBusinessParameter;
import sito.davide.utils.EOrderStatus;
import sito.davide.utils.EUserRoles;
import sito.davide.utils.GenericAtomicMapper;
import sito.davide.utils.OrderUtils;
import sito.davide.utils.exception.CustomException;

@Service
@Transactional(rollbackFor=Exception.class)
public class OrderServiceImpl implements OrderService
{
	private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	
	@Autowired 
	OrderDao orderDao;
	@Autowired 
	UserDao userDao;
	@Autowired 
	BusinessParameterDao parameterDao;
	@Autowired
	OrderUtils orderUtils;
	
	@Override
	public OrderDTO findByOrderNumber(String orderNumber) throws Exception
	{
		try
		{

			Optional<TbOrder> orderEntL = orderDao.findByOrderNumber(orderNumber);
			if (!orderEntL.isPresent())
			{
				throw new CustomException("Order number " + orderNumber + " not found");
			}

			/*
			 * If the logged user is not an admin, and the orderNumber does not belong to
			 * the user orders, it must be blocked
			 */
			String loggedUsername = SecurityConf.getLoggedUsername();
			TbOrder ord = orderEntL.get();
			TbUser user = ord.getUser();
			
			List<String> loggedUserAuthorities = SecurityConf.getLoggedUserAuthorities();

			if (!loggedUserAuthorities.contains(EUserRoles.USER_ADMIN.name())) // not admin
			{
				/*sanity check, should not happen */
			 
				if(!user.getUsername().equals(loggedUsername))
					throw new CustomException("Operation not allowed for the logged user");

			}

			

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
		 
		
		try
		{
			/*just a quick example */
			List<OrderDTO> results = new ArrayList<>();
			
			List<TbUser> possibleUsers = userDao.findByUsernameContainingIgnoreCase(searchRequest.getUsername());
			for(TbUser user : possibleUsers)
			{
				List<TbOrder> orders = user.getOrders();
				if(null != orders)
				{
					for(TbOrder orderEntity : orders)
					{
						OrderDTO ordDTO = GenericAtomicMapper.copyObject(orderEntity, OrderDTO.class);
						UserDTO usrDTO = GenericAtomicMapper.copyObject(user, UserDTO.class);
						ordDTO.setUser(usrDTO);
						
						results.add(ordDTO);
					}
				}
			}
			
			return results;
		}
		catch(Exception ex)
		{
			log.error("Error custom search",ex);
			throw ex;
		}
		
	}

	@Override
	public OrderDTO createOrder(OrderDTO orderRequest) throws Exception
	{
		
 
		try
		{	
			String username = SecurityConf.getLoggedUsername();
			
			/*order validation */
			orderUtils.validateOrderRequest(orderRequest);
			
			/*field mapping from client */
			Optional<TbBusinessParameter> costParameterOpt = parameterDao.findByNameIgnoreCase(EBusinessParameter.PILOTES_COST.name());
			if(!costParameterOpt.isPresent())
				throw new CustomException("Invalid parameter configuration");
			
			Double costDouble = Double.parseDouble(costParameterOpt.get().getStrVal());
			
			TbOrder orderEntity = GenericAtomicMapper.copyObject(orderRequest, TbOrder.class);
			/*new fields generation */
			orderEntity.setOrderNumber(UUID.randomUUID().toString());
			orderEntity.setCreationDate(new Date());
			orderEntity.setTotalCost(orderRequest.getNumberOfPilotes() * costDouble);
			orderEntity.setOrderStatus(EOrderStatus.CREATED.name());
			/*user extraction */
			Optional<TbUser> userOpt = userDao.findByUsername(username);
			if(!userOpt.isPresent())
			{
				throw new CustomException("User not found "+username);
			}
			TbUser user = userOpt.get();
			orderEntity.setUser(user);
			orderEntity = orderDao.save(orderEntity);
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
	public OrderDTO updateOrder(OrderDTO updateOrderRequest) throws Exception
	{
		 
		try
		{	
			/*Order validation */
			/*First we check that if the user is not an admin, the order is yet to be prepared */
			if(!StringUtils.hasLength(updateOrderRequest.getOrderNumber()))
			{
				throw new CustomException("Invalid specified order number");
			}
			
			TbOrder orderToUpdate = orderDao.findByOrderNumber(updateOrderRequest.getOrderNumber())
						.orElseThrow( () -> new CustomException("Invalid specified order number"));
			
			String loggedUsername = SecurityConf.getLoggedUsername();
			
			List<String> loggedUserAuthorities = SecurityConf.getLoggedUserAuthorities();
			 
			 if(!loggedUserAuthorities.contains(EUserRoles.USER_ADMIN.name())) //not admin
			 {
				  if(!orderToUpdate.getOrderStatus().equals(EOrderStatus.CREATED.name()))
					  throw new CustomException("The order cannot be updated, to much time is passed and it's in preparation");
				 
			 }
			 
			/*order validation */
			orderUtils.validateOrderRequest(updateOrderRequest);
			
			/*Order update */
			
			/*field mapping from client */
			Optional<TbBusinessParameter> costParameterOpt = parameterDao.findByNameIgnoreCase(EBusinessParameter.PILOTES_COST.name());
			if(!costParameterOpt.isPresent())
				throw new CustomException("Invalid parameter configuration");
			
			Double costDouble = Double.parseDouble(costParameterOpt.get().getStrVal());
			orderToUpdate.setTotalCost(updateOrderRequest.getNumberOfPilotes() * costDouble);
			orderToUpdate.setNumberOfPilotes(updateOrderRequest.getNumberOfPilotes());
			orderToUpdate.setDeliveryAddress(updateOrderRequest.getDeliveryAddress());
			
			orderDao.save(orderToUpdate);
			
			/*mapping back for the client response with the new fields */
			
			Optional<TbUser> userOpt = userDao.findByUsername(loggedUsername);
			if(!userOpt.isPresent()) //just a sanity check, it should not happen
			{
				throw new CustomException("User not found "+loggedUsername);
			}
			TbUser user = userOpt.get();
			
			updateOrderRequest = GenericAtomicMapper.copyObject(orderToUpdate, OrderDTO.class);
			UserDTO userDTO = GenericAtomicMapper.copyObject(user, UserDTO.class);
			updateOrderRequest.setUser(userDTO);
			
			return updateOrderRequest;
			
			
			
		}
		catch(Exception ex)
		{
			log.error("Error: ",ex);
			throw ex;
		}
		
		 
	}

	@Override
	public void deleteOrder(String orderNumber) throws Exception
	{
		// TODO Auto-generated method stub
		
	}

	
}
