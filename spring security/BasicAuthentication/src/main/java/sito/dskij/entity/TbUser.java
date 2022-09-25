package sito.dskij.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


/*
 * Entity to map on db the USER
 */
@Entity
@Table(name="TB_USER")
public class TbUser {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USER_ID")
	private Long userId;
	
	@Column(name="USER_USERNAME")
	private String username;
	
	@Column(name="USER_PASSWORD")
	private String password;
	
	// bi-directional many-to-many association to Role
    @ManyToMany
    @JoinTable(name = "TB_USER_ROLES", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
    private Set<TbRole> roles;

	public Long getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<TbRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<TbRole> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "TbUser [userId=" + userId + ", username=" + username + ", password=" + password + ", roles=" + roles
				+ "]";
	}
	
	
}
