
/*
 * 	 Problem at https://practice.geeksforgeeks.org/problems/maximum-index-1587115620/1
 *   Given an array A[] of N positive integers. The task is to find the maximum of j - i subjected to the constraint of A[i] < A[j] and i < j.
 * 
 * 
 *   This solution is not linear, but O(n^2)
 */
public class Solution
{

	public static void main(String[] args)
	{
//		int[] arr = new int[] {34, 8, 10, 3, 2, 80, 30, 33, 1};
//		int[] arr = new int[] {82 ,63 ,44, 74 ,82 ,99 ,82};
		int[] arr = new int[] {46, 59 ,39 ,26 ,19 ,13};
		int res = maxIndexDiff(arr,arr.length);
		System.out.println("result : "+res);
	}
	
	 
	static int maxIndexDiff(int A[], int N)
	{

		 int longest = 0;
		 int[] t = new int[N];
		 int[] t2 = new int[N];
		 t[0] = 0;
		 t[N-1] = N-1; 
		 t2[0] = 0;
		 t2[N-1] = N-1; 
		 
		 for(int i = 1; i< N; i++)
		 {
			 if(A[i] < A[t[i-1]])
			 {
				 t[i] = i;
			 }
			 else
			 {
				 t[i] = t[i-1];
			 }
		 }
		 
		 
		 for(int i = N-2; i>=0; i--)
		 {
			 if(A[i] > A[t2[i+1]])
			 {
				 t2[i] = i;
			 }
			 else
			 {
				 t2[i] = t2[i+1];
			 }
		 }
		 
		 outer: for(int i = 0; i<N; i++)
		 {
			 for(int j = N-1; j>=i; j--)
			 {
				 if(A[t[i]]<=A[t2[j]] && j-i > longest)
				 {
					 longest = j-i;
					 break;
				 }
			 }
		 }
		 
		 return longest;

	}
}
