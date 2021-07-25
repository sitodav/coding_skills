import java.util.ArrayList;
import java.util.List;

/*
 * Composite Pattern is used when we have two different kind of objects:
 * 	compositions
 *  atomic
 *  but we want to use them the same way, hiding to the client the difference 
 *  An example is when we want to model some sort of expression-like language with classes
 *  An operand can be a single number or a composition of other operations on operands
 */
public class CompositePattern {

	public static abstract class Geographic
	{
		protected String name;
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Geographic(String name)
		{
			this.name = name;
		}
		public abstract String printInfo();
		
	}
	
	public static class Region extends Geographic //atomic 
	{
		
		public Region(String name) { super(name);}

		@Override
		public String printInfo() {
			return "my name is : "+name;
		}
	}
	
	public static class Country extends Geographic //composition
	{
		List<Geographic> regions = new ArrayList<>();
		
		public Country addRegion(Geographic region) {this.regions.add(region); return this;}
		public Country(String name) {super(name);}
		
		@Override
		public String printInfo() {
			 String regionNames = "\n";
			 for(Geographic region : regions)
				 regionNames += (region.printInfo()+",");
			 return "my country name is "+name+", and my regions are: "+regionNames;
		}
	}
	
	
	public static void main(String[] args)
	{
		Country country = new Country("Italy");
		country
			.addRegion(new Region("Molise"))
			.addRegion(new Region("Marche"))
			.addRegion(new Country("Lazio")
						.addRegion(new Region("San Marino"))
						);
		
		System.out.println(country.printInfo());
	}
}
