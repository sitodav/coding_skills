package sito.davide.api;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sito.davide.dto.OrderDTO;
import sito.davide.dto.SearchDTO;
import sito.davide.service.OrderService;


/*
 * TWO TYPES OF ROLES :
 * 
 * 	USER_ADMIN
 *  USER_CUSTOMER
 */
@RequestMapping("/order")
@RestController
public class OrderAPI
{
	public static final Logger log = LoggerFactory.getLogger(OrderAPI.class);
	
	
	@Autowired
	OrderService orderService;
	
	/*
	 * A order can be searched from USER_CUSTOMER and USER_ADMIN:
	 * USER_CUSTOMER: it must be his order
	 * USER_ADMIN: whatever order
	 */
	@PreAuthorize("hasAuthority('USER_ADMIN') or hasAuthority('USER_CUSTOMER')")
	@GetMapping("/{orderNumber}")
	public ResponseEntity<OrderDTO> getOrderDetails(
				@PathVariable("orderNumber") String orderNumber ) throws Exception
	{
		log.info("Invoked getOrderDetails API: "+orderNumber);
		OrderDTO order = orderService.findByOrderNumber(orderNumber );
		log.info("Order details success");
		return ResponseEntity.ok(order);
	}
	
	/*A order can be created from USER_CUSTOMER and USER_ADMIN, with no restriction*/
	@PreAuthorize("hasAuthority('USER_ADMIN') or hasAuthority('USER_CUSTOMER')")
	@PostMapping("")
	public ResponseEntity<OrderDTO> createOrder(
				@RequestBody OrderDTO orderDTO  ) throws Exception
	{
		log.info("Invoked createOrder API: "+orderDTO);
		orderDTO = orderService.createOrder(orderDTO );
		log.info("Order creation success");
		return ResponseEntity.ok(orderDTO);
		
	}
	
	/*
	 * An order can be updated from an USER_CUSTOMER only in the valid time window, and only if it's his order
	 * An USER_ADMIN can always update an order
	 */
	@PreAuthorize("hasAuthority('USER_ADMIN') or hasAuthority('USER_CUSTOMER')")
	@PutMapping("")
	public ResponseEntity<OrderDTO> updateOrder(
				@RequestBody OrderDTO orderDTO ) throws Exception
	{
		log.info("Invoked updateOrder API: "+orderDTO);
		orderDTO = orderService.updateOrder(orderDTO );
		log.info("Order update success");
		return ResponseEntity.ok(orderDTO);
		
	}
	
	/*
	 * An order can be deleted from a USER_CUSTOMER only if it s in the valid time window and it's his order
	 * An USER_ADMIN can always delete an order
	 */
	@PreAuthorize("hasAuthority('USER_ADMIN') or hasAuthority('USER_CUSTOMER')")
	@DeleteMapping("/{orderNumber}")
	public ResponseEntity<String> deleteOrder(@PathVariable("orderNumber") String orderNumber ) throws Exception
	{
		log.info("Invoked deleteOrder API: "+orderNumber);
		orderService.deleteOrder(orderNumber );
		log.info("deleteOrder success");
		return ResponseEntity.ok("Success");
	}
	
	
	/*Only USER_ADMIN can use custom search */
	@PreAuthorize("hasAuthority('USER_ADMIN')")
	@PostMapping("/customSearch")
	public ResponseEntity<List<OrderDTO>> customOrderSearch(@RequestBody SearchDTO searchRequestDTO ) throws Exception
	{
		log.info("Invoked customOrderSearch API: "+searchRequestDTO);
		List<OrderDTO> results =orderService.searchOrders(searchRequestDTO );
		log.info("customOrderSearch success");
		return ResponseEntity.ok(results);
	}
	
	
	
	
}
