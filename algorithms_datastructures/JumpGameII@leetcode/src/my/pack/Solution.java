package my.pack;

/*
 * https://leetcode.com/problems/jump-game-ii/description/
You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].

Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at nums[i], you can jump to any nums[i + j] where:

0 <= j <= nums[i] and
i + j < n
Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can reach nums[n - 1].

 

Example 1:

Input: nums = [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * 
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution
{

	public static void main(String[] args)
	{
		int[] nums = new int[] { 2,3,1,1,4};
		int res = solution(nums);
		System.out.println(res);
	}

	public static int solution(int[] nums)
	{



		if (nums.length > 1)
		{
			/*use arraylist as queue to implement BFS-search iteratively */
			List<Integer> bfsQueue = new ArrayList<>(); 
			/*keep track of number of jumps used to arrive in i-th element, this is accessed like the queue*/
			List<Integer> previousJumpsQueue = new ArrayList<>();
			/*use a map for the "memoization" of already resolved index. When an index is resolved the first time, it mean that we
			 * arrived on it with the minimum number of jumps
			 */
			Map<Integer, Boolean> memoization = new HashMap<>();
			/*initializing with 0-th element the queues*/
			bfsQueue.add(0);
			previousJumpsQueue.add(0);

			while (bfsQueue.size() > 0)
			{
				Integer poppedElementIdx = bfsQueue.remove(0); 
				Integer previousJumpsForPoppedElementIdx = previousJumpsQueue.remove(0);
				Integer jumpValueForPoppedElement = nums[poppedElementIdx];

				for (int i = 1; i <= jumpValueForPoppedElement; i++)
				{
					if (poppedElementIdx + i == nums.length - 1) /*solution found */
					{
						return previousJumpsForPoppedElementIdx + 1;
					}
					if (!memoization.containsKey(poppedElementIdx + i) && poppedElementIdx + i < nums.length)
					{
						bfsQueue.add(poppedElementIdx + i);
						previousJumpsQueue.add(previousJumpsForPoppedElementIdx + 1);
						memoization.put(poppedElementIdx + i, true); /*update memoization */
					} 
				}
			}
		}

		return 0;
	
	}

}