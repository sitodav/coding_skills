import java.util.HashMap;

/*
 * problem https://leetcode.com/problems/two-sum/
 * 
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.

 

Example 1:

Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Output: Because nums[0] + nums[1] == 9, we return [0, 1].

 */
class Main {
    public int[] twoSum(int[] nums, int target) {
        int[] toRet = new int[2];
        HashMap<Integer,Integer> mapt = new HashMap<Integer,Integer>();
        
        for(int i = 0; i< nums.length; i++)
        {
            Integer t = Integer.valueOf(target-nums[i]);
            if(mapt.get(t) != null)
            {
                toRet[0] = mapt.get(t).intValue();
                toRet[1] = i;
            }
            else
                mapt.put(nums[i],i);
        }
        
        return toRet;
    }
}