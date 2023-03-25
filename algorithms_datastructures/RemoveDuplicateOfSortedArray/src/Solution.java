/*
 * https://practice.geeksforgeeks.org/problems/remove-duplicate-elements-from-sorted-array/1
 * Given a sorted array A[] of size N, delete all the duplicated elements from A[]. Modify the array such that if there are X distinct elements in it then the first X positions of the array should be filled with them in increasing order and return the number of distinct elements in the array.

Note:
1. Don't use set or HashMap to solve the problem.
2. You must return the number of distinct elements(X) in the array, the generated output will print all the elements of the modified array from index 0 to X-1.
 */
public class Solution
{
	public static void main(String[] args)
	{
		int[] a = new int[] {1,2,2,3,4,4,5,6};
		int res= remove_duplicate(a,a.length);
		System.out.println("termn");
	}

	static int remove_duplicate(int A[], int N)
	{
		 
		
		int p = A[0];
		int x = 0;
		int y = 1;
		
		while(y < N)
		{
			while(y < N && A[y] == p )
			{
				y++;
			}
			
			if(x+1 >= N || y >=N)
				break;
			
			A[x+1] = A[y];
			p = A[y];
			x++ ;
			y++;
		}
		
		return x+1;
	}
}
