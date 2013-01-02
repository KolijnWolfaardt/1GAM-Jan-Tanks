package com.Tanks;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.Tanks.Levelinfo;
import com.Tanks.TextTools;

import java.util.Scanner;
import java.io.*;

public class GameDisplay extends GeneralDisplay
{
	//This class should act like the main game engin.
	//Levelinfo contains all info about the current level
	Texture Tiles1;
	Texture Tanks1;
	
	float texWidth = 0.0f;
	float texOffset = 0.0f;
	
	float tankWidth = 0.0f;
	
	int tileSize = 40; //Draw 20px tiles as 40px tiles
	
	Levelinfo currLev;
	
	int[] tilesInfo = new int[1024];
	
	public void init()
	{
		currLev = new Levelinfo();
		currLev.init();
		currLev.loadFromFile("leveldata/level01.dat");
		
		try
		{
			Tanks1 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/tanks.png"),GL11.GL_NEAREST );
			Tiles1 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/tiles.png"),GL11.GL_NEAREST); 
			
			texOffset = Tiles1.getWidth() / 32.0f;
			texWidth = texOffset*20.0f/21.0f;
			
			tankWidth = Tanks1.getWidth()/4.0f;
			
			Scanner sc = new Scanner(new File ("images/tilesdata.dat"));
			
			for (int i = 0; i < 1024; i++)
			{
				tilesInfo[i] = sc.nextInt();
			}
			
			sc.close();
		}
		catch (IOException ioe)
		{
			// TODO Auto-generated catch block
			ioe.printStackTrace();
		}
	}
	

	public void render()
	{
		drawTiles();
		drawTank(currLev.playerTank);
		TextTools.uFont.drawString(10, 800, "Welcome To the Machine");
		TextTools.uFont.drawString(10, 820, "Speed : "+currLev.playerTank.Speed);
		TextTools.uFont.drawString(10, 840, "GoalR : "+Math.toDegrees(currLev.playerTank.GoalRot));
		TextTools.uFont.drawString(10, 860, "BodyR : "+Math.toDegrees(currLev.playerTank.bodyRot));
		TextTools.uFont.drawString(10, 880, "Delta : "+Math.toDegrees(currLev.playerTank.GoalRot-currLev.playerTank.bodyRot));
		TextTools.uFont.drawString(300, 820,"X pos : "+currLev.playerTank.XPos);
		TextTools.uFont.drawString(300, 840,"Y pos : "+currLev.playerTank.YPos);
		//TextTools.uFont.drawString(300, 860,"Delta : "+Math.toDegrees(currLev.playerTank.GoalRot-currLev.playerTank.bodyRot));
	}
	
	private void drawTiles()
	{
		Tiles1.bind();
	
		GL11.glBegin(GL11.GL_QUADS);
				
		for (int i = 0;i < currLev.getGridXSize(); i++)
		{
			for(int j = 0;j < currLev.getGridYSize();j++)
			{
				//Find the texture X and Y	
				int texXPos = currLev.grid[j][i]%32;
				int texYPos = currLev.grid[j][i]/32;
			
				GL11.glTexCoord2f(texXPos*texOffset,texYPos*texOffset);
				GL11.glVertex2f(i*tileSize,j*tileSize);
				
				GL11.glTexCoord2f(texXPos*texOffset+texWidth,texYPos*texOffset);
				GL11.glVertex2f((i+1)*tileSize,j*tileSize);
					
				GL11.glTexCoord2f(texXPos*texOffset+texWidth,texYPos*texOffset+texWidth);
				GL11.glVertex2f((i+1)*tileSize,(j+1)*tileSize);
					
				GL11.glTexCoord2f(texXPos*texOffset,texYPos*texOffset+texWidth);
				GL11.glVertex2f(i*tileSize,(j+1)*tileSize);
			}
		}
		GL11.glEnd();
	}
	
	private void drawTank(Tank theTank)
	{
		Tanks1.bind();
	
		//Draw body
		GL11.glPushMatrix();
		GL11.glTranslatef(theTank.getXPos(), theTank.getYPos(), 0);
		GL11.glRotatef((float) Math.toDegrees(theTank.getBodyRot()), 0f, 0f, 1f);
		GL11.glTranslatef(-theTank.getXPos(), -theTank.getYPos(), 0);
		
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0.0f,0.0f);
			GL11.glVertex2f(theTank.getXPos()-50,theTank.getYPos()-50);
					
			GL11.glTexCoord2f(tankWidth,0.0f);
			GL11.glVertex2f(theTank.getXPos()+50,theTank.getYPos()-50);
				
			GL11.glTexCoord2f(tankWidth,tankWidth);
			GL11.glVertex2f(theTank.getXPos()+50,theTank.getYPos()+50);
						
			GL11.glTexCoord2f(0.0f,tankWidth);
			GL11.glVertex2f(theTank.getXPos()-50,theTank.getYPos()+50);	
		GL11.glEnd();
		
		GL11.glPopMatrix();	
		
