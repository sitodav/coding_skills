import java.math.BigDecimal;

/*
 * 
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

 

Example 1:


Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.
 */
public class Solution {

	static public class ListNode {
		int val;
		ListNode next;

		ListNode() {
		}

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}

	
	
	/*Solution....
	 * 
	 */
	
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		BigDecimal tl1 = new BigDecimal(accumula(l1));
		BigDecimal tl2 = new BigDecimal(accumula(l2));

		return buildReverse(tl1.add(tl2).toString());
	}

	private String accumula(ListNode node) {
		return (null != node.next ? accumula(node.next) : "") + "" + node.val;
	}

	private ListNode buildReverse(String remainingValues) {
		if (remainingValues.length() > 0) {
			int lastIdx = remainingValues.length() - 1;
			return new ListNode(Integer.parseInt("" + remainingValues.charAt(lastIdx)),
					buildReverse(remainingValues.substring(0, lastIdx)));
		}
		return null;

	}

}
