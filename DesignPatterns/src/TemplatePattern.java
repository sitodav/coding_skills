import java.lang.reflect.InvocationTargetException;

/*
 * Template pattern is used when we want to "force" a given order of operations
 * on a client request.
 * The method , inherited (and not overridable) from the class
 * forces the sequence of operations.
 * The single operations are defined from the child classes in the inheritance 
 */
public class TemplatePattern {
	
	
	public static abstract class TheGame
	{
		public abstract void startTheGame();
		public abstract void firstMove();
		public abstract void keepPlayin();
		public abstract void winOrLose();
		public abstract void endTheGame();
		
		public final void playTheGame() //NOTE THE FINAL, CANNOT BE OVERRIDEN
		{
			this.startTheGame();
			this.firstMove();
			this.keepPlayin();
			this.winOrLose();
			this.endTheGame();
		}
	}
	
	public static class Monopoli extends TheGame
	{

		@Override
		public void startTheGame() {
			System.out.println("Let's choose my figure");
			
		}

		@Override
		public void firstMove() {
			System.out.println("roll the first dice");
		}

		@Override
		public void keepPlayin() {
			System.out.println("I hate this game");
		}

		@Override
		public void winOrLose() {
			System.out.println("I am losing all my money");
		}

		@Override
		public void endTheGame() {
			System.out.println("fck Monopoli");
		}
		
	}
	
	public static class Starcraft extends TheGame
	{

		@Override
		public void startTheGame() {
			System.out.println("Searching for game on battle.net");
		}

		@Override
		public void firstMove() {
			System.out.println("let's zerg flood");
		}

		@Override
		public void keepPlayin() {
			System.out.println("my connection sucks");
		}

		@Override
		public void winOrLose() {
			System.out.println("I hate you humans");
		}

		@Override
		public void endTheGame() {
			System.out.println("GG NO RE. Disconnected");
		}
		
	}
	
	//This is used to allow client's access only to playTheGame() method
	//it's not mandatory for the template pattern
	//This, specifically, is a proxy pattern
	public static class TemplateProxy
	{
		private TheGame gameInstance;
		
		public TemplateProxy(Class<? extends TheGame> whatGameYouWant) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
		{
			gameInstance = whatGameYouWant.getDeclaredConstructor().newInstance();
		}
		public void playTheGame()
		{
			this.gameInstance.playTheGame();
		}
	}
	
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
	{
		TemplateProxy monopoliGame = new TemplateProxy(Monopoli.class);
		TemplateProxy starcraftGame = new TemplateProxy(Starcraft.class);
		//THE CLIENT HERE CAN ACCESS ONLY TO playTheGame() method because it is proxied via TemplateProxy
		monopoliGame.playTheGame();
		starcraftGame.playTheGame();
	}
	
}
