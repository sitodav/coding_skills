import java.util.ArrayList;
import java.util.List;

/*
 * Example to show the use of command pattern
 * Command pattern requires 4 components:
 *  A command
 *  A receiver
 *  A client
 *  An invoker
 *  
 *  It allows to decouple the object who has the implementations (the receiver) from the 
 *  client calls (instead of having the client calling the receiver methods,
 *  the client calls the invoker, passing the commands to schedule).
 *  It's obtained wrapping the use of the different receiver methods into command implementations.
 *  In this way the command implementations can be called as sort of facade, but can even be scheduled, 
 *  delayed etc...
 */
public class CommandPattern {

	
	/*The receiver *****************************************************************************/
	private static class Cuoco
	{
		public void prendiPentola(){ System.out.println("prendiPentola");}
		public void prendiGriglia() { System.out.println("prendiGriglia");}
		public void accendiFornelli() { System.out.println("accendiFornelli");}
		public void spegniFornelli() { System.out.println("spegniFornelli");}
		public void soffriggiCipolla() { System.out.println("soffriggiCipolla");}
		public void versaSugo() { System.out.println("versaSugo");}
		public void mettiPesce() { System.out.println("mettiPesce");}
		public void cuociPasta() { System.out.println("cuociPasta");}
	}
	
	/*The commands ****************************************************************************/
	private static abstract class CommandOrdine
	{
		public abstract void execute();
		public void setCuoco(Cuoco cuoco) {this.cuoco = cuoco;}
		public Cuoco cuoco; //the wrapped receiver, it is set by the invoker
		 
		
	}
	
	private static class CommandOrdinePasta extends CommandOrdine 
	{
		 
		@Override
		public void execute() {
			this.cuoco.prendiPentola();
			this.cuoco.accendiFornelli();
			this.cuoco.soffriggiCipolla();
			this.cuoco.versaSugo();
			this.cuoco.cuociPasta();
			this.cuoco.spegniFornelli();
		}
		
	}
	
	private static class CommandOrdineGrigliataDiPesce extends CommandOrdine
	{
	  
		@Override
		public void execute() {
			this.cuoco.prendiGriglia();
			this.cuoco.accendiFornelli();
			this.cuoco.mettiPesce();
			this.cuoco.spegniFornelli();
		}
		
	}
	
	
	/* The invoker **************************************************************************/
	private static class Cameriere
	{
		Cuoco cuoco = new Cuoco(); //receiver
		List<CommandOrdine> ordini = new ArrayList<>();
		
		public void addOrdine(CommandOrdine nuovoOrdine) {
			nuovoOrdine.setCuoco(this.cuoco); //we set the receiver, because the client doesn't know anything about it
			this.ordini.add(nuovoOrdine);
		}
		
		public void evadiOrdine()
		{
			if(this.ordini.size() > 0)
			{
				this.ordini.remove(0).execute();
			}
		}
	}
	
	
	/* The Client **************************************************************************/
	public static void main(String[] args) throws InterruptedException
	{
		/*The client can send the request to the final receiver not directly
		 * otherwise he should call all the methods in a precise order on the receiver (cookPasta(), accendiFornelli() etc...)
		 * but using the commands it doesn't have to know how the receiver methods work.
		 * And on the other hand, the client can send this wrapped "requests" of calls in a manner that can be handled asynchronously
		 * because the calls are handled by the invoker when he wants
		 */
		
		Cameriere invoker = new Cameriere();
		System.out.println("I WANT PASTA:");
		invoker.addOrdine(new CommandOrdinePasta());
		
		System.out.println("I WANT GRIGLIATA DI PESCE");
		invoker.addOrdine(new CommandOrdineGrigliataDiPesce());
		
		//async handling by the invoker
		Thread.sleep(2000);
		invoker.evadiOrdine();
		Thread.sleep(2000);
		invoker.evadiOrdine();
		
		
	}
}
