package com.Tanks;

import org.lwjgl.opengl.GL11;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.awt.geom.Point2D.Float;

import java.util.ArrayList;

import java.io.*;

public class ParticleSystem
{
	private class Particle
	{
		public float locX = 0;
		public float locY = 0;
		public float speedX = 0;
		public float speedY = 0;
		public float accX = 0;
		public float accY = 0;
		public float rot = 0;
		public int life = 0;
	
		public void update(int delta)
		{
			life+=delta;
			
			locX+=speedX;
			locY+=speedY;
			
			speedX+=accX;
			speedY+=accY;
		}
	}

	ArrayList<Particle> allTheParticles;
	int maxNum = 500;
	boolean autoAdd = true;
		//If autoAdd is true, particles will be added evenly for the entire time.
		// If autoAdd is false, particles will only be added when add() is called.
	Texture particleTexture;
	String particleTextureFilename;
	
	public float size = 50;
	public float startX = 0.0f;
	public float startY = 0.0f;
	
	public float startSpeedX = 0.0f;
	public float startSpeedY = 0.0f;
	
	public float accX = 0.0f;
	public float accY = 0.0f;
	
	public int particleLifespan;
	public int addrate = 0;
	
	public int sizeX = 0;
	public int sizeY = 0;
	
	public ParticleSystem(String name,boolean move, float posX,float posY,int life)
	{
		particleTextureFilename=name;
		startX = posX;
		startY = posY;
		autoAdd = move;
		particleLifespan = life;
		
		allTheParticles = new ArrayList();
		//Load texture.
		try
		{
			particleTexture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(particleTextureFilename),GL11.GL_NEAREST );
		}
		catch (IOException ioe)
		{
			// TODO Auto-generated catch block
			ioe.printStackTrace();
		}
	}	

	public void add()
	{
		Particle pat = new Particle();
		pat.locX = startX;
		pat.locY = startY;
		
		pat.speedX = startSpeedX;
		pat.speedY = startSpeedY;
		
		pat.accX = accX;
		pat.accY = accY;
		
		pat.rot = (float)(Math.random()*Math.PI);
		
		allTheParticles.add(pat);
	}
	
	public void add(float posX, float posY, float rotation)
	{
		Particle pat = new Particle();
		pat.locX = posX;
		pat.locY = posY;
		
		pat.speedX = startSpeedX;
		pat.speedY = startSpeedY;
		
		pat.accX = accX;
		pat.accY = accY;
		
		pat.rot = rotation;
		
		allTheParticles.add(pat);
	}
	
	public void render(int offsetX,int offsetY)
	{
		//This particle is just to hold the current particle in the list
		Particle tempPart;
		
		particleTexture.bind();
		
		
		for (int i = 0; i < allTheParticles.size(); i++)
		{
			tempPart = allTheParticles.get(i);
			
			GL11.glPushMatrix();
			GL11.glTranslatef(tempPart.locX-offsetX, tempPart.locY-offsetY, 0);
			GL11.glRotatef((float) Math.toDegrees(tempPart.rot), 0f, 0f, 1f);
			GL11.glTranslatef(-tempPart.locX+offsetX, -tempPart.locY+offsetY, 0);
			
			GL11.glBegin(GL11.GL_QUADS);
				//Render it.
				GL11.glTexCoord2f(0.0f,0.0f);
				GL11.glVertex2f(tempPart.locX-offsetX-sizeX,tempPart.locY-offsetY-sizeY);
						
				GL11.glTexCoord2f(particleTexture.getWidth(),0.0f);
				GL11.glVertex2f(tempPart.locX-offsetX+sizeX,tempPart.locY-offsetY-sizeY);
					
				GL11.glTexCoord2f(particleTexture.getWidth(),particleTexture.getHeight());
				GL11.glVertex2f(tempPart.locX-offsetX+sizeX,tempPart.locY+sizeY-offsetY);
							
				GL11.glTexCoord2f(0.0f,particleTexture.getHeight());
				GL11.glVertex2f(tempPart.locX-offsetX-sizeX,tempPart.locY+sizeY-offsetY);	
			GL11.glEnd();
			
			GL11.glPopMatrix();	
		}
		
	}
	
	public void update(int delta)
	{
		//This particle is just to hold the current particle in the list
		Particle tempPart;
		
		for (int i = 0; i < allTheParticles.size(); i++)
		{
			tempPart = allTheParticles.get(i);
			tempPart.update(delta);
			
			if (tempPart.life > particleLifespan)
			{
				allTheParticles.remove(i);
			}			
		}
	}

	public void removeAll()
	{
	}
}