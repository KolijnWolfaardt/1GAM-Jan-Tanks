package com.Tanks;

import com.Tanks.Util;
import com.Tanks.OpenGLTools;
import com.Tanks.TextTools;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Runner 
{
	Settings gameSettings;
	
	// time at last frame
	long lastFrame;
	//frames per second 
	int fps;
	// last fps time
	long lastFPS;
		
	public Runner()
	{
		//First steps first. Create Settings object. Load from file. If file does not exist, create one, save.
		gameSettings = new Settings();
		int settingsLoadResult = gameSettings.loadFromFile();
		
		if (settingsLoadResult == 0)
		{
			Util.PrintText(1,"Settings Loaded");
		}
		if (settingsLoadResult == 2 || settingsLoadResult == 1 )
		{
			Util.PrintText (1, "Could not load settings. Reverting to default");
			
			int settingsSaveResult = gameSettings.saveToFile();
			
			if (settingsSaveResult == 0)
			{
				Util.PrintText (1,"New settings saved");
			}
			if (settingsSaveResult == 1)
			{
				Util.PrintText (1,"New settings could not be saved. Do you have write access?");
			}
		}
		
		gameSettings.printSettings();
	}

	public void start() 
	{
		OpenGLTools.setupGL(gameSettings);
		OpenGLTools.initGL(gameSettings);
		
		TextTools.init();
		
		GameDisplay gd = new GameDisplay();
		gd.init();
		
		getDelta();
		lastFPS = getTime();

		while (!Display.isCloseRequested())
		{		
			int delta = getDelta();	
			
			/*if (Keyboard.isKeyDown(Keyboard.KEY_F10))
			{
				//Toggle fullscreen! Yay.
				gameSettings.setDisplayFull(!gameSettings.getDisplayFull());
				Display.destroy();
				System.out.println("Destroying Display");
				OpenGLTools.setupGL(gameSettings);
				OpenGLTools.initGL(gameSettings);
			}*/
			
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 
			
			gd.update(delta);
			updateFPS();
			
			gd.render();

			Display.update();
			Display.sync(120); 
		}

		Display.destroy();
	}
	
	//Calculate how many milliseconds have passed since last frame.
	public int getDelta()
	{
	    long time = getTime();
	    int delta = (int) (time - lastFrame);
	    lastFrame = time;
	 
	    return delta;
	}
	

	//Get the accurate system time
	public long getTime()
	{
	    return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	
	// Calculate the FPS and set it in the title bar
	public void updateFPS()
	{
		if (getTime() - lastFPS > 1000)
		{
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}
	
	
	public static void main(String[] argv)
	{
		System.setProperty("org.lwjgl.librarypath",System.getProperty("user.dir") + "/lwjgl/natives/");
	
		Runner displayExample = new Runner();
		displayExample.start();
	}
}