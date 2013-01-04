package com.Tanks;

import java.util.Scanner;
import java.io.*;

public class Levelinfo
{
	public int[][] grid;
	
	int gridXSize;
	int gridYSize;
	
	//Centre Coordinates
	public Tank playerTank;
	
	public void init()
	{
		playerTank = new Tank();
		playerTank.XPos = 200;
		playerTank.YPos = 200;
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
	
	

}