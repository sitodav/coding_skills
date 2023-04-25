package sito.davide.utils;

public enum EOrderStatus
{
	CREATED,   IN_PREPARATION, IN_DELIVERY,DELIVERED;
	
	public static EOrderStatus findByValue(String strValue)
	{
		for(EOrderStatus e : values())
		{
			if(e.name().equals(strValue))
				return e;
		}
		
		return null;
	}
	  
}
