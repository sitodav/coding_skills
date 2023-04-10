import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * https://practice.geeksforgeeks.org/problems/insert-in-a-sorted-list/1
 * 
 * 
Given a linked list sorted in ascending order and an integer called data, insert data in the linked list such that the list remains sorted.

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
		Node root = Node.generateLinkedList(new Integer[]{ 1, 6, 18, 22, 66 }); 
	 
		root.printFromMe();
		sortedInsert(root, 12);
		root.printFromMe();

	}

 
	static  Node sortedInsert(Node head1, int key) {
        Node phead = head1;
       
        if(key <= phead.data)
        {
            Node n = new Node(key);
            n.next = head1;
            head1 = n;
            return head1;
        }
        while(phead != null   )
        {
            if(phead.next != null && key <=phead.next.data)
            {
                Node n = new Node(key);
                n.next = phead.next;
                phead.next = n;
                break;
            }
            else if(phead.next == null)
            {
                Node n = new Node(key);
                phead.next = n;
                break;
            }
            phead = phead.next;
             
        }
        
        return head1;
       
    }
}
