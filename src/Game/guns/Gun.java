package Game.guns;

import java.util.ArrayList;

import Game.guns.projectiles.StockBullet;
import android.graphics.Canvas;
import android.graphics.Paint;
//default gun class
public class Gun
{
	//hold the pos
	private float posX = 0, posY = 0, clipsize = 10, recover = 3, dir = +1, tick = 0, bulletsshot = 0;
	//set the paint
	private Paint p = new Paint();
	private boolean reload = false, reloaded = false;
	//create the class with the pos's
	private ArrayList<StockBullet> bullets = new ArrayList<StockBullet>();
	//defualty
	public Gun(float posX, float posY)
	{
		this.posX = posX;
		this.posY = posY;
	}
	//on draw
	public void onDraw(Canvas c)
	{
		//reload if we have reloaded then we reset stuff
		if (reloaded)
		{
			//clear the bullets shot
			bulletsshot = 0;
			//set that we dont need to reload again for a bit
			reload = false;
			reloaded = false;
		}
		//incroment tick
		tick++;
		//if we are facing left
		if (dir == -1)
		{
			//draw 1 way
			c.drawRect(posX - 20, posY + 5, posX, posY, p);
			c.drawRect(posX - 10, posY + 10, posX, posY, p);
		}
		else
		{
			//draw the right way
			c.drawRect(posX, posY, posX + 20, posY + 5, p);
			c.drawRect(posX, posY, posX + 10, posY + 10, p);
		}
		//remove are destryoed bullets so we can free some memory
		for (int i =0; i < bullets.size();i++)
			if (bullets.get(i).isDestroyed())
				bullets.remove(i);
		
		//draw the bullets to the screen
		for (int i =0; i < bullets.size();i++)
			bullets.get(i).onDraw(c);
		
	}
	public void fire()
	{
		//dont instant fire
		if (tick >= recover)
		{
			//check we are not outside of the clip size
			if (bulletsshot < clipsize)
			{
				//add bullet to array
				bullets.add(new StockBullet(posX,posY, dir));
				//incriment bullets shot
				bulletsshot++;
				//reset tick on fire
				tick = 0;
			}
			//give us a nice call to check if we need to reload
			else
				reload = true;
		}
	}
	// set some shizz
	public void setPosX(float posX)
	{
		this.posX = posX;
	}
	public void setPosY(float posY)
	{
		this.posY = posY;
	}
	public void setDir(float dir)
	{
		this.dir = dir;
	}
	public ArrayList<StockBullet> getBullets()
	{
		return bullets;
	}
	//get difference
	public float getClipsize()
	{
		return clipsize - bulletsshot;
	}
	public boolean isReload()
	{
		return reload;
	}
	public void setReloaded(boolean reloaded)
	{
		this.reloaded = reloaded;
	}
	
}
