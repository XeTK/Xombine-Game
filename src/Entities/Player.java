package Entities;

import Game.guns.Gun;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
//the player you know the peson u play as
public class Player extends ObjectItem
{
	//create le weopon and position it
	private Gun gun = new Gun(posX + 5, posY + 15);
	//default constructor and shizz creates a player in a set position and mods from super class
	public Player(float posX, float posY)
	{
		super(posX, posY);
		super.setHeight(30);
		super.setWidth(10);
		Paint p = new Paint();
		p.setColor(Color.WHITE);
		super.setP(p);
		// TODO Auto-generated constructor stub
	}
	//diable this as a player dosnt need to seak 
	@Override
	public void seak(){}
	//some collision mcshizzz minuses health and adds hit you know the usual shit
	@Override 
	public void collision()
	{
		health--;
		hit = true;
	}
	
	public void damage()
	{
		health--;
		hit = true;
	}
	//override some of the drawing method
	@Override
	public void onDraw(Canvas c)
	{
		//reset the gun position
		gun.setPosX(posX + 5);
		gun.setPosY(posY + 15);
		//call origernal draw class
		super.onDraw(c);
		//draw are gun
		gun.onDraw(c);
	}
	//method for moving right
	public void moveRight()
	{
		gun.setDir(+1);
		posX += wvolocity;
	}
	//method for moving left
	public void moveLeft()
	{
		gun.setDir(-1);
		posX -= wvolocity;
	}
	//little bit of jumping
	public void makeJump()
	{
		//disable le double jump
		if (!jumping&&!falling)
			jumping = true;
	}
	//call the fire on the gun
	public void shoot()
	{
		gun.fire();
	}
	//pull array back from the gun
	public Gun getGun()
	{
		return gun;
	}
}
