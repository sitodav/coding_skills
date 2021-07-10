import java.util.HashMap;
import java.util.Map;

public class MemoizationFibonacci {

	static Map<Integer,Long> memoiz = new HashMap<>();
	
	public static void main(String[] args)
	{
		long start = System.currentTimeMillis();
		for(int i = 0; i< 47; i++)
			System.out.print(fibonacciRecursiveDummy(i)+",");
		System.out.println("\nFibonacci dummy no memoization, time : " + (System.currentTimeMillis() - start)+"ms");
		
		start = System.currentTimeMillis();
		for(int i = 0; i< 47; i++)
			System.out.print(fibonacciRecursiveMemoization(i)+",");
		System.out.println("\nFibonacci with memoization, time : " + (System.currentTimeMillis() - start)+"ms");
		
	}
	
	public static long fibonacciRecursiveMemoization(int n )
	{
		long res = -1;
		
		if(memoiz.containsKey(Integer.valueOf(n)))
		{
			res = memoiz.get(Integer.valueOf(n));
		}
		else
		{
			if(n == 0)
				res = 0;
			else if(n == 1)
				res = 1;
			else 
				res = fibonacciRecursiveMemoization(n-1)+fibonacciRecursiveMemoization(n-2);
			
			memoiz.put(Integer.valueOf(n),res);
		}
		
		 
		return res;
	}
	
	
	public static int fibonacciRecursiveDummy(int n )
	{
		int res = -1;
		
		if(n == 0)
			res = 0;
		else if(n == 1)
			res = 1;
		else 
			res = fibonacciRecursiveDummy(n-1)+fibonacciRecursiveDummy(n-2);
		
		 
		return res;
	}
}
