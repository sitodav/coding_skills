package sito.davide.entity;

import java.util.Objects;

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
	
	@Column(name="FIRSTNAME",nullable=false)
	private String firstname;
	@Column(name="lastname",nullable=false)
	private String lastname;
	@Column(name="USERNAME", nullable=false, unique=true)
	private String username;
	@Column(name="PASSWORD")
	private String password;
	
	public TbUser() {}
	public TbUser(Long id, String firstname, String lastname, String username, String password)
	{
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(username);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TbUser other = (TbUser) obj;
		return Objects.equals(username, other.username);
	}
	@Override
	public String toString()
	{
		return "TbUser [" + (id != null ? "id=" + id + ", " : "") + (firstname != null ? "firstname=" + firstname + ", " : "")
					+ (lastname != null ? "lastname=" + lastname + ", " : "") + (username != null ? "username=" + username + ", " : "")
					+ (password != null ? "password=" + password : "") + "]";
	}
	
	
	
	
	
	
}
