package mpht.downball;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.view.SurfaceHolder;

public class GameThread extends Thread {
	
	
//Handle to the surface manager object users interact with.
	private SurfaceHolder sHolder;
	private Paint threadPaint;
	private GameState state;
	private boolean isRunning = true;
	
	
	public GameThread(SurfaceHolder sH, Context c,Handler h){
		sHolder = sH;
		threadPaint = new Paint();
		state = new GameState();
	}
	
	@Override
	public void run(){
		while(true){
		while(isRunning)
		{
			Canvas canvas = sHolder.lockCanvas();
			state.update();
			state.draw(canvas,threadPaint);
			sHolder.unlockCanvasAndPost(canvas);	
			
			
			
			
		}
}
}

	//use this in game view to tell gamestate a key has been pressed
	public GameState getGameState(){
		return state;
	}

	public void paused() {
		// TODO Auto-generated method stub
		isRunning = false;
	}
	public void unpaused(){
		isRunning = true;
	}

}
