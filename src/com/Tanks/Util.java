package com.Tanks;

public class Util
{
	public static void PrintText(int debugLevel,String text)
	{
		//Possible debug levels :
		// 1 - light text
		// 2 - More Text
		// 3 - Fuckloads. Errors and everything. Everything.
	
		int debugReq = 3; 
		
		if (debugLevel <= debugReq)
		{
			System.out.println(text);
		}
	}
}