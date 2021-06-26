import java.util.Arrays;

/*
 * 
 * https://leetcode.com/problems/zigzag-conversion/
 * 
 * Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.

 
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:

string convert(string s, int numRows);
 

Example 1:

Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"
Example 2:

Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:
P     I    N
A   L S  I G
Y A   H R
P     I
 */

public class Solution {

	public static void main(String[] args) {
	 
		String res = convert("PAYPALISHIRING",3);
		System.out.println(res);
	}

	public static String convert(String s, int numRows) 
	{
		
		if(s.length() <= numRows || numRows <2)
			return s;
		
		int lastIdx = s.length() - 1;
		int matrixColLength = computeRigaCol(numRows, lastIdx)[1]+1;
 
		char[] matrix = new char[numRows * matrixColLength];
 
		Arrays.fill(matrix, ' ');
		
		for(int k = 0; k< s.length(); k++)
		{
			int[] idxs = computeRigaCol(numRows,k);
			matrix[  idxs[0] * matrixColLength + idxs[1]   ] = s.charAt(k);
		}
 
		return new String(matrix).replace(" ", "");
	}
	
	/*
	 * input:
	 * 	k: idx of char
	 *  n: input of the problem
	 * output:
	 *  int[0] : row idx in matrix
	 *  int[1] : col idx in matrix
	 */
	private static int[] computeRigaCol(int n, int k)
	{
 
		int ibucket = k  / (2*n-2);
		int startcol = ibucket*(n-1);
		 
		int offset = k%(2*n-2);
		if(offset < n)
		{
			 
			return new int[] {offset,startcol};
		}
		else
		{
			int delta = offset-(n-1);
			return new int[] {(n-1)-delta, startcol+delta};
		}
	}
}
