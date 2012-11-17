package Game;
import java.util.ArrayList;

import Entities.ObjectItem;
import Entities.Player;
import Misc.HudObject;
import Misc.Statics;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.View;
import Misc.Button;
// main game class basicly controlls everything and contains main loop
public class MainExecution extends View
{
	//hold are drawable entities
	private ArrayList<ObjectItem> drawableItems = new ArrayList<ObjectItem>(); 
	//will be changed to hold all the gui items later for now just holds buttons
	private ArrayList<HudObject> GUIItem = new ArrayList<HudObject>();
	//holds default paint
	private Paint p = new Paint();
	//holds are player object for use to access later
	private Player player = new Player(100, 100);
	//creation void set the activity for the view master class then draw some stuff
	private float score = 0;
	@TargetApi(16)
	public MainExecution(Activity act)
	{
		//set master view class
		super(act);
		setBackgroundColor(Color.WHITE);
		//resize the max screen size as we go off the screen otherwise... dont know why
		Statics.setScreenHeight(Statics.getScreenHeight() - 60);
		//add the player to the drawable list
		drawableItems.add(player);
		//draw are buttons to the screen
		GUIItem.add(new Button("<<Left<<", 10 ,(Statics.getScreenHeight() - 50), 75, 50));
		GUIItem.add(new Button(">>right>>", 90,(Statics.getScreenHeight() - 50), 75, 50));
		GUIItem.add(new Button("((Reload))", Statics.getScreenWidth() - 245 ,(Statics.getScreenHeight() - 50), 75, 50));
		GUIItem.add(new Button("^^Jump^^", Statics.getScreenWidth() - 165 ,(Statics.getScreenHeight() - 50), 75, 50));
		GUIItem.add(new Button("--Shoot--", Statics.getScreenWidth() - 85 ,(Statics.getScreenHeight() - 50), 75, 50));
		//set the line colour to green for some reason
		p.setColor(Color.BLACK);
		//debug spawn some ai's
		for (int i =0; i < 20;i++)
			drawableItems.add(new ObjectItem(Statics.getScreenWidth() - 100,Statics.getScreenHeight() - 300));
		
		
	}
	// main draw method not a fake shitty one like i like to hide in classes
	@Override
	public void onDraw(Canvas c)
	{
		//do stuff for not drawable items yeah i just exploit this loop
		notdrawy();
		//draw are buttons to screen
		for (int i =0; i < GUIItem.size();i++)
			GUIItem.get(i).onDraw(c);
		
		//draw are little seperater from the buttons
		c.drawLine(0, Statics.getScreenHeight() - 60, Statics.getScreenWidth(), Statics.getScreenHeight() - 60, p);
		//print stats
		c.drawText("Score : " + (int)score, 10, 20, p);
		c.drawText("Health : " + (int)player.getHealth() + "%", 10, 40, p);
		c.drawText("ClipRemaining : " + (int)player.getGun().getClipsize(), 10, 60, p);
		//draw any drawable entitie to screen
		for (int i =0; i < drawableItems.size();i++)
			drawableItems.get(i).onDraw(c);
		//then we invalidate what we just drawn so it can be redrawn on a update
		invalidate();
	}
	// non drawy shit so like acctualy checking things are doing things...
	private void notdrawy()
	{
		// check if a flag for a item has been set to see if the item has died and remove it from the arraylist
		for (int i =0; i < drawableItems.size();i++)
			if (drawableItems.get(i).isDead())
			{
				//remove the corps
				drawableItems.remove(i);
				//add to score
				score += +100;
			}
		
		//check if any enterties enter each others bounds and if so means they have collided and will trigger the collision on the entitie
		for (int i = 0; i < drawableItems.size();i++)
			for (int j = 0; j < drawableItems.size();j++)
				if (j != i)
					if (drawableItems.get(j).getBounds().intersect(drawableItems.get(i).getBounds()))
						if (drawableItems.get(i).equals(player))
						{
							drawableItems.get(j).collision();
							drawableItems.get(i).collision();
						}
						else
							drawableItems.get(j).collision();
		
		//sets have bullets
		for (int i = 0; i < player.getGun().getBullets().size();i++)
			for (int j = 0; j<drawableItems.size();j++)
				if (!drawableItems.get(j).equals(player))
					if (drawableItems.get(j).getBounds().intersect(player.getGun().getBullets().get(i).getBounds()))
					{
						drawableItems.get(j).hit(player.getGun().getBullets().get(i).getDamage());
						//tell the bullet it has collided
						player.getGun().getBullets().get(i).collision();
						// we want to add to score
						score++;
					}
		
		// {debug} add new zombies to fight
		if (drawableItems.size() < 20)
			drawableItems.add(new ObjectItem(Statics.getScreenWidth() - 100,Statics.getScreenHeight() - 300));
		
		//update player p
		settarget(player.getPosX(),player.getPosY());
	}
	//spawn a new ai.. kinda debuggy usefulness
	public void createObject(float posX, float posY)
	{
		drawableItems.add(new ObjectItem(posX,posY));
	}
	//set the target for the ai's
	public void settarget(float posX, float posY)
	{
		// go threw all are drawables
		for (int i =0; i < drawableItems.size();i++)
		{
			drawableItems.get(i).setTargetX(posX);
			drawableItems.get(i).setTargetY(posY);
		}
	}
	//getsets everwhere
	public ArrayList<HudObject> getHudObjects()
	{
		return GUIItem;
	}
	public Player getPlayer()
	{
		return player;
	}
	
}

