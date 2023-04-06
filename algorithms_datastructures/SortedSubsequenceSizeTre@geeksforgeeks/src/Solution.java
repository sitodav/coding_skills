import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * https://practice.geeksforgeeks.org/problems/sorted-subsequence-of-size-3/1
 * Given an array A of N integers, find any 3 elements in it such that A[i] < A[j] < A[k] and i < j < k.
 * 
 * 
 * 
 */
public class Solution
{
	public static void main(String[] args)
	{
		ArrayList<Integer> a = new ArrayList<>(Arrays.asList(1,1,3));
		List<Integer> res = find3Numbers(a,a.size());
		System.out.println("terminato");
	}
	
	
	/*we want to find an element that has at least a smaller element on the left
	 * and a bigger element on the right
	 * We don't want just any smaller element on the left, but we want the smallest element on the left
	 * and the biggest element on the right
	 * 
	 * So we create an array, smaller, that contains at index j the index on the original array
	 * of the smallest element on the left of j, otherwise -1
	 * And we create an array, bigger, that contains at index j the index on the original array
	 * of the biggest element on the right of j, otherwise -1
	 * The solution is the element on the original index where the smaller and bigger array have != -1
	 *
	 */
	static ArrayList<Integer> find3Numbers(ArrayList<Integer> arr, int n) {
        ArrayList<Integer> res = new ArrayList<>();
       
        int[] smallers = new int[n];
        int[] biggers= new int[n];
        int minIdxFromLeft = 0, maxIdxFromRight = n-1;
        smallers[0] = -1; //first element have always no smaller element on the left
        biggers[n-1] = -1; //last element have always no bigger element on the right
        
        //for the smaller from left
        for(int i = 1; i< n; i++)
        {
        	if(arr.get(i) <= arr.get(minIdxFromLeft))
        	{
        		//the element at i have no smaller element on the left
        		//and it is the new smallest element
        		smallers[i] = -1;
        		minIdxFromLeft = i;
        	}
        	else
        	{
        		//element at i has a smaller element at index min
        		smallers[i] = minIdxFromLeft;
        	}
        }
        
        //for the bigger from right
        for(int i = n-2; i>= 0; i--)
        {
        	if(arr.get(i) >= arr.get(maxIdxFromRight))
        	{
        		//the element has no bigger from right
        		//and it is the new biggest
        		maxIdxFromRight = i;
        		biggers[i] = -1;
        	}
        	else
        	{
        		//the element has bigger from right
        		biggers[i] = maxIdxFromRight;
        	}
        }
        
        //find the solution
        for(int i = 0; i< n; i++)
        {
        	if(biggers[i] != -1 && smallers[i] != -1)
        	{
        		res.add(arr.get(smallers[i]));
        		res.add(arr.get(i));
        		res.add(arr.get(biggers[i]));
        		break;
        	}
        }
        
        return res;
    }
}
