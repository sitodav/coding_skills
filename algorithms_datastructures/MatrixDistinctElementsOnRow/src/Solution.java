import java.util.HashMap;
import java.util.Map;

/*
 * https://practice.geeksforgeeks.org/problems/find-distinct-elements2054/1
 */
public class Solution
{

	public static void main(String[] args)
	{
		//
	}
	
	static int distinct(int M[][], int N)
	{
		Map<Integer, Boolean> t = new HashMap<>();
		for (int i = 0; i < M[0].length; i++)
		{
			t.put(M[0][i], Boolean.valueOf(true));
		}

		for (int i = 1; i < M.length; i++)
		{
			for (Integer k : t.keySet())
			{
				t.put(k, Boolean.valueOf(false));
			}

			for (int j = 0; j < M[i].length; j++)
			{
				if (t.get(M[i][j]) != null)
				{
					t.put(Integer.valueOf(M[i][j]), Boolean.valueOf(true));
				}
			}

			Map<Integer, Boolean> t2 = new HashMap<>();
			for (Integer k : t.keySet())
			{
				if (t.get(k) == true)
				{
					t2.put(k, t.get(k));
				}
			}

			t = t2;
		}

		return t.keySet().size();

	}
}
