import java.util.ArrayList;
import java.util.List;

/*
DECORATOR USE COMPOSITION (HAS INTERFACE) + INHERITANCE (IS A INTERFACE)
* LIKE ADAPTER AND PROXY
* 	THE DIFFERENCE IS THAT ADAPTER CHANGE THE INTERFACE RESPECT TO THE CLIENT
*  DECORATOR ENHANCE THE INTERFACE TO THE CLIENT
*  PROXY PROVIDES THE SAME INTERFACE
*  
*/
public class DecoratorPattern {

	/*Common Interface and plain classes */
	
	public static interface Eadible
	{
		public void eatMe();
	}
	
	public static class Icecream implements Eadible
	{

		@Override
		public void eatMe() {
			System.out.println("gnam gnam it's cold");
			
		}
		
	}
	
	public static class Pizza implements Eadible
	{

		@Override
		public void eatMe() {
			System.out.println("gnam gnam it's hot");
			
		}
		
	}
	
	/*Providers (they can be controllers , services etc...) */
	public static class IcecreamShop
	{
		public Eadible gimmeIcecream()
		{
			return new Icecream();
		}
	}
	
	public static class Pizzeria
	{
		public Eadible gimmePizza()
		{
			return new Pizza();
		}
	} 
	
	/*Decorator, to decore/aggregate from multiple classes*/
	/*The decorator is a Edible, and has a Edible (composition + inheritance )
	 * and enhance (in this case using aggregation) the Eadible interface */
	public static class CompositeOrder implements Eadible
	{
		public Eadible pizza;
		public Eadible gelato;

		public CompositeOrder(Eadible pizza, Eadible gelato)
		{
			this.pizza = pizza;
			this.gelato = gelato;
		}
		
		private void greedyEating()
		{
			System.out.println("SLUUURP....I m gonna order from this nice cafè a multiple items order");
		}
		
		@Override
		public void eatMe() {
			greedyEating();
			pizza.eatMe();
			gelato.eatMe();
			
		}
		
	}
	
	/*Provider using decorator (This provider is another pattern, a Facade)*/
	public static class Cafe
	{
		Pizzeria pizzeria;
		IcecreamShop icecreamShop;
		
		public Cafe(Pizzeria pizzeria, IcecreamShop icecreamShop)
		{
			this.pizzeria = pizzeria;
			this.icecreamShop = icecreamShop;
		}
		public Eadible gimmePizzaEGelato()
		{
			CompositeOrder pizzaEGelato = new CompositeOrder(pizzeria.gimmePizza(), icecreamShop.gimmeIcecream());
			return pizzaEGelato;
		}
	}
	
	//Example
	public static void main(String[] args)
	{
		List<Eadible> eatThese = new ArrayList<>();
		
		Pizzeria pizzeria = new Pizzeria();
		IcecreamShop gelateria = new IcecreamShop();
		eatThese.add(pizzeria.gimmePizza());
		eatThese.add(gelateria.gimmeIcecream());
		Cafe pizzeriaEGelateria = new Cafe(pizzeria,gelateria);
		eatThese.add(pizzeriaEGelateria.gimmePizzaEGelato());
		
		/*Here we use the plain classes, and the decored one, as if it was
		 * the same, because the decorator is an edible (so it's used like the normal classes)
		 *  and has an edible (so it can decorate it)
		 */
		for(Eadible edible : eatThese)
			edible.eatMe();
	}
}
