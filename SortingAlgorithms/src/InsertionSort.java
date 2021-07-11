
public class InsertionSort {
	
	public static void main(String[] args)
	{
		
		int[] a = new int[] {1,44,12,32,2,114,34,99,17};
		insertionSort(a);
		
		for(int i = 0; i< a.length; i++)
		{
			System.out.print(a[i]+",");
		}
		System.out.println();
	}
	
	public static void insertionSort(int[] elms)
	{
		
		for(int i = 1; i< elms.length; i++)
		{
			int j = i-1;
			int t = elms[i];
			while(j>= 0 && elms[j] > t)
			{
				elms[j+1] = elms[j];
				j--;
			}
			 
			elms[j+1] = t;
			 
		}
		
	}
}
