package sito.davide.utils;

public enum EUserRoles
{
	
	
	USER_ADMIN,USER_CUSTOMER;
	public static EUserRoles findByDbValue(String strValue)
	{
		for(EUserRoles e : values())
		{
			if(e.name().equals(strValue))
				return e;
		}
		
		return null;
	}
	  
}
