package sito.davide.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="TB_USER")
@Getter
@Setter
public class TbUser
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="USERNAME",unique = true, nullable = false )
	private String username;
	
	@Column(name="PASSWORD",nullable = false )
	private String password;
	
	@Column(name="EMAIL",unique = true, nullable = false )
	private String email;

}
