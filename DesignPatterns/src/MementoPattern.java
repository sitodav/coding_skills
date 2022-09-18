
/*
 * 
 * Memento pattern is used when we want to create a mechanism to store / and restore the state of a given object
 * The object whose internal state we want to save/restore is the ORIGINATOR
 * 	the ORIGINATOR only knows its internal state at one moment, and how to save/restore it's state (DOES NOT KNOW WHEN TO SAVE / RESTORE ITS STATE)
 * THe wrapper of the state is the MEMENTO
 * The actor who knows WHEN to call save/restore and decides when to do it, is the CARETAKER, and it keeps/uses the mementos produces by the ORIGINATOR
 */
public class MementoPattern {

	
	/*Example*/
	
	public static interface Originator<T> //Originator interface
	{
		public Memento<T> saveState();
		public void restoreState(Memento<T> state);
	}
	
	public static class Memento<T> //Memento class
	{
		T data;  //this is the wrapped object
		public Memento(T data) {this.data = data;}
	}
	
	//we don't need the caretaker interface because it simply interacts with the originator 
	
	public static class StudentInfographic //this is the kind of data we ll incapsulate in the student and wrap in the memento
	{
		String name;
		String surname;
		String age;
		String schoolNumber;
		public StudentInfographic(String name, String surname, String age, String schoolNumber) {this.name = name; this.surname = surname; this.schoolNumber = schoolNumber; this.age = age;}
	}
	//this is the actual originator implementation
	public static class Student implements Originator<StudentInfographic>
	{
		private StudentInfographic studentInfo; //this represents the originator internal state at a given moment.
		
		
		public StudentInfographic getStudentInfo() {
			return studentInfo;
		}

		public void setStudentInfo(StudentInfographic studentInfo) {
			this.studentInfo = studentInfo;
		}

		public Student(StudentInfographic studentInfo) {
			this.studentInfo = studentInfo;
		}
		
		//important methods....
		@Override
		public Memento<StudentInfographic> saveState() { //this creates a new memento
			return new Memento<>(this.studentInfo);
		}

		@Override
		public void restoreState(Memento<StudentInfographic> state) { //this uses the memento to restore the originator internal state
			//we want to restore the state copying the variables, not using the instance, otherwise we would couple the memento wrapper with the
			//internal state in two way (wrong) way
			this.studentInfo.age = new String(state.data.age); 
			this.studentInfo.name = new String(state.data.name);
			this.studentInfo.schoolNumber = new String(state.data.schoolNumber);
			this.studentInfo.surname = new String(state.data.surname);
		}
		
		@Override
		public String toString() {
			return "name:"+this.studentInfo.name+", surname: "+this.studentInfo.surname+", age: "+this.studentInfo.age+", schoolNum: "+this.studentInfo.schoolNumber;
		}
	}
	
	
	
	
	//Caretaker example (THE CARETAKER IS THE CONSUMER/CLIENT OF THE ORIGINATOR)
	public static void main(String[] args)
	{
		System.out.println("creating student");
		Student stud = new Student(new StudentInfographic("John","Borg","12","28"));
		System.out.println("student created "+stud);
		System.out.println("we change the internal state");
		stud.setStudentInfo(new StudentInfographic("Bob", "Swoff", "32","18"));
		System.out.println("student: "+stud);
		System.out.println("We save the internal state");
		Memento<StudentInfographic> studMemento1 = stud.saveState(); // IT'S THE CARETAKER WHO KNOWS WHEN TO SAVE (AND IT KEEPS THE WRAPPED PREVIOUS STATE, NOT THE ORIGINATOR)
		System.out.println("We change again the student state...");
		stud.setStudentInfo(new StudentInfographic("Maria","Jovi","44","12"));
		System.out.println("student now: "+stud);
		System.out.println("Restoring previous saved state");
		stud.restoreState(studMemento1);
		System.out.println("student after restoring: "+stud);
	}
	
	//IMPORTANT NOTE: THE CARETAKER IS THE USER WHO KNOWS WHEN TO SAVE / RESTORE
	//YOU DON'T HAVE TO KEEP THE MEMENTO STATES IN THE CARETAKER (LIKE IN THE PREVIOUS EXAMPLE), BUT YOU CAN
	//MEMORIZE THEM IN A STACK LIKE STRUCTURE IN THE ORIGINATOR ITSELF, SO THAT YOU CAN 
	//RESTORE ALL THE PREVIOUS STATES IN A UNDO FASHION
}
