package sito.dskij.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/*
 * Entity to map on db the ROLE
 */

@Entity
@Table(name="TB_ROLES")
public class TbRole {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ROLE_ID")
	private Long userId;
	
	@Column(name="ROLE_NAME")
	private String name;

	
	 // bi-directional many-to-many association to User
    @ManyToMany(mappedBy = "roles")
    private Set<TbUser> users;
    
    
	public Long getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setName(String roleName) {
		this.name = roleName;
	}

	public Set<TbUser> getUsers() {
		return users;
	}

	public void setUsers(Set<TbUser> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "TbRole [userId=" + userId + ", name=" + name + "]";
	}
	
	
	
}
