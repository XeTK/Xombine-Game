package Misc;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class HudObject
{
	protected float posX = 0, posY = 0 , height = 0 , width = 0;
	protected Paint p = new Paint();
	public HudObject(float posX,float posY,float height, float width)
	{
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
	}
	public Rect getBounds()
	{
		return new Rect((int)posX, (int)posY, (int)(posX + width), (int)(posY + height));
	}
	public void onDraw(Canvas c)
	{
		c.drawRect(posX,posY,posX+width,posY+height, p);
	}
}
