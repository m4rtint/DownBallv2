package mpht.downball;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;


public class PongActivity extends Activity{

	public static final int MENU_RESUME = Menu.FIRST;
	public static final int MENU_EXIT = Menu.FIRST+1;
	
	//HighScore constant
	public static int highScore;
	public static final String PREFS_NAME = "DownBallPreferences";
	public static final String HIGH_SCORE = "HighScore";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//Retrieves high score
				SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
				highScore = settings.getInt(HIGH_SCORE, 0);
		
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gameactivity);
	}
	
	public void setHighScore(int score)
	{
		highScore = score;
	}
	
	public int getHighScore()
	{
		return highScore;
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
	super.onCreateOptionsMenu(menu);
	menu.add(Menu.NONE,MENU_RESUME,Menu.NONE, "Resume" );
	menu.add(Menu.NONE,MENU_EXIT,Menu.NONE, "Exit Game" );
	return true;
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		SharedPreferences settings = getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();
		editor.putInt(HIGH_SCORE, highScore);
		editor.commit();
		super.onBackPressed();
	}

	@Override
	public boolean onMenuOpened(int featureId, Menu menu)
	{
		SharedPreferences settings = getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();
		editor.putInt(HIGH_SCORE, highScore);
		editor.commit();
		
		GameThread gThread = PongSurfaceView.getGameThread();
		super.onMenuOpened(featureId, menu);
		gThread.paused();
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		GameThread gThread = PongSurfaceView.getGameThread();
		switch(item.getItemId())
		{
		case MENU_RESUME:
		gThread.unpaused();
		return true;
		case MENU_EXIT:
		{Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
		return true;}
		default:
			return super.onOptionsItemSelected(item);
			}
		
	}

	
	
}
