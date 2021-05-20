import java.util.List;

/*
 * 
 * https://www.algoexpert.io/questions/Validate%20Subsequence
 * 
 * 
 */
class Program {
  public static boolean isValidSubsequence(List<Integer> array, List<Integer> sequence) {
   

		int ia = 0;
		int is = 0;
		boolean keepSearch = false;
		
		
		do
		{
			keepSearch = false;
			while (ia < array.size() && !(keepSearch=sequence.get(is).equals(array.get(ia)))     ) {
				ia++;
			}
			ia++;
			is++;
		}
		while(keepSearch && is < sequence.size() && ia < array.size());
			
		 

		return keepSearch && is >= sequence.size();
  }
}
