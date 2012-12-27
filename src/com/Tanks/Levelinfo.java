package com.Tanks;

import java.util.Scanner;
import java.io.*;

public class Levelinfo
{
	public int[][] grid;
	
	int gridXSize;
	int gridYSize;
	
	//Centre Coordinates
	int playerXPos = 100;
	int playerYPos = 100;
	
	float playerBodyRot = 1.0f; //?????????
	float playerTurrentRot = 1.0f;
	
	public void init()
	{
	}
	
	public void loadFromFile(String fileName)
	{
		try
		{
			Scanner sc = new Scanner(new File (fileName));
			
			gridXSize = sc.nextInt();
			gridYSize = sc.nextInt();
			
			grid = new int[gridYSize][gridXSize];
			
			for (int i = 0; i < gridYSize; i++)
			{
				for (int j = 0; j < gridXSize; j++)
				{
					grid[i][j] = sc.nextInt();
				}
			}
		}
		catch (FileNotFoundException fnfe)
		{
			fnfe.printStackTrace();
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}	
	}
	
	
	//Level Stuff
	public int getGridXSize(){
		return gridXSize;
	}
	
	public int getGridYSize(){
		return gridYSize;
	}
	
	
	//Player Variables
	public int getPlayerXPos(){
		return playerXPos;
	}
	public int getPlayerYPos(){
		return playerYPos;
	}
	public float getPlayerBodyRot(){
		return playerBodyRot;
	}
	public float getPlayerTurrentRot(){
		return playerTurrentRot;
	}
}