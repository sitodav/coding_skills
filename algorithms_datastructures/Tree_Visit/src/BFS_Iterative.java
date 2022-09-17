import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BFS_Iterative {

	
	static class Node
	{
		String label;
		List<Node> childs = new ArrayList<>();
		public Node(String label) 
		{
			this.label = label;
		}
		
		public Node(String label, Node ...childs) 
		{
			this(label);
			this.childs.addAll(Arrays.asList(childs));
		}
		
		 
	}
	
	
	public static void main(String[] args)
	{
		Node root = new Node("1", new Node("2", new Node("4"), new Node("5")), new Node("3"));
		System.out.println("\nBFS------------");
		visitBFS(root);
	}
	
	public static void visitBFS(Node root)
	{
		List<Node> queue = new ArrayList<>();
		queue.add(root);
		while(!queue.isEmpty())
		{
			Node elm = queue.remove(0);
			System.out.print(elm.label);
			for(Node child : elm.childs)
				queue.add(child);
		}
	}
}
