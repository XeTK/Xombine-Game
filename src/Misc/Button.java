package Misc;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
// the name gives it away this class is for drawing buttons
public class Button extends HudObject
{
	// the posision, and size
	// set some colours
	private Paint inp = new Paint();
	// hold some text
	private String text = "";
	//lets create some shizz here
	public Button(String text,int posX, int posY, int width, int height)
	{
		//interact with are super super class
		super(posX,posY,height,width);
		//set text
		this.text = text;

		//Colours we love colours
		p.setColor(Color.BLACK);
		inp.setColor(Color.GRAY);
	}
	// when we draw do this...
	@Override
	public void onDraw(Canvas c)
	{
		//draw background first
		c.drawRect(posX, posY, posX + width, posY + height, p);
		// then draw box inside it
		c.drawRect(posX + 4, posY + 4, posX + width - 4, posY + height - 4, inp);
		// draw the text on top :P
		c.drawText(text, posX + ((width / 2) - calctextpic(text) /2) , posY + (height /2), p);
	}
	//we need to work out how long the text string is so we can center it in the box
	public int calctextpic(String text)
	{
		//call a method todo that... maby i shouldnt use a void ohwell
		return (int) new Paint().measureText(text);
	}
	public String getText()
	{
		return text;
	}
	
}
