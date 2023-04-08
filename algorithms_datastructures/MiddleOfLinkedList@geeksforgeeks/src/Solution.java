
 

 /*
  *  https://practice.geeksforgeeks.org/problems/finding-middle-element-in-a-linked-list/1
  *  
  *  Given a singly linked list of N nodes.
The task is to find the middle of the linked list. For example, if the linked list is
1-> 2->3->4->5, then the middle node of the list is 3.
If there are two middle nodes(in case, when N is even), print the second middle element.
For example, if the linked list given is 1->2->3->4->5->6, then the middle node of the list is 4.
  */

class Solution
{
	
	 static class Node {
		   int data;
		    Node next;
		    Node(int d)  { data = d;  next = null; }
		}
	 
    int getMiddle(Node head)
    {
         Node start = head;
         int tot = 0;
         while(head != null)
         {
             tot++;
             head = head.next;
         }
         
         int middle = (int)(Math.ceil(tot* 0.5));
         if(tot % 2 == 0)
            middle++;
         int i = 1;
         head = start;
         while(i < middle)
         {
             i++;
             head = head.next;
         }
         return head.data;
    }
}