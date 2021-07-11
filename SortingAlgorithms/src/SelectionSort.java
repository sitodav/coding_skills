
public class SelectionSort {

	public static void main(String[] args)
	{
		
		int[] a = new int[] {1,44,12,32,2,114,34,99,17};
		selectionSort(a);
		
		for(int i = 0; i< a.length; i++)
		{
			System.out.print(a[i]+",");
		}
		System.out.println();
	}
	
	public static void selectionSort(int[] elms)
	{
		for(int i = 0; i< elms.length - 1; i++)
		{
			int imin = i;
			for(int j = i+1; j<elms.length; j++)
			{
				if( elms[j] < elms[imin] )
					imin = j;
			}
			int t = elms[imin];
			elms[imin] = elms[i];
			elms[i] = t;
		}
	}
}
