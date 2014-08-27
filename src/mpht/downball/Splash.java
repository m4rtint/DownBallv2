package mpht.downball;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class Splash extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState){
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		Thread logoTimer = new Thread(){;
		
		public void run(){
			try {
				int logoTimer = 1000;
				while(logoTimer > 0){
					sleep(100);
					logoTimer= logoTimer - 100;
				}
				startActivity(new Intent("com.example.pongv1.MainActivity"));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				finish();
				}	
		}
	};
	logoTimer.start();
	}
}
