package sito.davide.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Table(name="TB_ORDER")
@Entity
@Getter
@Setter
public class TbOrder
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ORDER_ID")
	private Long id;
	
	@Column(nullable=false, unique = true)
	private String orderNumber;
	
	@Column(  nullable=false)
	private String deliveryAddress;
	
	@Column( nullable=false)
	private Integer numberOfPilotes;
	
	@Column(nullable = false)
	private Double totalCost;
	
	@Column(nullable = false)
	private Date creationDate;
	
	@Column(nullable = false)
	private String orderStatus;
	
	
	@ManyToOne
	@JoinColumn(name = "FK_USERID", referencedColumnName = "USER_ID")
	TbUser user;
	
}
