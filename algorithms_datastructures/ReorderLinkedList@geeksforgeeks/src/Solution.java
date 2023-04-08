import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * https://practice.geeksforgeeks.org/problems/reorder-list/1
 * 
 * 
 * Given a singly linked list: A0→A1→...→An-2→An-1, reorder it to: A0→An-1→A1→An-2→A2→An-3→...
For example: Given 1->2->3->4->5 its reorder is 1->5->2->4->3.

 */
public class Solution
{

	static class Node
	{
		Node next;
		int data;

		Node(int d)
		{
			data = d;
			next = null;
		}

		public Node addNext(Node node)
		{
			this.next = node;
			return node;
		}

		public Node addNodes(Integer[] datas)
		{
			Node phead = this;
			for (int data : datas)
			{
				Node newNode = new Node(data);
				phead = phead.addNext(newNode);
			}
			return this;
		}

		public void printFromMe()
		{

			System.out.println(this.data);
			if (this.next != null)
				this.next.printFromMe();
		}

		public static Node generateLinkedList(Integer[] datas)
		{
			Node root = new Node(datas[0]);
			List<Integer> tl = new ArrayList<>(Arrays.asList(datas));
			tl.remove(0);
			datas = tl.toArray(new Integer[tl.size()]);
			root.addNodes(datas);
			return root;
		}

	}

	public static void main(String[] args)
	{
		Node root = Node.generateLinkedList(new Integer[]{ 1, 2, 3, 4, 5 }); 
	 
		root.printFromMe();
		reorderlist(root);
		root.printFromMe();

	}

 
	static void reorderlist(Node head)
	{
		
		Node phead = head;
		List<Node> nodes = new ArrayList<Node>();
		while(phead != null)
		{
			nodes.add(phead);
			phead = phead.next;
			
		}
		
		Node a = null;
		Node b = null;
		int originalSize = nodes.size();
		while(nodes.size() > 0)
		{
			a = nodes.remove(0);
			if(nodes.size() > 0)
			{
				b = nodes.remove(nodes.size() -1);
				Node t = a.next;
				a.next = b;
				b.next = t;
			}
			else //last element, odd number of nodes, remove next to avoid loop
			{
				a.next = null; 
			}
			 
		}
		
		if(originalSize% 2 == 0) //remove last loop
		{
			b.next = null;
		}
		
		
		
		
		
		
	}
}
