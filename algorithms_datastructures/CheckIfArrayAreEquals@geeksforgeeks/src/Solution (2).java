import java.util.HashMap;
import java.util.Map;

/*
 * https://practice.geeksforgeeks.org/problems/check-if-two-arrays-are-equal-or-not3847/1
 * 
 * Given two arrays A and B of equal size N, the task is to find if given arrays are equal or not. Two arrays are said to be equal if both of them contain same set of elements, arrangements (or permutation) of elements may be different though.
   Note : If there are repetitions, then counts of repeated elements must also be same for two array to be equal.

 */
class Solution{
	public static void main(String[] args)
	{
		long[] a = new long[] {30,29,12,41,30,29,44,12,18};
		long[] b = new long[] {18,12,12,44,29,29,30,41,30};
		System.out.println(check(a,b,a.length));
	}
	
	
    //Function to check if two arrays are equal or not.
    public static boolean check(long A[],long B[],int N)
    {
        Map<Long,Integer> counts = new HashMap<>();
        for(long t : A)
        {
            if(counts.get(Long.valueOf(t)) == null)
            {
                counts.put(Long.valueOf(t), 1);
            }
            else
            {
                counts.put(Long.valueOf(t),counts.get(Long.valueOf(t))+1);
            }
        }
        
        for(long t : B)
        {
            if(counts.get(Long.valueOf(t)) == null)
            {
                return false;
            }
            else
            {
                int countT = counts.get(Long.valueOf(t));
                countT--;
                if(countT == 0)
                    counts.remove(Long.valueOf(t));
                else
                    counts.put(Long.valueOf(t),countT);
            }
        }
        if(counts.isEmpty())
            return true;
        else
            return false;
        
    }
}