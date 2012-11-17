package Game;

import android.graphics.Color;
// old class i used for random colours on the ai's will be removed when acctual art is done..
public class RandomNess
{
	public static int RandomColour()
	{
		int r = 1 + (int)(Math.random() * ((10 - 1) + 1));
		if (r ==1)
		{
			return Color.BLUE;
		}
		else if (r ==2)
		{
			return Color.CYAN;
		}
		else if (r ==3)
		{
			return Color.GREEN;
		}
		else if (r == 4)
		{
			return Color.MAGENTA;
		}
		else if (r == 5)
		{
			return Color.LTGRAY;
		}
		else if (r == 6)
		{
			return Color.MAGENTA;
		}
		else if (r == 7)
		{
			return Color.RED;
		}
		else if (r == 8)
		{
			return Color.WHITE;
		}
		else if (r == 9)
		{
			return Color.YELLOW;
		}
		else
		{
			return Color.CYAN;
		}
	}
}
