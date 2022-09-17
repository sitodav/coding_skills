
/*
 * 
 * https://leetcode.com/problems/squares-of-a-sorted-array/
 * 
 * Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.

 

Example 1:

Input: nums = [-4,-1,0,3,10]
Output: [0,1,9,16,100]
Explanation: After squaring, the array becomes [16,1,0,9,100].
After sorting, it becomes [0,1,9,16,100].
Example 2:

Input: nums = [-7,-3,2,3,11]
Output: [4,9,9,49,121]
 

Constraints:

1 <= nums.length <= 104
-104 <= nums[i] <= 104
nums is sorted in non-decreasing order.
 

 SQUARING EACH ELEMENT AND SORTING THE NEW ARRAY IS VERY TRIVIAL, COULD YOU FIND AN O(N) SOLUTION USING A DIFFERENT APPROACH?
 */
public class Solution {

	public static void main(String[] args) {
		
		int[] sorted = sortedSquares(new int[] {-7,-3,2,3,11});
		System.out.println("terminato");
	}

	public static int[] sortedSquares(int[] nums) {

		int[] res = new int[nums.length];
		
		/*once we find the part of the array which contains elems <= 0,
		 * then it's just a merge of two sorted arrays between that subarray (reversed) and the
		 * subarray containing elems > 0
		 */
		int end_firstpart = 0;
		while(end_firstpart < nums.length && nums[end_firstpart] <= 0) {
			end_firstpart++;
		}
		/*the merge of two sorted arrays is going backward on the first subpart 
		 * 
		 */
		int ia = end_firstpart-1;
		int ib = end_firstpart;
		int targetidx = 0;
		while(ia >= 0 && ib < nums.length)
		{
			 
				if( Math.abs(nums[ia]) < Math.abs(nums[ib]) )
				{
					res[targetidx++] = nums[ia]*=nums[ia];
					ia--;
				}
				else
				{
					res[targetidx++] = nums[ib]*=nums[ib];
					ib++;
				}
			  
		}
		while(ia >= 0)
		{
			res[targetidx++] =  nums[ia]*=nums[ia];
			ia--;
		}
	 
		while( ib < nums.length)
		{
			res[targetidx++] = nums[ib]*=nums[ib];
			ib++;
		}
		
		return res;
	}
	
	 
}
