import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/*
 * 
 * Flyweight pattern is used when we want to reduce RAM (heap) consuption 
 * by reducing the number of object instances
 * We have a factory method (or an abstract factory) used to create new instances only when some
 * requirements are met, otherwise previous instances are reused
 * 
 * The important thing are:
 * YOU HAVE TO DEFINE SOME VARIABLES/PROPERTIES OF THE OBJECT (you want to duplicate/instantiate) used to decide if a new instance is to be built (or an old one reused)
 * YOU HAVE TO PREVENT the user of the object (the consumer of the factory) to change the shared instances
 */
public class FlyweightPattern {

	/*
	 * For example imagine you have a student class.
	 * Each student has an internal variables which points to a LanguageProfessor
	 * We don't want to create a new LanguageProfessor when there is already one (associated to another student) for the same language
	 * But we want different students, with the same native language, to share the same professor
	 */
	
	public static enum Language {ENGLISH, ITALIAN, SPANISH, LATIN, FRENCH;}
	
	public static class LanguageProfessor
	{
		private Language language;
		public String profId ;
		public LanguageProfessor(Language language) 
		{
			this.profId = Math.random() +"";
			this.language= language;
		}
		
		public String teach()
		{
			String teach = null;
			switch(this.language)
			{
				case ENGLISH:
				teach = "I am teaching you"+" id:"+this.profId;
				break;
				case ITALIAN:
				teach = "Ti sto insegnando"+" id:"+this.profId;
				break;
				case SPANISH:
				teach = "Te estoy enseñando"+" id:"+this.profId;
				break;
				case LATIN:
				teach = "Linguam Latinam doceo te"+" id:"+this.profId;
				break;
				case FRENCH:
				teach = "je t'apprends"+" id:"+this.profId;
				break;
			}
			return teach;
		}
		
		//the user of the class cannot change the language of the professor
	}
	
 
	public static class Student  
	{
		private LanguageProfessor myProfessor;
		private String name;
		
		private Student(String name) //the constructor is private
		{
			this.name = name;
		}

		public LanguageProfessor getMyProfessor() {
			return myProfessor;
		}

//		public void setMyProfessor(LanguageProfessor myProfessor) { //the client of the class cannot change the professor
//			this.myProfessor = myProfessor;
//		}

		public String getName() {
			return name;
		}

	 
		public void setName(String name) {
			this.name = name;
		}
		
		public void learn()
		{
			System.out.println(this.myProfessor.teach());
		}
		
	}
	
	public static class StudentFactory
	{
		Map<String,LanguageProfessor> existingProfessors = new HashMap<>();
		
		public  Student createStudent(String name, Language language)
		{
			//we check if there is already a professor for that language
			LanguageProfessor associatedProf = null;
			if(null == (associatedProf = this.existingProfessors.get(language.toString())))
			{
				associatedProf = new LanguageProfessor(language);
				this.existingProfessors.put(language.toString(), associatedProf);
			}
			Student newStudent = new Student(name);
			newStudent.myProfessor = associatedProf; //here the factory can access the student's professor because even if is private, they are both inner classes,
			//but the end user will not be able to access the myProfessor variable
			return newStudent;
		}
	
	}
	
	public static void main(String[] args)
	{
		StudentFactory studFactory = new StudentFactory();
		Student stud = studFactory.createStudent("Davide", Language.ENGLISH);
		Student stud2 = studFactory.createStudent("John", Language.ITALIAN);
		Student stud3 = studFactory.createStudent("MIchael", Language.ENGLISH);
		Student stud4 = studFactory.createStudent("Andrea", Language.FRENCH);
		Student stud5 = studFactory.createStudent("Lolita", Language.LATIN);
		Student stud6 = studFactory.createStudent("Josh", Language.FRENCH);
		
		stud.learn();
		stud2.learn();
		stud3.learn();
		stud4.learn();
		stud5.learn();
		stud6.learn();
	}
	
	
}
