import java.util.HashMap;
import java.util.Map;

/*
 * https://practice.geeksforgeeks.org/problems/count-distinct-pairs-with-difference-k1233/1
 * 
 * Given an integer array and a non-negative integer k, count all distinct pairs with difference equal to k, i.e., A[ i ] - A[ j ] = k
 */
public class Solution
{

	public static void main(String[] args)
	{
		int[] array = new int[]
		{ 11, 6, 10, 5, 11, 16 };
		System.out.println(TotalPairs(array, array.length));
	}

	public static int TotalPairs(int[] nums, int k)
	{
		java.util.Arrays.sort(nums);
		Map<String, Boolean> couplings = new HashMap<>();

		int counts = 0;
		for (int i = 0; i < nums.length; i++)
		{
			for (int j = i + 1; j < nums.length; j++)
			{
				if (nums[j] - nums[i] == k && couplings.get(nums[j] + "," + nums[i]) == null)
				{
					counts++;
					couplings.put(nums[j] + "," + nums[i], true);
				}
				else if (couplings.get(nums[j] + "," + nums[i]) != null || nums[j] - nums[i] > k)
					break;
			}
		}

		return counts;
	}

}
