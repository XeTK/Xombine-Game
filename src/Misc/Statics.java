package Misc;
// this class is for holding are horible static variables.... :'(
public class Statics
{
	// nice static screen size
	private static int screenWidth = 0, screenHeight = 0;

	// and some getsets
	public static int getScreenWidth()
	{
		return screenWidth;
	}

	public static void setScreenWidth(int screenWidth)
	{
		Statics.screenWidth = screenWidth;
	}

	public static int getScreenHeight()
	{
		return screenHeight;
	}

	public static void setScreenHeight(int screenHeight)
	{
		Statics.screenHeight = screenHeight;
	}
	
}
