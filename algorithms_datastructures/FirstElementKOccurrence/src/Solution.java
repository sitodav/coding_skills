import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
 * https://practice.geeksforgeeks.org/problems/first-element-to-occur-k-times5150/1
 */
public class Solution
{
	public static void main(String[] args)
	{

	}

	public int firstElementKTime(int[] a, int n, int k)
	{
		 
		Map<Integer, Integer> count = new HashMap<>();

		for (int el : a)
		{
			if (count.get(Integer.valueOf(el)) == null)
			{
				if (k == 1)
					return el;
				count.put(Integer.valueOf(el), 1);

			}
			else
			{
				int countT = count.get(Integer.valueOf(el));
				countT++;
				if (countT == k)
					return el;
				else
					count.put(Integer.valueOf(el), countT);
			}
		}

		return -1;
	}
}
