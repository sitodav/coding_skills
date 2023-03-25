
/*Count of smaller (or equals) element of x , in the arr
 * using O(logn) complexity
 */
public class Solution
{

	public static void main(String[] args)
	{
		long[] arr = new long[] {0,1,2,3,4,5,6,7,8,9};
		long res =  countOfElements(arr, arr.length, 5);
		 
	}
	
	public static long countOfElements(long arr[], long n, long x)
    {
         
        return divideEtImperaSearch(arr, 0, n-1, x) +1;
         
    }
	
	public static long divideEtImperaSearch(long arr[], long a,long b, long key)
	{
		 
		
		long m = (long)((b+a)*0.5);
		 
		if(arr[(int)m] == key)
			return m;
		else if(b == a)
		{
			//not found
			if(arr[(int)m] < key)
				return m;
			else
				return m-1;
		}
		else if(arr[(int)m] < key)
		{
			return divideEtImperaSearch(arr,m+1,b,key);
		}
		else
			return divideEtImperaSearch(arr,a,m-1,key);
		
		 
	}
}
