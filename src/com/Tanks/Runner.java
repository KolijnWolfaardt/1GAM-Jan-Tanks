package com.Tanks;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import com.Tanks.Util;

public class Runner 
{
	Settings gameSettings;
	
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
		try
		{
			//First, a few validation checks.
			DisplayMode currentCapabilities = Display.getDesktopDisplayMode();
			
			//Is fullscreen is supported
			if (!currentCapabilities.isFullscreenCapable()) // If the screen cannot go fullscreen
			{
				if (gameSettings.getDisplayFull()==1)	//And the user is trying to go fullscreen
				{
					Util.PrintText(1,"Sorry, fullscreen is not supported on your screen");
					gameSettings.setDisplayFull(0);
				}
			}
			
			//Is the resolution larger than the supported resolution?
			if (gameSettings.getDisplayHeight() > currentCapabilities.getHeight())
			{
				Util.PrintText(1,"Resolution height is larger than the supported resolution");
				gameSettings.setDisplayHeight(currentCapabilities.getHeight());
			}

			if (gameSettings.getDisplayWidth() > currentCapabilities.getWidth())
			{
				Util.PrintText(1,"Resolution width is larger than the supported resolution");
				gameSettings.setDisplayWidth(currentCapabilities.getWidth());
			}			
				
			//Now create the Display
			Display.setDisplayMode(new DisplayMode(gameSettings.getDisplayWidth(), gameSettings.getDisplayHeight()));
			Display.setDisplayMode(Display.getDesktopDisplayMode());
			
			if ( gameSettings.getDisplayFull() == 1)
			{
				Util.PrintText(1,"Going Fullscreen");
				Display.setFullscreen(true);
			}
			
			Display.create();
			

		}
		catch (LWJGLException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		
		// init OpenGL here
		
		while (!Display.isCloseRequested())
		{
			// render OpenGL here
			Display.update();
		}
		
		Display.destroy();
	}
	
	public static void main(String[] argv)
	{
		System.setProperty("org.lwjgl.librarypath",System.getProperty("user.dir") + "/lwjgl/natives/");
		
	
		Runner displayExample = new Runner();
		displayExample.start();
	}
}