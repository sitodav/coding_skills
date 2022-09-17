

/*
 * 
 * Strategy Pattern is used when we have multiple methods
 * which are inherited between multiple classes.
 * Some of these classes (in the hierarchy tree) share some methods,
 * and defines others (some new, some with override)
 * When we are in a situation when some overrides are commons, but are not
 * sharable because you can't have multiple inheritance, you end up with 
 * code duplication.
 * You can try to solve the situation refactoring the hierarchy tree, but you end up
 * with a messy and overcomplicated situation.
 * In this case it's better to extract the common methods and use the COMPOSITION OVER INHERITANCE
 * (hence the strategy pattern)
 */
public class StrategyPattern {
	
	/*Here a simple example WITHOUT Strategy pattern
	 * using simple Inheritance, to show a problem in the 
	 * hierarchy tree
	 */
	
	public static abstract class Electronic
	{
		public void connectThePlug()
		{
			System.out.println("Connecting the plug");
		}
		
		public void disconnectThePlug()
		{
			System.out.println("Disconnecting the plug");
		}
		
		public abstract void turnOn();
		public abstract void turnOff();
	}
	
	public static class CoffeeMachine extends Electronic
	{

		@Override
		public void turnOn() {
			System.out.println("pressing the ON button");
		}

		@Override
		public void turnOff() {
			System.out.println("pressing the OFF button");
		}
		
		public void makeCoffee()
		{
			System.out.println("shuuuuuu...making coffee");
		}
		
	}
	
	public static class MobilePhone extends Electronic
	{

		@Override
		public void turnOn() { //CODE DUPLICATION with the CoffeeMachine class, because MobilePhone cannot extends from CoffeeMachine
			System.out.println("pressing the ON button"); 
		}

		@Override
		public void turnOff() { //CODE DUPLICATION with the CoffeeMachine class, because MobilePhone cannot extends from CoffeeMachine
			System.out.println("pressing the OFF button");
		}
		
		public void callSomeone()
		{
			System.out.println("calling someone...");
		}
		
	}
	
	public static class HairCutter extends Electronic
	{

		@Override
		public void turnOn() {
			System.out.println("turn on the thingy");
		}

		@Override
		public void turnOff() {
			System.out.println("turn off the thingy");
			
		}
		
		public void cutHair()
		{
			System.out.println("bzzzz cutting hair");
		}
		
	}
	
	//Apon we have a code duplication for the two methods defined in two classes.
	//The code cannot be shared by defining it in the mother class (Electronics) otherwise
	//we would have a specific definition in an high level abstract class
	
	
	//The same Example with the strategy pattern below:
	//In the strategy pattern we prefer the composition over inheritance
	//and we normalize the methods (separation) and the we aggregate them in the compositions
	//according to what we want
	public static class Electronics2
	{
		public TurnableOnOff useThisToTurnOnOff;
		public void connectThePlug()
		{
			System.out.println("Connecting the plug");
		}
		
		public void disconnectThePlug()
		{
			System.out.println("Disconnecting the plug");
		}
	}
	
	public interface TurnableOnOff
	{
		public void turnOn();
		public void turnOff();
	}
	
	public static class TurnableOnOffWithButtonImpl implements TurnableOnOff
	{
		@Override
		public void turnOn() {
			System.out.println("pressing the ON button");
		}

		@Override
		public void turnOff() {
			System.out.println("pressing the OFF button");
		}
	}
	
	public static class TurnableOnOffWithThingyImpl implements TurnableOnOff
	{
		@Override
		public void turnOn() {
			System.out.println("turn on the thingy");
		}

		@Override
		public void turnOff() {
			System.out.println("turn off the thingy");
		}
	}
	
	public static class CoffeeMachine2 extends Electronics2 
	{
		public CoffeeMachine2()
		{
			this.useThisToTurnOnOff = new TurnableOnOffWithButtonImpl();
		}
		public void makeCoffee()
		{
			System.out.println("shuuuuuu...making coffee");
		}
	}
	
	public static class MobilePhone2 extends Electronics2
	{
		public MobilePhone2()
		{
			this.useThisToTurnOnOff = new TurnableOnOffWithButtonImpl(); 
		}
		public void callSomeone()
		{
			System.out.println("calling someone...");
		}
	}
	
	public static class HairCutter2 extends Electronics2
	{
		public HairCutter2()
		{
			this.useThisToTurnOnOff = new TurnableOnOffWithThingyImpl(); 
		}
		public void cutHair()
		{
			System.out.println("bzzzz cutting hair");
		}
	}
	
	
	public static void main(String[] args)
	{
		System.out.println("Without strategy...");
		Electronic coffeMaker1 = new CoffeeMachine();
		Electronic mobilePhone = new MobilePhone();
		Electronic hairCutter = new HairCutter();
		
		coffeMaker1.connectThePlug();
		coffeMaker1.turnOn();
		coffeMaker1.turnOff();
		coffeMaker1.disconnectThePlug();
		mobilePhone.connectThePlug();
		mobilePhone.turnOn();
		mobilePhone.turnOff();
		mobilePhone.disconnectThePlug();
		hairCutter.connectThePlug();
		hairCutter.turnOn();
		hairCutter.turnOff();
		hairCutter.disconnectThePlug();
		
		System.out.println("With strategy...");
		Electronics2 coffeMaker2 = new CoffeeMachine2();
		Electronics2 mobilePhone2 = new MobilePhone2();
		Electronics2 hairCutter2 = new HairCutter2();
		
		coffeMaker2.connectThePlug();
		coffeMaker2.useThisToTurnOnOff.turnOn();
		coffeMaker2.useThisToTurnOnOff.turnOff();
		coffeMaker2.disconnectThePlug();
		mobilePhone2.connectThePlug();
		mobilePhone2.useThisToTurnOnOff.turnOn();
		mobilePhone2.useThisToTurnOnOff.turnOff();
		mobilePhone2.disconnectThePlug();
		hairCutter2.connectThePlug();
		hairCutter2.useThisToTurnOnOff.turnOn();
		hairCutter2.useThisToTurnOnOff.turnOff();
		hairCutter2.disconnectThePlug();
	}
	
	

}
