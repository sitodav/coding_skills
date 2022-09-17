

public class ReverseLinkedList {

	static class Node
	{
		String label;
		Node pnext;
		
		public Node(String label) 
		{
			this.label = label;
		}
		public Node(String label, Node next)
		{
			this(label);
			this.pnext = next;
		}
		public Node addNext(String label)
		{
			this.pnext =  new Node(label);
			return this.pnext;
		}
	}
	
	public static void printLinkedList(Node root)
	{
		System.out.println("");
		Node sentry = root;
		do
		{
			System.out.print(sentry.label+"->");
			sentry = sentry.pnext;
		}
		while(sentry != null);
	}
	
	
	/*Algorithm to reverse ***********************************************************/
	public static Node reverseLinkedList(Node root)
	{
		Node prev= null, actual = root, next = null;
		while(actual != null)
		{
			next = actual.pnext;
			actual.pnext = prev;
			prev = actual;
			actual = next;
		}
		return prev;
	}
	
	public static void main(String[] args)
	{
		Node root = new Node("A");
		root.addNext("B").addNext("C").addNext("D").addNext("E");
		printLinkedList(root);
		root = reverseLinkedList(root);
		printLinkedList(root);
		
	}
}
