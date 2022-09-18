
/*
 * Facade pattern is used
 * when we have a provider with multiple methods
 * but we want to semplify the way the client interact with it
 * An example of facade is an orchestrator/coordinator (in a SAGA pattern)
 */
public class FacadePattern {

	
	public static class Kitchen
	{
		public void addPasta() {
			System.out.println("added pasta");
		}
		
		public void addSauce()
		{
			System.out.println("added sauce");
		}
		
		public void cookSauce()
		{
			System.out.println("cook sauce");
		}
		public void boilWater()
		{
			System.out.println("boil water");
		}
	}
	
	
	//The facade
	public static class KitchenFacade
	{
		Kitchen kitchen = new Kitchen();
		public void preparePastaMeal()
		{
			kitchen.boilWater();
			kitchen.addPasta();
			kitchen.cookSauce();
			kitchen.addSauce();
		}
	}
	
	public static void main(String[] args)
	{
		System.out.println("without facade...");
		Kitchen kitchen = new Kitchen();
		kitchen.boilWater();
		kitchen.addPasta();
		kitchen.cookSauce();
		kitchen.addSauce();
		System.out.println("with the facade...");
		KitchenFacade kitchenFacade = new KitchenFacade();
		kitchenFacade.preparePastaMeal();
		
	}
}
