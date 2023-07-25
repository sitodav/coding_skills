package sito.davide.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="TB_EMPLOYEE")
@Getter
@Setter
public class TbEmployee implements Serializable
{
	private static final long serialVersionUID = -4875200171483605135L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="NAME", length = 600)
	private String name;
	
	@Column(name="SURNAME", length = 600)
	private String surname;
	
	@Column(name="LOGICAL_CODE", length = 600)
	private String logicalCode;
	
}
