package sito.dskij.dto;

import java.io.Serializable;

public class StudentDTO implements Serializable{

	private static final long serialVersionUID = 8606355387071020742L;
	private String name;
	private String surname;
	private Long id;
	
	public StudentDTO() { }
	public StudentDTO(String name, String surname, Long id) {
		this.name = name;
		this.surname = surname;
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public String getSurname() {
		return surname;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Long getId() {
		return this.id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "StudentDTO [name=" + name + ", surname=" + surname + ", id="+id+"]";
	}
	
	
	
}
 