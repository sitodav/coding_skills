
/*Proxy pattern uses composition (has a) and inheritance (is a) of an interface,
 * like decorator and adapter.
 * The difference is that proxy provides the same interface
 * usually to alter some behaviour of the wrapped original element (ofter for performance issues)
 */
public class ProxyPattern {

	/*For example imagine we have a DB connector, that originally the client
	 * can create, and call the initMethod , which is really heavy, everytime it wants
	 * We cannot change the original connector, but we want to avoid the client calling the
	 * init methods multiple times
	 * 
	 */
	
	public interface DBConnector
	{
		public void init() throws InterruptedException; 
		public void connect();
	}
	
	public static class JDBCConnector implements DBConnector
	{

		@Override
		public void init() throws InterruptedException {
			System.out.println("REALLY HEAVY INIT...");
			Thread.sleep(3000);
			System.out.println("Init completed");
		}

		@Override
		public void connect() {
			System.out.println("connecting to db");
		}
	}
	
	/*Proxy class */
	public static class JDBCConnectorProxy implements DBConnector //is a DBConnector (inheritance)
	{
		private DBConnector wrappedJDBCConnector; //has DBConnector (composition)

		@Override
		public void init() throws InterruptedException {
			if(wrappedJDBCConnector == null) //heavy call only on the first time
			{
				wrappedJDBCConnector = new JDBCConnector();
				wrappedJDBCConnector.init();
			}
			/*otherwise nothing */
		}

		@Override
		public void connect() {
			this.wrappedJDBCConnector.connect();
		}
	}
	
	/*Here we create a proxy to prevent the DBConnector's client to call
	 * init method multiple times
	 */
	public static void main(String[] args) throws InterruptedException
	{
		System.out.println("without proxy");
		DBConnector connector = new JDBCConnector();
		connector.init();
		connector.connect();
		connector.init(); //this should be avoided
		connector.connect();
		
		System.out.println("with proxy");
		DBConnector connectorProxy = new JDBCConnectorProxy();
		connectorProxy.init();
		connectorProxy.connect();
		connectorProxy.init(); //this does nothing because the proxy take care of multiple calls
		connectorProxy.connect();
	}
}
