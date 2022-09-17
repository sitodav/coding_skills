import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Builder is used when you want a proxy to build some kind of object
 * with a common interface/abstract class
 * but instead of defining the actual class as single input (like a factory)
 * you want to pass a set of inputs to shape the object (defining its parts one by one)
 * and then assemblying the final product like in a factory
 */
public class BuilderPattern {

	public static abstract class Edible
	{
		String temperature;
		String sauce;
		String cheese;
		String salt;
		String water;
		String description;
		
		public abstract void eatMe();
		
	}
	
	public static class Pizza extends Edible
	{

		@Override
		public void eatMe() {
			System.out.println("eating a pizza..."+description);
		}
		
	}
	
	public static class Pasta extends Edible
	{

		@Override
		public void eatMe() {
			System.out.println("eating pasta..."+description);
		}
		
	}
	
	public static class EdibleBuilder
	{
		private Map<String,String> listOfIngredients;
		
		public void initBuilder()
		{
			listOfIngredients = new HashMap<>();
		}
		
		public void setTemperature(String temp)
		{
			this.listOfIngredients.put("temperature",temp);
		}
		public void addSauce(String sauce)
		{
			this.listOfIngredients.put("sauce", sauce);
		}
		public void addCheese(String cheese)
		{
			this.listOfIngredients.put("cheese",cheese);
		}
		public void addSalt(String salt)
		{
			this.listOfIngredients.put("salt",salt);
		}
		public void addWater(String water)
		{
			this.listOfIngredients.put("water",water);
		}
		
		public Edible buildEdible(Class<? extends Edible> type) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
		{
			Edible built = type.getDeclaredConstructor().newInstance();
			//building phase
			built.cheese = this.listOfIngredients.get("cheese");
			built.salt = this.listOfIngredients.get("salt");
			built.temperature = this.listOfIngredients.get("temperature");
			built.sauce = this.listOfIngredients.get("sauce");
			built.water = this.listOfIngredients.get("water");
			
			built.description = "Edible [temperature=" + built.temperature + ", sauce=" + built.sauce + ", cheese=" + built.cheese + ", salt=" + built.salt
					+ ", water=" + built.water +  "]"; 
			
			return built;
		}
	}
	
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
	{
		EdibleBuilder builder = new EdibleBuilder();
		builder.initBuilder();
		builder.addCheese("lots of mozzarella cheese");
		builder.addWater("not so much water to cook");
		builder.addSalt("lot of salt");
		builder.addSauce("no sauce, white dough with zucchini");
		builder.setTemperature("very hot");
		Edible builtPizza = builder.buildEdible(Pizza.class);
		builtPizza.eatMe();
		
		builder.initBuilder();
		builder.addCheese("a little bit of parmigiano cheese");
		builder.addWater("loots of water to cook");
		builder.addSalt("sprinkle of salt");
		builder.addSauce("lot of pomodori sauce");
		builder.setTemperature("not so hot");
		Edible builtPasta = builder.buildEdible(Pasta.class);
		builtPasta.eatMe();
	}
}
