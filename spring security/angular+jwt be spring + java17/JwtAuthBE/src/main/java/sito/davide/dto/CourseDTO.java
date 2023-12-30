package sito.davide.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDTO implements Serializable
{
	private static final long serialVersionUID = 3183680795584238869L;
	
	private Long id;
	private String name;
	
	
	public CourseDTO() {}
	public CourseDTO(Long id, String name)
	{
		super();
		this.id = id;
		this.name = name;
	}


	@Override
	public String toString()
	{
		return "CourseDTO [" + (id != null ? "id=" + id + ", " : "") + (name != null ? "name=" + name : "") + "]";
	}
	
	
}
