package mpht.downball;


import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

//SurfaceView - provides a drawing surface
//surfacehlder.callback - recieves information if there are any changes to the
//surface when using with "surfaceview".
public class PongSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

	public static GameThread gameThread;
	
	public PongSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		//To listen to events
		SurfaceHolder holder = getHolder();
		holder.addCallback(this);
		setFocusable(true);
		
		//instantiate thread
		gameThread = new GameThread(holder,context, new Handler());
	}
	
	
	public boolean onTouchEvent(MotionEvent event){
		return gameThread.getGameState().onTouchEvent(event);
	}


	//Part of the SurfaceHolder.Callback interface
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	//Part of the SurfaceHolder.Callback interface
	//When the surface is first created
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		gameThread.start();
		
	}

	//Part of the SurfaceHolder.Callback interface
	//when surface is destroyed
	@SuppressWarnings("deprecation")
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		gameThread.stop();
		
	}

	public static GameThread getGameThread() {
		// TODO Auto-generated method stub
		return gameThread;
	}

	

}
