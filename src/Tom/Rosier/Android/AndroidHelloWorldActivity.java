package Tom.Rosier.Android;

import Game.MainAL;
import Game.MainExecution;
import Misc.Statics;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.widget.Button;

public class AndroidHelloWorldActivity extends Activity {
    /** Called when the activity is first created. */
	private Activity act;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        
		// aparently this is super
        super.onCreate(savedInstanceState);
        //set content view i dont know it was already there
        setContentView(R.layout.main);
        act = this;
        Button buttonStart = (Button)findViewById(R.id.button1);        
        buttonStart.setOnClickListener(startListener); // Register the onClick listener with the implementation above
    }
  //Create an anonymous implementation of OnClickListener
    private OnClickListener startListener = new OnClickListener() {

		public void onClick(View arg0)
		{
			// TODO Auto-generated method stub
	        Display display = getWindowManager().getDefaultDisplay();
	        //make a new point
	        Point size = new Point();
	        //get the sixe
	        display.getSize(size);
	        //set the statics to the size
	        Statics.setScreenWidth(size.x);
	        Statics.setScreenHeight(size.y);
	        //create new main thread
	        MainExecution me = new MainExecution(act);
	        //add a touch listener to it
	        me.setOnTouchListener(new MainAL(me));
	        // set the content to be set to the main class
	        setContentView(me);
		}
    };

}