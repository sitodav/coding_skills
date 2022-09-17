import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class DFS_Iterative {

	
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
		
		@Override
		public String toString() {
			
			return this.label;
			
		}
	}
	
	
	public static void main(String[] args)
	{
		Node root = new Node("1", new Node("2", new Node("4"), new Node("5")), new Node("3"));
		System.out.println("\nIN ORDER----------------------------");
	 
		System.out.println("\nPRE ORDER----------------------------");
		visitPreOrder(root);
		System.out.println("\nPOST ORDER----------------------------");
		visitPostOrder(root);
		 
		
		
	}
	
	private static void visitInOrder(Node root)
	{
		Stack<Node> stack1 = new Stack<>();
		Stack<Node> stack2 = new Stack<>();
		
		stack1.push(root);
		while(!stack1.isEmpty())
		{
			
		}
	}
	
	private static void visitPostOrder(Node root)
	{
		
		Stack<Node> stack1 = new Stack<>();
		Stack<Node> stack2 = new Stack<>();
		
		stack1.push(root);
		while(!stack1.isEmpty())
		{
			Node elm = stack1.pop();
			if(elm.childs.size() > 0)
			{
				stack2.add(elm);
				for(int i = elm.childs.size()-1; i>= 0; i--)
				{
					stack1.push(elm.childs.get(i));
				}
			}
			else
			{
				System.out.print(elm.label);
			}	 
		}
		
		while(!stack2.isEmpty())
		{
			System.out.print(stack2.pop().label);
		}
		
	
		
	}
	
	private static void visitPreOrder(Node root)
	{
		
		Stack<Node> stack1 = new Stack<>();
		stack1.push(root);
		while(!stack1.isEmpty())
		{
			Node elm = stack1.pop();
			System.out.print(elm.label);
			for(int i = elm.childs.size()-1; i>= 0; i--)
			{
				stack1.push(elm.childs.get(i));
			}
		}
		
	
		
	}
}
