

/*
 * 
 * The patterns is used when we want to model a chain of actors
 * multiple handlers for a request
 */
public class ChainOfResponsability {

	
	public static class Request
	{
		String requestValue;
		public Request(String value) {this.requestValue = value;}
		@Override
			public String toString() {
				 return requestValue;
			}
	}
	
	public static abstract class Processor 
	{
		Processor next; //chain link
		
		public Processor addNext(Processor next)
		{
			
			this.next = next;
			return next;
		}
		
		public Request processNext(Request req)
		{
			req = this.processRequest(req);
			if(null != this.next)  //if we have next, send the processed (by me) request to it and return its result
				return this.next.processRequest(req);
			return req;  
		}
		
		public abstract Request processRequest(Request req);
	}
	
	public static class Trimmer extends Processor
	{
		 

		@Override
		public Request processRequest(Request req) {
			if(null == req || null == req.requestValue)
				return null;
			
			String value = req.requestValue;
			value = value.trim();
			
			req.requestValue = value;
			return processNext(req);
		}
	}
	
	public static class WhiteSpaceRemover extends Processor
	{

		@Override
		public Request processRequest(Request req) {
			String value = req.requestValue;
			value = value.replace(" ", "");
			
			req.requestValue = value;
			return processNext(req);
		}
		
	}
	
	public static class Replacer extends Processor
	{
		String replaceWhat = null;
		String withWhat = null;
		

		public Replacer(String replaceWhat, String withWhat) {
			this.replaceWhat = replaceWhat;
			this.withWhat = withWhat;
		}



		@Override
		public Request processRequest(Request req) {
			String val = req.requestValue;
			val = val.replace(replaceWhat, withWhat);
			
			req.requestValue = val;
			return processNext(req);
		}
		
	}
	
	
	public static void main(String[] args)
	{
		Request startingRequest = new Request(" my name is bob ");
		System.out.println("initial request: "+startingRequest);
		System.out.println("applying chain of responsability...");

		Processor trimmer = new Trimmer();
		trimmer.addNext(new WhiteSpaceRemover()).addNext(new Replacer("bob","john"));
		
		Request processedReq = trimmer.processRequest(startingRequest);
		System.out.println("final request: "+processedReq);
	}
	
}
