package com.Tanks;

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