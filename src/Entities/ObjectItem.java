package Entities;

import java.util.Random;

import Game.RandomNess;
import Misc.Statics;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
//basic ai class gavin eat your heart out
public class ObjectItem
{
	//we have some protected entities :P may be useful
	protected float posX = 100, posY = 100, width = 7, height = 15, volocity = 0, jvolocity = 0, wvolocity = 1, targetX = 0 ,
					targetY = 0,health = 100;
	//and some ints :P
	protected int tick = 0, dir = +1, tickhit = 0, tickmovement = 0, random = 0, randomthrown = 0;
	//some nice paints for later
	protected Paint p = new Paint(), pa = new Paint(), dp = new Paint();
	//some states that will be useful later
	protected boolean jumping = true, thrown = false, falling = false, dead = false, hit = false, attack = false;
	//creation and shit sets all are things up
	private Random rand = new Random();
	public ObjectItem(float posX, float posY)
	{
		this.posX = posX - (width / 2);
		this.posY = posY - (height / 2);
		p.setColor(RandomNess.RandomColour());
		pa.setColor(Color.BLACK);
		dp.setColor(Color.RED);
		wvolocity = rand.nextInt(5 - 1 + 1) + 1;
		randomDir();
	}
	//the life of a object
	public void objectLive()
	{
		//out of bounds then we reset
		if (posX < 0 || (posX + width) >= Statics.getScreenWidth())
			dirflip();
		//^^ same just other dir
		if (posY < 0 || (posY + height) > Statics.getScreenHeight() - 65)
			posY = (Statics.getScreenHeight() - 65) - height;
		//set some death
		if (health <= 0)
			dead = true;
	}
	//gravity you know that thing thats useful to stop us floating
	public void gravity()
	{
		// check if we are greater than the floor
		if ((posY + height) < Statics.getScreenHeight() - 65)
		{
			//make us fall faster
			if (volocity < 5)
				volocity++;
		}
		else
		{
			//else we reset to normal
			volocity = 0;
			falling = false;
		}
		//make gravity work and such
		posY += volocity;
	}
	//jumping we disable gravity temperary
	public void jump()
	{
		// randoms always make things random
		Random rand = new Random();
		//if we have jumping set
		if (jumping)
		{
			// we speed up a bit
			jvolocity++;
			// if random between 5-15
			if (jvolocity > rand.nextInt(15 - 5 + 1) + 5)
			{
				// we set top of the loop then we set the volocity to none and set us falling
				jvolocity = 0;
				jumping = false;
				falling = true;
			}
			//make us move
			posY -= jvolocity;
		}
		//else we just use gravity
		else
			gravity();
	}
	//seaky seak we hunt down the player by hunting for the posision
	public void seak()
	{
		//if the object is being thrown then we don't want to seek
		if (!thrown)//if the object is not mid air then it can seek
		{	
			//if target is in site then move towards it
			if(targetX - posX <= (Statics.getScreenWidth()/8) && targetX - posX >= 0)//to the right
			{
				dir = +1;
			}
			else if(posX - targetX <= (Statics.getScreenWidth()/8) && posX - targetX >= 0)//to the left
			{
				dir = -1;
			}
			//else we move in a direction for a set number of ticks
			else
			{
				if (tickmovement > random)
				{
					// if ticks are up we choose to do another random seak for how wever long
					random = rand.nextInt(Statics.getScreenWidth() - (Statics.getScreenWidth() /4) + 1) + (Statics.getScreenWidth() /4);
					//and choose a random dir
					randomDir();
				}
				else
					tickmovement++;
			}
		}
		//move the object
		posX += dir * wvolocity;
			
	}
	// we choose a random direction to travil
	public void randomDir()
	{
		if (10 < new Random().nextInt((20 - 0 + 1) + 0))
			dir = -1;
		else
			dir = +1;
	}
	//lets have some collision
	public void collision()
	{
		//set the item to be thrown
		thrown = true;
		//flip the direction of the player
		dirflip();
		//if the player is not already jumping or falling make it jump
		if (!jumping&&!falling)
			jumping = true;
	}
	//flip the direction
	private void dirflip()
	{
		if (dir == -1)
			dir = +1;
		else
			dir = -1;
	}
	//throw the object across the screen
	public void itemthrown()
	{
		//random distance to throw the item
		//if the item is set to be thrown
		if (thrown)
		{
			//incroment the tick
			tick++;
			//if the tick is greater than the random number then
			if (tick > randomthrown)
			{
				//reset tick
				tick = 0;
				//clear thrown
				thrown = false;
				//rerandomize
				randomthrown = rand.nextInt(20 - 5 + 1) + 5;
			}
		}
	}
	//draw the thing
	public void onDraw(Canvas canvas)//i believe this gets called repetitively for each incarnation
	{
		//carry out stuff from above
		jump();//makes a spawned zed jump.... not good to have running lots, makes them a jittery bunch, if turned of play will not fall :/
		
		objectLive();// makes sure the zed is within  bounds. good to have running lots
		seak();//makes zeds looks for juicy victims to kill, good to have running lots
		itemthrown();//makes the zeds throw stuff, should only run when triggered
		//draw the shape
		canvas.drawRect(posX, posY, posX + width + 4, posY + height + 4, pa);
		canvas.drawRect(posX + 2, posY + 2, posX + width + 2, posY + height + 2, p);
		
		if (hit)//displays a hit
		{
			//then attempt to draw blood
			canvas.drawCircle(posX + (width /2), posY + (height /2), 5,dp);
			
			//time bloods appearance
			//count the tick
			tickhit++;
			//keep it there till tick to 20
			if (tickhit > 20)
			{
				//clear hit
				hit = false;
				//reset tick
				tickhit = 0;
			}
		}
	}
		
	//on hit take damage and enabled hit
	public void hit(float damage)
	{
		health -= damage;
		hit = true;
	}
	
	//get the bound
	public Rect getBounds()
	{
		return new Rect((int)posX, (int)posY, (int)(posX + width), (int)(posY + height));
	}
	//gets and sets
	public float getPosX()
	{
		return posX;
	}

	public void setPosX(float posX)
	{
		this.posX = posX;
	}

	public float getPosY()
	{
		return posY;
	}

	public void setPosY(float posY)
	{
		this.posY = posY;
	}

	public int getTick()
	{
		return tick;
	}

	public void setTick(int tick)
	{
		this.tick = tick;
	}

	public Paint getP()
	{
		return p;
	}

	public void setP(Paint p)
	{
		this.p = p;
	}

	public float getWidth()
	{
		return width;
	}

	public void setWidth(float width)
	{
		this.width = width;
	}

	public float getHeight()
	{
		return height;
	}

	public void setHeight(float height)
	{
		this.height = height;
	}

	public float getTargetX()
	{
		return targetX;
	}

	public void setTargetX(float targetX)
	{
		this.targetX = targetX;
	}

	public float getTargetY()
	{
		return targetY;
	}

	public void setTargetY(float targetY)
	{
		this.targetY = targetY;
	}

	public boolean isDead()
	{
		return dead;
	}

	public void setDead(boolean dead)
	{
		this.dead = dead;
	}
	public float getHealth()
	{
		return health;
	}
	
}
