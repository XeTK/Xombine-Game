package Game;

import Misc.Button;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
//are acction listerner for everything
public class MainAL implements OnTouchListener
{
	//hold onto are main class we will need it later
	private MainExecution me;

	//main execution pull the stuff from the other class for are needs
	public MainAL(MainExecution me)
	{
		this.me = me;
	}
	// on acction happerning do this stuff
	public boolean onTouch(View arg0, MotionEvent event)
	{
		// TODO Auto-generated method stub
		switch (event.getAction())
		{
		// we check if we have a action down
			case MotionEvent.ACTION_DOWN:
			{
				//loop through the buttons we have
				for (int i = 0; i < me.getHudObjects().size(); i++)
				{
					//check if the touch is within the bounds of the thing we are interacting with using some rectangles
					if (me.getHudObjects().get(i).getBounds().intersect(new Rect((int) event.getX(),(int) event.getY(),(int) event.getX() + 10, (int) event.getY() + 10)))
					{
						//call the method for the thing to happen this needs tiding up as if we cahnge the index of buttons it will break
						if (((Button) me.getHudObjects().get(i)).getText().equals("<<Left<<"))
						{
							//move the player left
							me.getPlayer().moveLeft();
						}
						else if (((Button) me.getHudObjects().get(i)).getText().equals(">>right>>"))
						{
							//move the player right
							me.getPlayer().moveRight();
						} 
						else if (((Button) me.getHudObjects().get(i)).getText().equals("^^Jump^^"))
						{
							//make the player jump
							me.getPlayer().makeJump();
						} 
						else if (((Button) me.getHudObjects().get(i)).getText().equals("--Shoot--")) // shoot
						{
							//make the player shoot but for now i have a placeholder 
							me.getPlayer().shoot();
						}
						else if (((Button) me.getHudObjects().get(i)).getText().equals("((Reload))")) // reload
						{
							//have some reloading
							me.getPlayer().getGun().setReloaded(true);
						}
						//break the loop once its found its target
						break;
					}
				}
				//break the swtich ready for next command
				break;
			}
			case MotionEvent.ACTION_UP:
			{
				// stuff to be added later
				break;
			}
			case MotionEvent.ACTION_MOVE:
			{
				//^^
				break;
			}
		}
		//for some reason we return false..
		return false;
	}

}
