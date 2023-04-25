package sito.davide.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO implements Serializable
{

	private static final long serialVersionUID = -632060089970118966L;
	

	@JsonIgnore
	private Long id; 
	
	
	private String orderNumber;
	private String deliveryAddress;
	private Integer numberOfPilotes;
	private Double totalCost;
	private Date creationDate;
	private String orderStatus;
	private UserDTO user;
	
	
	@Override
	public String toString()
	{
		return "OrderDTO [id=" + id + ", orderNumber=" + orderNumber + ", deliveryAddress=" + deliveryAddress + ", numberOfPilotes=" + numberOfPilotes
					+ ", totalCost=" + totalCost + ", creationDate=" + creationDate + ", orderStatus=" + orderStatus + "], USER NUM : "+(user != null ? user.getUserNumber() : "null");
	}
	
	
	
	


}
