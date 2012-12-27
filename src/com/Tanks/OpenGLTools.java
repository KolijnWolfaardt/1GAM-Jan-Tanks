package com.Tanks;

import com.Tanks.Settings;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import org.lwjgl.input.Mouse;

public class OpenGLTools
{
	public static void setupGL(Settings set)
	{
		try
		{	
			DisplayMode targetDisplayMode = null;
			 
			if (set.getDisplayFull())
			{
				DisplayMode[] modes = Display.getAvailableDisplayModes();
				int freq = 0;
				 
				for (int i=0;i<modes.length;i++)
				{
					DisplayMode current = modes[i];
					 
					if ((current.getWidth() == set.getDisplayWidth()) && (current.getHeight() == set.getDisplayHeight()))
					{
						if ((targetDisplayMode == null) || (current.getFrequency() >= freq))
						{
							if ((targetDisplayMode == null) || (current.getBitsPerPixel() > targetDisplayMode.getBitsPerPixel()))
							{
								targetDisplayMode = current;
								freq = targetDisplayMode.getFrequency();
							}
						}
					 
						// if we've found a match for bpp and frequence against the
						// original display mode then it's probably best to go for this one
						// since it's most likely compatible with the monitor
						if ((current.getBitsPerPixel() == Display.getDesktopDisplayMode().getBitsPerPixel()) &&
						(current.getFrequency() == Display.getDesktopDisplayMode().getFrequency()))
						{
							targetDisplayMode = current;
							break;
						}
					}
				}
			}
			else
			{
				targetDisplayMode = new DisplayMode(set.getDisplayWidth(),set.getDisplayHeight());
			}
			 
			if (targetDisplayMode == null)
			{
				System.out.println("Failed to find value mode: "+set.getDisplayWidth()+"x"+set.getDisplayHeight()+" fs="+set.getDisplayFull());
				return;
			}

			Display.setDisplayMode(targetDisplayMode);
			Display.setFullscreen(set.getDisplayFull());
			Display.setVSyncEnabled(set.getDisplayVsync());
			System.out.println("Creating display");
			Display.create();
			 
		} 
		catch (LWJGLException e) 
		{
			System.out.println("Unable to setup mode "+set.getDisplayWidth()+"x"+set.getDisplayHeight()+" fullscreen="+set.getDisplayFull() + e);
		}
	}
	
	public static void initGL(Settings set)
	{
		GL11.glEnable(GL11.GL_TEXTURE_2D); 
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		
		GL11.glLoadIdentity();
		GL11.glOrtho(0, set.getDisplayWidth(), 0, set.getDisplayHeight(), 1, -1);
		
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glEnable(GL11.GL_BLEND); 
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		//Mouse.setGrabbed(true); 
	}
	
	
}