package com.Tanks;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.*;

public class Tank
{
	float XPos = 0;
	float YPos = 0;
	
	float bodyRot = 0.0f;
	float turrentRot = 1.0f;
	
	float Speed = 0.0f;
	
	float maxSpeed = 0.15f;
	float speedInc = 0.015f;
	float GoalRot = 0.0f;
	
	int enemyType = 0;
	
	float moveDistance = 0;
	
	static Texture tanksTexture;
	static float tankWidth = 0.0f;
	
	
	ParticleSystem TankTracksParticles;
	
	public static void init()
	{
		try
		{
			Tank.tanksTexture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("images/tanks.png"),GL11.GL_NEAREST );
			
			Tank.tankWidth = Tank.tanksTexture.getWidth()/4.0f;
		}
		catch (IOException ioe)
		{
			// TODO Auto-generated catch block
			ioe.printStackTrace();
		}
	}

	public Tank()
	{
		TankTracksParticles = new ParticleSystem("images/tracksParticle.png",true,400,400,20000);
		TankTracksParticles.startSpeedX = 0;
		TankTracksParticles.startSpeedY = 0;
		TankTracksParticles.accX=0f;
		TankTracksParticles.accY=0f;
		TankTracksParticles.sizeX = 12;
		TankTracksParticles.sizeY = 30;
	}
	
	public void render(float offsetX,float offsetY)
	{
		//Draw tracks
		TankTracksParticles.render((int)offsetX,(int)offsetY);
		
		//Draw tank 
		Tank.tanksTexture.bind();
		
		//Calculate the position, with the screen offset.
		float xPos = XPos - offsetX; //Whatch out for these variables.
		float yPos = YPos - offsetY; // XPos is global, xPos only here
	
		//Draw body
		GL11.glPushMatrix();
		GL11.glTranslatef(xPos, yPos, 0);
		GL11.glRotatef((float) Math.toDegrees(bodyRot), 0f, 0f, 1f);
		GL11.glTranslatef(-xPos, -yPos, 0);
		
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0.0f,Tank.tankWidth*enemyType);
			GL11.glVertex2f(xPos-50,yPos-50);
					
			GL11.glTexCoord2f(Tank.tankWidth,Tank.tankWidth*enemyType);
			GL11.glVertex2f(xPos+50,yPos-50);
				
			GL11.glTexCoord2f(Tank.tankWidth,Tank.tankWidth*(enemyType+1));
			GL11.glVertex2f(xPos+50,yPos+50);
						
			GL11.glTexCoord2f(0.0f,Tank.tankWidth*(enemyType+1));
			GL11.glVertex2f(xPos-50,yPos+50);	
		GL11.glEnd();
		
		GL11.glPopMatrix();	
		
		//Draw Turrent
		GL11.glPushMatrix();
		GL11.glTranslatef(xPos, yPos, 0);
		GL11.glRotatef((float) Math.toDegrees(turrentRot), 0f, 0f, 1f);
		GL11.glTranslatef(-xPos, -yPos, 0);
		
		GL11.glBegin(GL11.GL_QUADS);
		
			GL11.glTexCoord2f(Tank.tankWidth,Tank.tankWidth*enemyType);
			GL11.glVertex2f(xPos-50,yPos-50);
					
			GL11.glTexCoord2f(Tank.tankWidth*2,Tank.tankWidth*enemyType);
			GL11.glVertex2f(xPos+50,yPos-50);
				
			GL11.glTexCoord2f(Tank.tankWidth*2,Tank.tankWidth*(enemyType+1));
			GL11.glVertex2f(xPos+50,yPos+50);
						
			GL11.glTexCoord2f(Tank.tankWidth,Tank.tankWidth*(enemyType+1));
			GL11.glVertex2f(xPos-50,yPos+50);	
		GL11.glEnd();
		
		GL11.glPopMatrix();	
	}
	
	public void update(int delta)
	{
		if (moveDistance > 20)
		{
			//Add a new track particle
			TankTracksParticles.add(XPos,YPos,bodyRot);
			moveDistance -= 20;
		}
		TankTracksParticles.update(delta);
	}
	
	//Player Variables
	public float getXPos(){
		return XPos;
	}
	public float getYPos(){
		return YPos;
	}
	public float getBodyRot(){
		return bodyRot;
	}
	public float getTurrentRot(){
		return turrentRot;
	}
}