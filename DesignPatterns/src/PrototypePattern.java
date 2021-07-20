import java.util.HashMap;
import java.util.Map;

/*
 * 
 * Prototype pattern is used when we want a factory like method,
 * but every internal state of the created objects has to be consistent
 * and always the same (at the creation time)
 * Moreover the instances of the created objects have to be different
 */
public class PrototypePattern implements Cloneable {

	public static class Persona
	{
		private String name;
		private String surname;
		
		
		
		public Persona(String name, String surname) {
			this.name = name;
			this.surname = surname;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getSurname() {
			return surname;
		}
		public void setSurname(String surname) {
			this.surname = surname;
		}
		
		@Override
		protected Object clone() throws CloneNotSupportedException {
				// TODO Auto-generated method stub
				return super.clone();
		}

	}
	
	
	public static class Student extends Persona implements Cloneable
	{
		private String number;

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}

		public Student(String name, String surname, String number) {
			super(name, surname);
			this.number = number;
		}
		
		@Override
		protected Object clone() throws CloneNotSupportedException {
			Employee emp = new Employee(this.getName(),this.getSurname(),this.getNumber());
			return emp;
		}

		@Override
		public String toString() {
			return "Student [number=" + number + ", getNumber()=" + getNumber() + ", getName()=" + getName()
					+ ", getSurname()=" + getSurname() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
					+ ", toString()=" + super.toString() + "]";
		}
		
		
		
	}
	
	public static class Employee extends Persona implements Cloneable
	{
		private String employeeId;
		
		public String getEmployeeId() {
			return employeeId;
		}
		public void setEmployeeId(String employeeId) {
			this.employeeId = employeeId;
		}

		public Employee(String name, String surname, String employeeId) {
			super(name, surname);
			this.employeeId = employeeId;
		}
		
		@Override
		protected Object clone() throws CloneNotSupportedException {
			Employee emp = new Employee(this.getName(),this.getSurname(),this.getEmployeeId());
			return emp;
		}
		
		
		@Override
		public String toString() {
			return "Employee [employeeId=" + employeeId + ", getEmployeeId()=" + getEmployeeId() + ", getName()="
					+ getName() + ", getSurname()=" + getSurname() + ", getClass()=" + getClass() + ", hashCode()="
					+ hashCode() + ", toString()=" + super.toString() + "]";
		}
		
		
	}
	
	
	public static class PersonaBuilder
	{
		private Map<String,Persona> inBuiltsStuds = new HashMap<>(); //this can be whatever mechanism to keep in memory the original blueprints
		private Map<String,Persona> inBuiltsEmploys = new HashMap<>();   //for example in spring is the bean context
		
		
		public PersonaBuilder()
		{
			//just initializing the inbuilt memory for the blueprints
			Persona worker1 = new Employee("John","Mico","123IDasd");
			Persona worker2 = new Employee("Michael","Esposito","AAADSAD23cd");
			inBuiltsEmploys.put("123IDasd", worker1);
			inBuiltsEmploys.put("AAADSAD23cd", worker2);
			
			Persona stud1 = new Student("Andrea","Bianchi","1");
			Persona stud2 = new Student("John","Borg","2");
			inBuiltsStuds.put("1", stud1);
			inBuiltsStuds.put("2", stud2);
		}
		public Persona buildPersona(Class<? extends Persona> type, String idOnNumber) throws CloneNotSupportedException
		{
			Persona built = null;
			
			if(type.getCanonicalName().equals(Student.class.getCanonicalName()))
			{
				 built = (Persona) inBuiltsStuds.get(idOnNumber).clone();
			}
			else if(type.getCanonicalName().equals(Employee.class.getCanonicalName()))
			{
				 built = (Employee) inBuiltsEmploys.get(idOnNumber).clone();
			}
			
			return built;
		}
	}
	
	public static void main(String[] args) throws CloneNotSupportedException
	{
		PersonaBuilder builder = new PersonaBuilder();
		Persona worker1 = builder.buildPersona(Employee.class, "123IDasd");
		Persona stud1 = builder.buildPersona(Student.class,"2");
		System.out.println(worker1+"\n"+stud1);
	}
	
	
}
