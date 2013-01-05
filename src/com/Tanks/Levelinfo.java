package com.Tanks;

import java.util.Scanner;

import java.util.ArrayList;

import java.io.*;

public class Levelinfo
{
	public int[][] grid;
	
	public ArrayList<Tank> EnemyTanks;
	
	int gridXSize;
	int gridYSize;
	
	//Centre Coordinates
	public Tank playerTank;
	
	public void init()
	{
		playerTank = new Tank();
		playerTank.XPos = 200;
		playerTank.YPos = 200;
		
		EnemyTanks = new ArrayList(); 
		
		Tank enemy1 = new Tank();
		enemy1.XPos = 1000;
		enemy1.YPos = 1000;
		enemy1.enemyType = 0;
		EnemyTanks.add(enemy1);
		
		Tank enemy2 = new Tank();
		enemy2.XPos = 1000;
		enemy2.YPos = 1100;
		enemy2.enemyType = 1;
		EnemyTanks.add(enemy2);
		
		Tank enemy3 = new Tank();
		enemy3.XPos = 1000;
		enemy3.YPos = 1200;
		enemy3.enemyType = 2;
		EnemyTanks.add(enemy3);
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