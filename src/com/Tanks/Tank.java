package com.Tanks;

public class Tank
{
	float XPos = 0;
	float YPos = 0;
	
	float bodyRot = 0.0f;
	float turrentRot = 1.0f;
	
	float speed = 0.0f;
	
	float maxSpeed = 1.0f;
	float speedInc = 0.1f;

	public Tank()
	{
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