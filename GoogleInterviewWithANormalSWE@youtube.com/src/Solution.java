import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 
 * Problem at :
 * https://youtu.be/rw4s4M3hFfs
 * 
 * 
 */
public class Solution {



	/*Utility methods */
	
	private static void printDistanceScores(List<Map<String,Integer>> scores)
	{
		System.out.println("\n---------------");
		for(Map<String,Integer> score : scores)
		{
			for(String key : score.keySet())
			{
				System.out.println(key+":"+score.get(key));
			}
			System.out.println();
		}
		System.out.println("----------------");
	}
	
	private static Map<String,Boolean> buildBlockMap(String[] onlyTrueImportants,String[] allImportants)
	{
		Map<String,Boolean> toRet = new HashMap<>();
		
		for(String k : allImportants)
			toRet.put(k,false);
		
		for(String k : onlyTrueImportants)
			toRet.put(k,true);
		
		return toRet;
	}
	
	private static Map<String,Integer> initScore(String[] allImportants)
	{
		Map<String,Integer> score = new HashMap<>();
		
		for(String important : allImportants)
		{
			score.put(important, Integer.valueOf(100000));
		}
		
		return score;
	}
	
	
	public static void main(String[] args)
	{
		List<Map<String,Boolean>> blocks = new ArrayList<>();
		
		String[] allImportants = new String[] {"A","B","C"};
		
		blocks.add(buildBlockMap(new String[]{"B"},allImportants));
		blocks.add(buildBlockMap(new String[]{"A"},allImportants));
		blocks.add(buildBlockMap(new String[]{"A","B"},allImportants));
		blocks.add(buildBlockMap(new String[]{"B"},allImportants));
		blocks.add(buildBlockMap(new String[]{"B","C"},allImportants));
		
		solve(blocks,allImportants);
	}
	
	
	
	
	/*Solution */
	public static int solve(List<Map<String,Boolean>> blocks, String[] allImportants)
	{
		int idxWinner = -1;
		int minMax = 1000000;
		
		List<Map<String,Integer>> scoreDistances =  new ArrayList<>();
		
		Map<String,Integer> previousBlockScore = initScore(allImportants); //dummy before 0-index iteration
		
		//SCAN FORWARD
		for(int iblock = 0; iblock < blocks.size(); iblock++)
		{
			Map<String,Boolean> block = blocks.get(iblock);
			Map<String,Integer> scoreDistanceForBlock = initScore(allImportants);
			
			for(String importantToCheck : allImportants)
			{
				if(block.get(importantToCheck)) //if there is that "important" in the block, set the distance to 0
				{
					scoreDistanceForBlock.put(importantToCheck, Integer.valueOf(0));
				}
				else //otherwise it's the distance of the neighbours + 1
				{
					scoreDistanceForBlock.put(importantToCheck, previousBlockScore.get(importantToCheck)+1);
				}
			}
			scoreDistances.add(scoreDistanceForBlock);
			previousBlockScore = scoreDistanceForBlock;
		}
		
		//SCAN BACKWARD (we start from second to last, 
		//and at this point, the previousBlockScore already points to the last score distance */
		//WHILE WE SCAN, WE FIND THE BLOCK WITH THE SMALLEST MAXIMUM DISTANCES ASSOCIATED WITH THE "IMPORTANTS"
		
		for(int iblock = blocks.size()-1; iblock >= 0; iblock--)
		{
			Map<String,Boolean> block = blocks.get(iblock);
			Map<String,Integer> scoreCalculatedOnForward = scoreDistances.get(iblock);
			
			int maxDistanceScoreLocally = 0;
			
			for(String importantToCheck : allImportants)
			{
				if(block.get(importantToCheck)) //if there is that "important" in the block
				{
					//nothing because we already set it to 0 in the forward scan
				}
				else //otherwise check if the right neightbour distance +1 is < then the distance set in the forward scan
				{
					if(previousBlockScore.get(importantToCheck) +1 < scoreCalculatedOnForward.get(importantToCheck))
						scoreCalculatedOnForward.put(importantToCheck, previousBlockScore.get(importantToCheck)+1);
				}
				
				if(scoreDistances.get(iblock).get(importantToCheck) > maxDistanceScoreLocally)
					maxDistanceScoreLocally = scoreDistances.get(iblock).get(importantToCheck) ;
			}
			
			if(maxDistanceScoreLocally < minMax)
			{
				minMax = maxDistanceScoreLocally;
				idxWinner = iblock;
			}
			
			previousBlockScore = scoreCalculatedOnForward;
		}
		
		printDistanceScores(scoreDistances);
		
		return idxWinner;
	}
}
