import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * https://practice.geeksforgeeks.org/problems/remove-every-kth-node/1
 * 
 * 
 * Given a singly linked list, your task is to remove every kth node from the linked list.
 * 
 */
public class Solution
{	
	
	static class Node {
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
		
		public  Node addNodes(Integer[] datas)
		{
			Node phead = this;
			for(int data : datas)
			{
				Node newNode = new Node(data);
				phead = phead.addNext(newNode);
			}
			return this;
		}
		
		public void printFromMe()
		{
			
			System.out.println(this.data);
			if(this.next != null)
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
		Node root = Node.generateLinkedList(new Integer[] {1, 2, 3, 4, 5, 6, 7,8});
//		root.printFromMe();
		new GfG().delete(root, 2);
		root.printFromMe();
		
	}
	
	/*This is the solution snippet for geeksforgeeks*/
	static class GfG
	{
	    /*You are required to complete this method*/
	    Node delete(Node head, int k)
	    {
	     
	    	
		    Node phead = head;
		    int tot = 1;
		    while( phead != null && phead.next != null)
		    {
		         
		        if(tot == k-1)
		        {   
		            phead.next = phead.next.next;
		            tot = 1;
		        }
		        else
		        {
		            tot++;
		        }
		         
		        phead = phead.next;
		         
		    }
		    
		    
		    return head;
	    }
	}
}