		//Draw Turrent
		GL11.glPushMatrix();
		GL11.glTranslatef(theTank.getXPos(), theTank.getYPos(), 0);
		GL11.glRotatef((float) Math.toDegrees(theTank.getTurrentRot()), 0f, 0f, 1f);
		GL11.glTranslatef(-theTank.getXPos(), -theTank.getYPos(), 0);
		
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(tankWidth,0.0f);
			GL11.glVertex2f(theTank.getXPos()-50,theTank.getYPos()-50);
					
			GL11.glTexCoord2f(tankWidth*2,0.0f);
			GL11.glVertex2f(theTank.getXPos()+50,theTank.getYPos()-50);
				
			GL11.glTexCoord2f(tankWidth*2,tankWidth);
			GL11.glVertex2f(theTank.getXPos()+50,theTank.getYPos()+50);
						
			GL11.glTexCoord2f(tankWidth,tankWidth);
			GL11.glVertex2f(theTank.getXPos()-50,theTank.getYPos()+50);	
		GL11.glEnd();
		
		GL11.glPopMatrix();	
		
	}
	
	public void update(int delta)
	{
		
		//Check for keyboard inputs
		
		//Grid Movement
		if (Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_A) || Keyboard.isKeyDown(Keyboard.KEY_S) || Keyboard.isKeyDown(Keyboard.KEY_D))
		{
			if (currLev.playerTank.Speed < currLev.playerTank.maxSpeed)
				currLev.playerTank.Speed+= currLev.playerTank.speedInc;
		}
		else
		{
			currLev.playerTank.Speed = currLev.playerTank.Speed*0.5f;
			if (Math.abs(currLev.playerTank.Speed) < currLev.playerTank.speedInc)
				currLev.playerTank.Speed = 0;
		}
		int a = 0;
		int b  =0;
		
		if (Keyboard.isKeyDown(Keyboard.KEY_W) )
		{
			b=1;
		}
		else
		{
			if (Keyboard.isKeyDown(Keyboard.KEY_S))
			{
				b=-1;
			}
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_A) )
		{
			a=-1;
		}
		else
		{
			if (Keyboard.isKeyDown(Keyboard.KEY_D))
			{
				a=1;
			}
		}
		if (!(a==0 && b==0))	//They are both 0, no keys have been pressed
		{ 
			currLev.playerTank.GoalRot = (float)Math.atan2(-b,a);
		}
		
		if (currLev.playerTank.GoalRot < 0)
		{
			currLev.playerTank.GoalRot+=2.0*Math.PI;
		}
		
		if (currLev.playerTank.bodyRot < 0)
		{
			currLev.playerTank.bodyRot+=2.0*Math.PI;
		}
		
		currLev.playerTank.GoalRot = (float) (currLev.playerTank.GoalRot%(2.0*Math.PI));
		currLev.playerTank.bodyRot = (float) (currLev.playerTank.bodyRot%(2.0*Math.PI));
		
		//Now rotate to match rotation.
		float radDelta = currLev.playerTank.GoalRot-currLev.playerTank.bodyRot;
		if (radDelta > 0)
		{
			if (radDelta > Math.PI)
			{
				currLev.playerTank.bodyRot+= (radDelta-2*Math.PI)*0.01f*delta;
			}
			else
			{
				currLev.playerTank.bodyRot+= radDelta*0.01f*delta;
			}
		}
		else
		{
			if (radDelta > -Math.PI)
			{
				currLev.playerTank.bodyRot+= radDelta*0.01f*delta;
			}
			else
			{
				currLev.playerTank.bodyRot+= (radDelta+2*Math.PI)*0.01f*delta;
			}
		}
		
		boolean move = true;
		
		//Calculate add
		float addX = (float)(currLev.playerTank.Speed*Math.cos(currLev.playerTank.bodyRot)*delta);
		float addY = (float)(currLev.playerTank.Speed*Math.sin(currLev.playerTank.bodyRot)*delta);
		
		//Calculate new grid position
		int tankGridX = (int)(currLev.playerTank.XPos)/tileSize;
		int tankGridY = (int)(currLev.playerTank.YPos)/tileSize;
		
		int tankNewGridX = (int)(currLev.playerTank.XPos+addX)/tileSize;
		int tankNewGridY = (int)(currLev.playerTank.YPos+addY)/tileSize;

		//Calculate current grid posistion
		
		if (!((tankNewGridX >= currLev.gridXSize-1) || (tankNewGridX <= 0))) //X direction is allowed to move
		{
			if (!(tilesInfo[currLev.grid[tankGridY][tankNewGridX]]<10))
				move = false;

		}
		else
			move = false;
			
		
		if (!((tankNewGridY >= currLev.gridYSize-1) || (tankNewGridY <= 0)))
		{
			if (!(tilesInfo[currLev.grid[tankNewGridY][tankGridX]]<10))
				move = false;
		}
		else
			move = false;
		
		if (move)
		{
			currLev.playerTank.XPos+=addX;
			currLev.playerTank.YPos+=addY;
		}
		//Update Turrent Direction
		currLev.playerTank.turrentRot= (float)Math.atan2(-(Mouse.getY()-currLev.playerTank.getYPos()),Mouse.getX()-currLev.playerTank.getXPos());
	}
}






