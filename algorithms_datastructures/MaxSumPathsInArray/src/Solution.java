import java.util.HashMap;
import java.util.Map;

/*
 * https://practice.geeksforgeeks.org/problems/max-sum-path-in-two-arrays/1
 * 
 * Given two sorted arrays A and B of size M and N respectively. Each array may have some elements in common with the other array. Find the maximum sum of a path from the beginning of any array to the end of any of the two arrays. We can switch from one array to another array only at the common elements.Both the arrays are sorted.
   Note: Only one repeated value is considered in the valid path sum.
   
   Expected Time Complexity: O(M + N)
   Expected Auxiliary Space: O(1)
 */
public class Solution
{
	
	public static void main(String[] args)
	{
//		int[] a = new int[] {2,3,7,10,12};
//		int[] b = new int[] {1,5,7,8};
		
//		int[] a = new int[] {1,2,3};
//		int[] b = new int[] {3,4,5};
		
//		int[] a = new int[] { 10,12};
//		int[] b = new int[] { 5,7,9};
		
		int[] a = new int[] { 2,3,7,10,12,15,30,34};
		int[] b = new int[] { 1,5,7,8,10,15,16,19};
		
		int res = maxPathSum(a,b);
		System.out.println(res);
		
	}
	
	
	static int maxPathSum(int ar1[], int ar2[])
    {
        int totSum = 0;
        int sumA = 0, sumB = 0, iA = ar1.length-1, iB = ar2.length-1;
        
        
        while(iA >= 0 && iB >= 0)
        {
        	if(ar1[iA] > ar2[iB])
        	{
        		sumA+= ar1[iA--];
        	}
        	else if(ar2[iB] > ar1[iA])
        	{
        		sumB+= ar2[iB--];
        	}
        	else
        	{
        		totSum = Math.max(sumA, sumB) + ar1[iA] + totSum;
        		iA--;
        		iB--;
        		sumA = 0;
        		sumB = 0;
        	}
        }
        
        while(iA >= 0)
        {
        	sumA+= ar1[iA--];
        }
        
        while(iB >= 0)
        {
        	sumB+= ar2[iB--];
        }
        
        totSum = Math.max(sumA,sumB) + totSum;
        
        return totSum;
    }
}
