/*
 * 
 * Given an array of integer , we want to find to elements Ai , Aj
 * where i<j, that gives the max gain where the gain is Aj-Ai
 * Output the best gain
 * Time Complexity : O(n)
 * 
 * (interview at Janea Systems)
 */
public class Solution
{

	public static void main(String[] args)
	{
		int[] array = new int[] {10,2,4,5,15,1,3};
//		int[] array = new int[] {1,1,1,1,1};
		int result = maxGain(array);
		System.out.println(result);
	}
	
	public static int maxGain(int[] array)
	{
		int maxGain = 0;
		int min = array[0];
		int imin = 0;
		for(int i = 1; i< array.length; i++)
		{
			if(array[i]< min)
			{
				imin = i;
				min = array[i];
			}
			
			if(imin != i)
			{
				if(array[i] - array[imin] > maxGain)
				{
					maxGain = array[i]-array[imin];
				}
			}
		}
		
		return maxGain;
	}
}
