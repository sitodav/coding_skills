import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * https://practice.geeksforgeeks.org/problems/rearrange-a-linked-list/1
 * 
 * 
 * Given a singly linked list, the task is to rearrange it in a way that all odd position nodes are together and all even positions node are together.
Assume the first element to be at position 1 followed by second element at position 2 and so on.
Note: You should place all odd positioned nodes first and then the even positioned ones. (considering 1 based indexing). Also, the relative order of odd positioned nodes and even positioned nodes should be maintained.
 * 
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
		Node root = Node.generateLinkedList(new Integer[]{ 1, 2, 3, 4, 5, 6, 7, 8 }); 
	
		//root.printFromMe();
		rearrangeEvenOdd(root);
		root.printFromMe();

	}

	// Should rearrange given linked list such that all even
	// positioned Nodes are before odd positioned.
	// Returns nothing
	static void rearrangeEvenOdd(Node head)
	{
		int count = 0;
		Node phead = head;
		Node lastPodd = null;
		Node lastPeven = null;
		Node firstPeven = null;
 
		while(phead != null)
		{
			count++;
//			System.out.println(phead.data+":"+count);
			if(count % 2 != 0) //odd
			{
				if(lastPodd != null) //not first odd
				{
					
					lastPodd.next = phead;
				} 
				
				lastPodd = phead;
			}
			else
			{
				if(lastPeven !=  null)
				{
					lastPeven.next = phead;
				}
				else
				{
					firstPeven = phead;
				}
				lastPeven = phead;
			}
			phead = phead.next;
		}
		
		lastPodd.next = firstPeven;
	}
}
