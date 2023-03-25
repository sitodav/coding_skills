import java.util.ArrayList;
import java.util.List;

/*
 * https://practice.geeksforgeeks.org/problems/leaders-in-an-array-1587115620/1
 */
public class Solution
{
	public static void main(String[] args)
	{
		int[] a = new int[] {16,17,4,3,5,2};
		List<Integer> res = leaders(a,a.length);
		System.out.println("terminato");
	}

	 static ArrayList<Integer> leaders(int arr[], int n){
	       ArrayList<Integer> leaders = new ArrayList<>();
			
			leaders.add(0,arr[n-1]);
			 
			int j = n-2;
			while(j >= 0)
			{
				if(arr[j] >= leaders.get(0))
				{
				 
					leaders.add(0,arr[j]);
				}
				j--;
			}
			
			return leaders;
	    }
}
