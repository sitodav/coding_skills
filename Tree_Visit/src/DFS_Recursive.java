import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DFS_Recursive {

	
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
		
		public void visitInOrder()
		{

			int midSize = (int) (0.5 * childs.size());
			for (int i = 0; i < midSize; i++) {
				this.childs.get(i).visitInOrder();
			}
			
			System.out.print(this.label);
			
			for (int i = midSize; i < childs.size(); i++) {
				this.childs.get(i).visitInOrder();
			}
			
		}
		
		public void visitPreOrder()
		{
			System.out.print(this.label);
			for (int i = 0; i < childs.size(); i++) {
				this.childs.get(i).visitPreOrder();
			}
		}
		
		public void visitPostOrder()
		{
			for (int i = 0; i < childs.size(); i++) {
				this.childs.get(i).visitPostOrder();
			}
			System.out.print(this.label);
		}
	}
	
	
	public static void main(String[] args)
	{
		Node root = new Node("1", new Node("2", new Node("4"), new Node("5")), new Node("3"));
		System.out.println("\nIN ORDER----------------------------");
		root.visitInOrder();
		System.out.println("\nPRE ORDER----------------------------");
		root.visitPreOrder();
		System.out.println("\nPOST ORDER----------------------------");
		root.visitPostOrder();
		
	}
}
