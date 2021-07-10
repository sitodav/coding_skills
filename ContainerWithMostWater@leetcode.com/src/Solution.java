import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
 * 
 * 
 * https://leetcode.com/problems/container-with-most-water/
 * 
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of the line i is at (i, ai) and (i, 0). Find two lines, which, together with the x-axis forms a container, such that the container contains the most water.

Notice that you may not slant the container.

 

Example 1:


Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
 */
public class Solution {

	public static void main(String[] args) {
		int[] test = new int[] {1,2,1};
		System.out.println(maxArea(test));
	}

	public static int maxArea(int[] height) {
		
		/*	
		 * Complexity O (n(n-1)/2 )
		 */
		int maxScore = -1;
//		int[][] scoreMatrix = new int[height.length][height.length];
		for(int j = 0; j< height.length;j++)
		{
			for(int i = 0; i<=j; i++)
			{
//				scoreMatrix[i][j] = Math.abs(i-j) * Math.min(height[i], height[j]);
				int score =  Math.abs(i-j) * Math.min(height[i], height[j]);
				if(score > maxScore)
					maxScore = score;
			}
		}
		return maxScore;
		
//		String labels = "   ABCDEFGHI";
//		printMatrix(scoreMatrix,labels);
		
//		for(int j = 0; j< height.length;j++)
//		{
//			for(int i = 0; i<=j; i++)
//			{
//				scoreMatrix[i][j] *= Math.min(height[i], height[j]);
//			}
//		}
//		
//		printMatrix(scoreMatrix,labels);
		
		 
	}
	
	private static void printMatrix(int[][] matrix, String labels)
	{
		
		System.out.println("\n---------------------------------\n"+labels);
		for(int i = 0; i< matrix.length;i++)
		{
			System.out.print(labels.charAt(3+i)+": ");
			for(int j = 0; j< matrix.length; j++)
			{
				System.out.print(matrix[i][j]+"-");
			}
			System.out.println("");
		}
		System.out.println("-----------------------------------");
	}

}
