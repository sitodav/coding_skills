package sito.davide.utils;

public enum EBusinessParameter
{
	PILOTES_COST, UPDATE_VALIDWINDOW_MNTS, PILOTES_VALID_QUANTITIES;
	
	public static EBusinessParameter findByValue(String strValue)
	{
		for(EBusinessParameter e : values())
		{
			if(e.name().equals(strValue))
				return e;
		}
		
		return null;
	}
	  
}
