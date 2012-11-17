package Game.guns.projectiles;

import Misc.Statics;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class StockBullet
{
	private float speed = 10, damage = 10, dir = +1, posX = 0, posY = 0, width = 7, height = 5, tick = 0;
	private boolean destroyed = false, distructable = true, refracts = true, collided = false;
	private Paint pin = new Paint(), pout = new Paint();
	
	public StockBullet(float posX, float posY, float direction)
	{
		this.posX = posX;
		this.posY = posY;
		this.dir = direction;
		pin.setColor(Color.GRAY);
		pout.setColor(Color.BLACK);
	}
	public void onDraw(Canvas c)
	{
		bulletMovement();
		c.drawRect(posX, posY, posX + width, posY + height, pout);
		c.drawRect(posX + 2, posY + 2, posX + (width -2), posY + (height -2), pin);
	}
	private void bulletMovement()
	{
		//out of bounds then we reset
		if (posX < 0 || (posX + width) >= Statics.getScreenWidth() || posY < 0 || (posY + height) > Statics.getScreenHeight() - 65)
			destroyed = true;
		
		posX += dir * speed;
		if (collided)
		{
			tick++;
			if (refracts)
			{
				if (tick < 5)
				{
					posY--;
				}
				else 
				{
					refracts = false;
					tick = 0;
				}
			}
			else 
			{
				if (posY >= Statics.getScreenHeight() - 65)
				{
					destroyed = true;
					tick = 0;
				}
				else
					posY += + 2;
			}
		}
		
	}
	private float dirflip(float indir)
	{
		if (indir == -1)
			return +1;
		else
			return -1;
	}
	public void collision()
	{
		if (distructable)
		{
			collided = true;
			dir = dirflip(dir);
		}
		else
		{
			speed--;
			damage--;
		}
		
		if (speed <= 0)
			collided = true;
	}
	//get the bound
	public Rect getBounds()
	{
		return new Rect((int)posX, (int)posY, (int)(posX + width), (int)(posY + height));
	}
	public void setPos(float posX,float posY)
	{
		this.posX = posX;
		this.posY = posY;
	}
	public boolean isDestroyed()
	{
		return destroyed;
	}
	public void setDestroyed(boolean destroyed)
	{
		this.destroyed = destroyed;
	}
	public float getDamage()
	{
		return damage;
	}
	
}
