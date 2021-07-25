/*
 * Visitor Pattern is used
 * when we want to garantee the OpenClosed principle (every new feature should be
 * added without modifying/refactoring the previous code, but writing new code)
   The visitor pattern let a class to add new functionality without having to 
   modify it (or extending it) but externalizing the behaviour
   (it's a mechanism similar to the bridge pattern, the difference is that in the visitor
   it access in a deeper way)
 */
public class VisitorPattern {

	public interface Translator //this is the "Visitor" interface
	{
		public void translate(Translatable visitable); //this is the "visit" method of the visitor
	}
	public static abstract class Translatable //this is the "Visitable" interface
	{
		public final void acceptTranslator(Translator visitor) //this is the "accept" method of the visitable (inherited by all the translator and not overridable)
		{
			visitor.translate(this);
		}
	}
	
	//These are the classes we don't to change if we want to implement
	//a different translator for a specific class
	public static class ItalianPoem extends Translatable
	{
		String originalPoem;
		String translatedPoem;
		
		public ItalianPoem(String originalPoem) {this.originalPoem = originalPoem;}
		 
	}
	
	public static class EnglishPoem extends Translatable
	{
		String originalPoem;
		String[] translatedPoemAsTokens;
		
		public EnglishPoem(String originalPoem) {this.originalPoem = originalPoem;}
		 
	}
	
	public static class BinaryPoem extends Translatable
	{
		String originalPoem;
		byte[] bytesB;
		
		public BinaryPoem(String originalPoem) {this.originalPoem = originalPoem;}

	}
	
	//this is the Visitor, which define the (accept) translate method,
	//and delegate to overloaded methods according to the actual type of the (visitable) caller
	public static class CustomTranslator implements Translator
	{

		@Override
		public void translate(Translatable visitable) {
			if(visitable instanceof ItalianPoem) //this is necessary because the overloading is resolved at compile time (so we have to differenciate at runtime on the different
				this.translate((ItalianPoem)visitable); //subclasses of the translatable, otherwise the same translate(Translatable) would be called)
			else if(visitable instanceof EnglishPoem)
				this.translate((EnglishPoem)visitable);
			else if(visitable instanceof BinaryPoem)
				this.translate((BinaryPoem)visitable);
		}
		
		//overloading to change the behaviour according to the calling visitable
		private void translate(ItalianPoem poem)
		{
			System.out.println("translation for italian poem");
			poem.translatedPoem = poem.originalPoem.replace("sw", "bb");
		}
		
		private void translate(EnglishPoem poem)
		{
			System.out.println("translation for english poem");
			poem.translatedPoemAsTokens = poem.originalPoem.split(",");
		}
		
		private void translate(BinaryPoem poem)
		{
			System.out.println("translation for binary poem");
			poem.bytesB = poem.originalPoem.getBytes();
		}
	}
	
	public static void main(String[] args)
	{
		Translatable italianPoem = new ItalianPoem("sw ciao sw");
		Translatable engPoem = new EnglishPoem("hi mate");
		Translatable binaryPoem = new BinaryPoem("111001");
		
		CustomTranslator universalTranslator = new CustomTranslator();
		italianPoem.acceptTranslator(universalTranslator);
		engPoem.acceptTranslator(universalTranslator);
		binaryPoem.acceptTranslator(universalTranslator);
	}
	
	
	
}
