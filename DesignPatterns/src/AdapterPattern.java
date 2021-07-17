
/*
 * Adapter pattern, when the client wants to use a class
 * but the class, as it is, has some tweaks to do
 * and we cannot change it
 * (for example if we want to use legacy code that cannot be changed 
 * otherwise it would break something else)
 */
public class AdapterPattern {

	public static interface Automotive
	{
		public double getSpeed(); 
	}
	
	public static class AmericanBrandAutomobile implements Automotive
	{

		@Override
		public double getSpeed() { //returns the speed as mph/h
			return 80.0;
		}
	}
	
	//The client wants to use the Automotive and the AmericanBrandAutomobile 
	//classes, but it wants the speed in km/h
	
	//So we use an adapter
	
	public static class EuropeanAdapterAutomobile implements Automotive
	{
		private Automotive automotiveToBeAdapted;
		public EuropeanAdapterAutomobile(Automotive toBeAdapted)
		{
			this.automotiveToBeAdapted = toBeAdapted;
		}
		
		private double convertMphToKmH(double speedToBeConverted)
		{
			return 1.6 * speedToBeConverted; //mph to kmh
		}
		@Override
		public double getSpeed() {
			return this.convertMphToKmH(this.automotiveToBeAdapted.getSpeed());
		}
		
	}
	
	public static void main(String[] args)
	{
		//Without the adapter
		Automotive americanCar = new AmericanBrandAutomobile();
		System.out.println("In america, my speed is "+americanCar.getSpeed()+" mph");
		//With the adapter
		EuropeanAdapterAutomobile adapter = new EuropeanAdapterAutomobile(americanCar);
		System.out.println("In europe, my speed is "+adapter.getSpeed()+" kmh");
	}
}
