package com.Tanks;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.Tanks.Levelinfo;

import java.io.IOException;

public class GameDisplay extends GeneralDisplay
{
	//This class should act like the main game engin.
	//Levelinfo contains all info about the current level
	Texture Tiles1;
	Texture tankBody;
	Texture tankTurrent;
	
	float texWidth = 0.0f;
	float texOffset = 0.0f;
	
	int tileSize = 40; //Draw 20px tiles as 40px tiles
	
	Levelinfo currLev;
	
	public void init()
	{
		currLev = new Levelinfo();
		currLev.init();
		currLev.loadFromFile("leveldata/level01.dat");
		
		try
		{
			Tiles1 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/tiles.png"),GL11.GL_NEAREST);
			
			texOffset = Tiles1.getWidth() / 32.0f;
			texWidth = texOffset*20.0f/21.0f;
		}
		catch (IOException ioe)
		{
			// TODO Auto-generated catch block
			ioe.printStackTrace();
		}
	}
	

	public void render()
	{
		


		
		GL11.glBegin(GL11.GL_QUADS);
		
		drawTiles();
		drawTank()

		GL11.glEnd();
	}
	
	private void drawTiles()
	{
		Tiles1.bind();
		
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
	}
	
	private void drawTank()
	{
		/*GL11.glPushMatrix();
		GL11.glTranslatef((X-xpos), (Y-ypos), 0);
		GL11.glRotatef((float) Math.toDegrees(rotation), 0f, 0f, 1f);
		GL11.glTranslatef(-(X-xpos), -(Y-ypos), 0);	*/
		
		/*GL11.glPopMatrix();*/		
	}
	
	public void update(int delta)
	{
		
	}
}