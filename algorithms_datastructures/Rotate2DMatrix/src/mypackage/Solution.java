package mypackage;


/*
 * https://leetcode.com/problems/rotate-image/
 * 
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).

 You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
 */
public class Solution {
	
	public static void main(String[] args)
	{
		int[][] mat = new int[][] {{1,2,3},{4,5,6},{7,8,9}};
		rotate(mat);
		System.out.println("terminato");
	}
	
	
    public static void rotate(int[][] matrix) {
        int n = matrix.length;

        for(int riga_start = 0; riga_start < (int)n/2; riga_start++)
        {
            for(int col_start = riga_start; col_start< n-1-riga_start; col_start++)
            {
                int  riga_orig, col_orig = -1; 
                int t = matrix[riga_start][col_start];
                int i = riga_start, j = col_start;
                for(;;)
                {
                    riga_orig = n-1-j;
                    col_orig = i;
                    if(riga_orig == riga_start && col_orig == col_start)
                    {
                        matrix[i][j] = t;
                        break;
                    }
                    else
                    {
                        matrix[i][j]= matrix[riga_orig][col_orig];
                        i = riga_orig;
                        j = col_orig;
                    }
                }
            }
        }
    }
}
