
public class LongestPalindromeSubstr {
	
	public static void main(String[] args)
	{
		 
		System.out.println(solve("BHBAEFDAABCDEFGHILMNOPQRSTUVZZVUTSRQPONMLIHGFEDCBADOFODAD"));
		
	}
	
	public static void printMemoizMatrix(boolean[][] memoiz)
	{
		for(int i = 0; i< memoiz.length; i++ )
		{
			System.out.println("");
			for(int j = 0; j< memoiz[i].length; j++)
			{
				System.out.print(memoiz[i][j] ? "T" : "F");
			}
		}
		System.out.println("");
	}
	public static int solve(String toCheck)
	{
		int longestSize = 1;
		boolean[][] memoiz = new boolean[toCheck.length()][toCheck.length()];
		
		/*Set to true all substr of size 1 (when i and j are 0 distance ) */
		for(int i = 0; i< toCheck.length(); i++)
			memoiz[i][i] = true;
		/*for all the substrs of size 2 (when i and j are 1 distance 
		 * set to true only if the chars in i, j are equals
		 *And the same value for the reverse j,i
		 */
		for(int i = 0; i< toCheck.length()-1; i++)
		{
			int j = i+1;
			boolean val = false;
			
			if(toCheck.charAt(i) == toCheck.charAt(j))
				val = true;
			
			memoiz[i][j] = val;
			memoiz[j][i] = val;
			
			
		}
		
		/*for the substr of size > 2 (distance varying from 2 to n-1)
		 * the value in the matrix is true only if the chars are equals in i,j
		 * and the matrixs old true in matrix[i+1][j-1] (here we are using the memoization
		 * in a bottom up approach)
		 */
		for(int distance = 2; distance <= toCheck.length()-1; distance++ )
		{
			for(int i = 0; i< toCheck.length(); i++)
			{
				for(int j = i+ distance; j< toCheck.length(); j++)
				{
					boolean value = (toCheck.charAt(i) == toCheck.charAt(j)) && memoiz[i+1][j-1];
					memoiz[i][j] = value;
					memoiz[j][i] = value;
					
					if(value && Math.abs(i-j) > longestSize)
					{
						longestSize = Math.abs(i-j);
					}
				}
			}
		}
		printMemoizMatrix(memoiz);
		
		return longestSize+1;
	}
}
