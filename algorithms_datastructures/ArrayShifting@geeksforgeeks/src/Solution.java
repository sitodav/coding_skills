/*
 * https://practice.geeksforgeeks.org/problems/rotate-array-by-n-elements-1587115621/1
 */
/*D-circular shift on the left, with O(n) complexity and in place */
public class Solution
{
	public static void main(String[] args)
	{
		int[] a = new int[] {1,2,3, 4,5,6,7,8,9};
//		invert(a, 3,a.length-1 );
		shift(a,3);
		System.out.println(a);
	}
	
	/*
	 * step 1: invert array from 0 to (d%n-1) inclusive
	 * step 2 : invert array from d%n, to n-1 inclusive
	 * step 3 : invert everything
	 */
	public static void shift(int[] a , int d)
	{
		int n = a.length;
		int k = d%n;
		invert(a,0,k-1);
		invert(a,k,n-1);
		invert(a,0,n-1);
		
	}
	
	/*invert array a , from index start to end inclusive */
	public static void invert(int[] a, int start, int end )
	{
		int n = end-start+1;
		for(int i = 0; i< (int)(0.5*(n)); i++)
		{
			int t = a[start+i];
			a[start+i] = a[end-i];
			a[end-i] = t;
		}
	}
	
}
