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
@Table(name="TB_COURSE")
@Getter
@Setter
public class TbCourse
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="NAME",unique=true, nullable=false)
	private String name;

	public TbCourse() {}
	public TbCourse(Long id, String name)
	{
		super();
		this.id = id;
		this.name = name;
	}
	
	
	
	@Override
	public String toString()
	{
		return "TbCourse [" + (id != null ? "id=" + id + ", " : "") + (name != null ? "name=" + name : "") + "]";
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(name);
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
		TbCourse other = (TbCourse) obj;
		return Objects.equals(name, other.name);
	}
	
	

}
