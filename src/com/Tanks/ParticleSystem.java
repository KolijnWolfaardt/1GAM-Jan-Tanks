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
	float size = 50;
	float startX = 0.0f;
	float startY = 0.0f;
	int particleLifespan;
	int addrate = 0;
	
	public ParticleSystem(String name,float posX,float posY)
	{
		particleTextureFilename=name;
		startX = posX;
		startY = posY;
		particleLifespan = 1500;
		
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
		
		pat.speedY = -(float)Math.random()*4-2;
		pat.accX = (float)(Math.random()-0.5)*0.1f;
		pat.accY = 0.05f;
		
		allTheParticles.add(pat);
	}
	
	public void render()
	{
		//This particle is just to hold the current particle in the list
		Particle tempPart;
		
		particleTexture.bind();
		
		GL11.glBegin(GL11.GL_QUADS);
		for (int i = 0; i < allTheParticles.size(); i++)
		{
			tempPart = allTheParticles.get(i);
			
			//Render it.
			GL11.glTexCoord2f(0.0f,0.0f);
			GL11.glVertex2f(tempPart.locX,tempPart.locY);
					
			GL11.glTexCoord2f(particleTexture.getWidth(),0.0f);
			GL11.glVertex2f(tempPart.locX+size,tempPart.locY);
				
			GL11.glTexCoord2f(particleTexture.getWidth(),particleTexture.getHeight());
			GL11.glVertex2f(tempPart.locX+size,tempPart.locY+size);
						
			GL11.glTexCoord2f(0.0f,particleTexture.getHeight());
			GL11.glVertex2f(tempPart.locX,tempPart.locY+size);	
		}
		GL11.glEnd();
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