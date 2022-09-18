
/*
 * Bridge pattern is used when you want to decouple
 * an abstract class (or interface) and some aspects of its implementation (because maybe
 * they can change often and indipendently from the implementations and the abstract)
 * Bridging with a third part interface
 */
public class BridgePattern {

	/*Example without bridge */
	public interface Shape
	{
		public double getArea();
		public void interact();
		public void draw();
	}
	
	public static class Rectangle implements Shape
	{
		public double w,h;
		public Rectangle(double w, double h) {this.w = w; this.h = h;}
		
		@Override
		public double getArea() {
			return w*h;
		}

		@Override
		public void interact() {
			System.out.println("interacting with a rectangle");
		}

		@Override
		public void draw() {
			System.out.println("drawing a rectangle");
		}
		
	}
	
	public static class Circle implements Shape
	{
		private double r;
		private final double PI = 6.28;
		
		public Circle(double r) {this.r = r;}
		
		@Override
		public double getArea() {
			return PI * r * r; 
		}

		@Override
		public void interact() {
			System.out.println("interacting with a circle");
		}

		@Override
		public void draw() {
			System.out.println("drawing a circle");
		}
		
	}
	
	//suppose we want to extract and decouple the draw methods
	//from the abstract interface Shape and its implementation
	//With the bridge it becomes
	
	public interface Shape2
	{
		public double getArea();
		public void interact();
		public default void draw(DrawingApi drawApi) { //use of the bridge
			drawApi.draw();
		}
	}
	
	public interface DrawingApi //this is the bridge interface
	{
		public void draw();
	}
	
	public static class Rectangle2 implements Shape2
	{
		public double w,h;
		public Rectangle2(double w, double h) {this.w = w; this.h = h;}
		
		@Override
		public double getArea() {
			return w*h;
		}

		@Override
		public void interact() {
			System.out.println("interacting with a rectangle");
		}
	}
	
	public static class Circle2 implements Shape2
	{
		private double r;
		private final double PI = 6.28;
		
		public Circle2(double r) {this.r = r;}
		
		@Override
		public double getArea() {
			return PI * r * r; 
		}

		@Override
		public void interact() {
			System.out.println("interacting with a circle");
		}
	}
	
	/*
	 * Bridge implementations
	 */
	public static class DrawingApiRectangle implements DrawingApi
	{

		@Override
		public void draw() {
			System.out.println("drawing a rectangle");
		}
	}
	
	public static class DrawingApiCircle implements DrawingApi
	{

		@Override
		public void draw() {
			System.out.println("drawing a circle");
		}
	}
	
	
	public static void main(String[] args)
	{
		System.out.println("without bridge");
		Shape circle = new Circle(10.0);
		Shape rectangle = new Rectangle(20.0,5.0);
		circle.draw();
		rectangle.draw();
		System.out.println("with the bridge");
		Shape2 circle2 = new Circle2(10.0);
		Shape2 rectangle2 = new Rectangle2(20.0,5.0);
		circle2.draw(  new DrawingApiCircle()   );
		rectangle2.draw( new DrawingApiRectangle() );
		//even with the lamba and anonymous classes
		Shape2 circle3 = new Circle2(20.0);
		circle3.draw(() -> {
			System.out.println("drawing with lambda a circle");
		});
	}
}
