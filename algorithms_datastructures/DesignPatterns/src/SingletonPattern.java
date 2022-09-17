
public class SingletonPattern {

	private static class MyCustomSingleton
	{
		public String value;
		private static MyCustomSingleton singletonInstance = null;

		private MyCustomSingleton() {
		}

		public static MyCustomSingleton getMyCustomSingleton(String value) {

			if (null == singletonInstance) {
				singletonInstance = new MyCustomSingleton();
			}

			singletonInstance.value = value;
			return singletonInstance;

		}
	}
	
	public static void main(String[] args)
	{
		MyCustomSingleton singleton = MyCustomSingleton.getMyCustomSingleton("HELLO");
		System.out.println("value: "+singleton.value);
		MyCustomSingleton refSingleton2 = MyCustomSingleton.getMyCustomSingleton("CHANGED");
		System.out.println("value: "+singleton.value);
	}
}
