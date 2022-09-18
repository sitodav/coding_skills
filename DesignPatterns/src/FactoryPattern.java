import java.lang.reflect.InvocationTargetException;

public class FactoryPattern {

	public static interface Car
	{
		public String getModel();
		public double getSpeed();
		public String turnOn();
	}
	
	public static class Porsche implements Car
	{

		public Porsche() {}
		@Override
		public String getModel() {
			return "I am a Porsche, bitch!";
		}

		@Override
		public double getSpeed() {
			return 200;
		}

		@Override
		public String turnOn() {
			return "bruuuuuum bruuuuum";
		}
		
	}
	
	public static class Fiat implements Car
	{

		public Fiat() {}
		@Override
		public String getModel() {
			return "I am a fucking Fiat, I suck";
		}

		@Override
		public double getSpeed() {
			return 20;
		}

		@Override
		public String turnOn() {
			return "prrrrrrrrr...prrrrbroom";
		}
		
	}
	
	public static class CarFactory
	{
		public <T extends Car> T getCar(Class<T> carBrand)  
		{
			try
			{
				return carBrand.getDeclaredConstructor().newInstance();
			}
			catch(Exception ex) {}
			
			return null;
		}
	}
	
	
	public static void main(String[] args)  
	{
		CarFactory carFactory = new CarFactory();
		System.out.println("I feel like i want a Fiat");
		Car fiatCar = carFactory.getCar(Fiat.class);
		System.out.println(fiatCar.getModel()+" my speed is "+fiatCar.getSpeed()+" ,"+fiatCar.turnOn());
		System.out.println("I feel like i want a real Porsche");
		Car porsche = carFactory.getCar(Porsche.class);
		System.out.println(porsche.getModel()+" my speed is "+porsche.getSpeed()+" ,"+porsche.turnOn());
	}
}
