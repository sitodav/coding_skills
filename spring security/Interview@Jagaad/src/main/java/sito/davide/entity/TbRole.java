package sito.davide.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
 

/*
 * Entity to map on db the ROLE
 */

@Entity
@Table(name="TB_ROLES")
@Getter
@Setter
public class TbRole {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ROLE_ID")
	private Long id;
	
	@Column( nullable = false, unique=true)
	private String name;

	
    @ManyToMany(mappedBy = "roles")
    private Set<TbUser> users;
    
    
	@Override
	public String toString() {
		return "TbRole [id=" + id + ", name=" + name + "]";
	}
	
	
	
}
