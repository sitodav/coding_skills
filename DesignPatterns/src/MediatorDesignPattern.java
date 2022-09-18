/*
 * The Mediator Design pattern is used when we have different classes that
 * have to interact, but we don't want to couple them
 * The mediator is the only one coupled to the class
 * A sort of coordinator
 */
public class MediatorDesignPattern {

	
	/*Example without Mediator Pattern */
	
	public static class Postman
	{
		private Intercom intercom ;
		private DoorStep doorStep;
		private Package pack ;
		
		public Postman() {}
		public Postman(Intercom intercom, DoorStep doorStep, Package pack)
		{
			this.intercom = intercom; this.doorStep = doorStep; this.pack = pack;
		}
		
		public String identifyMyself()
		{
			return "I am the postman";
		}
		
		public void completeJob()
		{
			this.intercom.pressIntercom();
			this.intercom.person.waitForResponse();
			this.doorStep.goTo();
			this.pack.drop();
		}
		 
	}
	
	public static class Intercom
	{
		Person person;
		Postman postman;
		
		public Intercom(Postman postman, Person person)
		{
			this.person = person; this.postman = postman;
		}
		public void pressIntercom()
		{
			System.out.println("riiiiing");
			System.out.println("its: "+postman.identifyMyself());
		}
	}
	
	public static class DoorStep
	{
		Person person;
		public DoorStep (Person person) {this.person = person;}
		
		public void open()
		{
			System.out.println("someone is opening the door");
		}
		public void goTo()
		{
			System.out.println("someone is walking the doorstep");
		}
	}
	
	public static class Package
	{
		public void drop()
		{
			System.out.println("postman drops the package");
		}
	}
	
	public static class Person
	{
		Intercom intercom;
		Postman postman;
		DoorStep doorStep;
		
		public Person()
		{
			
		}
		public Person(Intercom intercom, Postman postman, DoorStep doorstep)
		{
			this.intercom = intercom; 
			this.postman = postman; 
			this.doorStep = doorstep;
		}
		
		public void waitForResponse()
		{
			System.out.println("person: I am coming...");
		}
		
		public void enjoyThePackage()
		{
			System.out.println("person: It's a nice package!");
		}
		
		
	}
	
	//As we can se, the classes are tightly coupled
	
	
	
	/*Example with the mediator */
	
	public static class Postman2
	{
		 
		
		public Postman2() {}
		 
		
		public String identifyMyself()
		{
			return "I am the postman";
		}
		 
		 
	}
	
	public static class Intercom2
	{
		
		public void pressIntercom(Postman2 postman)
		{
			System.out.println("riiiiing");
			System.out.println("its: "+postman.identifyMyself());
		}
	}
	
	public static class DoorStep2
	{
		 
		public void open()
		{
			System.out.println("someone is opening the door");
		}
		public void goTo()
		{
			System.out.println("someone is walking the doorstep");
		}
	}
	
	public static class Package2
	{
		public void drop()
		{
			System.out.println("postman drops the package");
		}
	}
	
	public static class Person2
	{
		 
		
		public void waitForResponse()
		{
			System.out.println("person: I am coming...");
		}
		
		public void enjoyThePackage()
		{
			System.out.println("person: It's a nice package!");
		}
		
	}
	
	public static class MailService //THIS IS THE MEDIATOR
	{
		/*now the classes are decoupled, so we don't have instances dependency hell */
		Person2 person = new Person2();
		Postman2 postman = new Postman2();
		DoorStep2 doorstep = new DoorStep2();
		Package2 pack = new Package2();
		Intercom2 intercom = new Intercom2();
		
		public void completeTheTask()
		{
			intercom.pressIntercom(postman);
			person.waitForResponse();
			doorstep.goTo();
			pack.drop();
			person.enjoyThePackage();
		}
		
	}

	public static void main(String[] args)
	{
		
		System.out.println("WITHOUT THE MEDIATOR");
		//dependency hell , but this can be resolved with dependencly injection (inversion of control) 
		//but just once we have decoupled the classes with the mediator
		Person person = new Person();
		DoorStep door = new DoorStep(person);
		Postman postman = new Postman();
		Intercom intercom = new Intercom(postman, person);
		postman.doorStep = door;
		postman.intercom = intercom;
		postman.pack = new Package();
		person.doorStep = door;
		person.intercom = intercom;
		person.postman = postman;
		
		postman.completeJob();
		person.enjoyThePackage();
		
		System.out.println("WITH THE MEDIATOR");
		
		MailService mediator = new MailService();
		mediator.completeTheTask();
		
		
	}
	
}
