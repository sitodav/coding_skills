
public class Solution2
{
	public static void main(String[] args)
	{
		ListNode head1 = new ListNode("2");
		head1.addNode("4").addNode("3");
		
		ListNode head2 = new ListNode("5");
		head2.addNode("6").addNode("4");
		
		ListNode headResult = addTwoNumbers(head1,head2);
		System.out.println("terminato");
	}
	
	public static class ListNode
	{
		private String digit;
		private ListNode next;
		public ListNode() {}
		public ListNode(String digit) {
			this.digit = digit;
		}
		
		public ListNode addNode(String digit)
		{
			this.next = new ListNode(digit);
			return this.next;
		}
		
		public static void printList(ListNode head)
		{
			ListNode pnode = head;
			while(null != pnode)
			{
				System.out.print(pnode.digit+" ");
				pnode = pnode.next;
			}
		}
		
		 
	}
	
	 public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	 
		 String reversedDigits1 = cumulative(l1);
		 String reversedDigits2 = cumulative(l2);
		 Long sum = Long.parseLong(reversedDigits1)+Long.parseLong(reversedDigits2);
		 String sumString = ""+sum;
		 ListNode toRet = buildList(sumString.split(""));
		 return toRet;
		 
	}
	
	public static ListNode buildList(String[] digits)
	{
		ListNode originalNewHead = new ListNode(digits[digits.length-1]);
		ListNode addHere = originalNewHead;
		for(int i = digits.length-2; i>=0 ; i--)
		{
			addHere = addHere.addNode(digits[i]);
		}
		return originalNewHead;
	}
	 
	public static String cumulative(ListNode node)
	{
		if(node.next == null)
			return node.digit;
		else return cumulative(node.next)+node.digit;
	}
}
