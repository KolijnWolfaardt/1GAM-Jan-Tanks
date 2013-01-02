package com.Tanks;

import java.awt.Font;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class TextTools
{
	static UnicodeFont uFont;
	
	//uFont.drawString(x, y, "Welcome To the Machine");
	
	public static void init()
	{
		Font font = new Font("Times New Roman", Font.PLAIN, 18);
		uFont = new UnicodeFont(font);
		uFont.getEffects().add(new ColorEffect(java.awt.Color.white));
		uFont.addAsciiGlyphs();
		try
		{
			uFont.loadGlyphs();
		} catch (SlickException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
