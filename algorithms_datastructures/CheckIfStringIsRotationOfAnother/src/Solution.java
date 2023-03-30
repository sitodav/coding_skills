
/*
 * https://practice.geeksforgeeks.org/problems/check-if-strings-are-rotations-of-each-other-or-not-1587115620/1
 */
public class Solution
{
	public static void main(String[] args)
	{

	}
	
	/*To check if a string is rotation of another just 
	 * check if they have same size, otherwise they are not
	 * if they have same size, concatenate one string to itself, and if the second one is completely contained in the first, it's a rotation
	 */
	public static boolean areRotations(String s1, String s2)
	{
		s1 = s1+s1;
		if(s1.contains(s2))
			return true;
		return false;
	}
}
