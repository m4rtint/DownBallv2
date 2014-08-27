package mpht.downball;
import java.util.Random;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.view.MotionEvent;




public class GameState{
	DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
	
	int scrWidth = metrics.widthPixels;
	int scrHeight = metrics.heightPixels;
	
	
	//Screen width and height
	final int _screenWidth = scrWidth;
	final int _screenHeight = scrHeight;
	
	//The ball
	final int _ballSize = (int) (scrHeight*0.03);
	int _ballX = _screenWidth/2; 	int _ballY = _screenHeight/2;
	double _ballVelocityX = scrWidth*0.00463; 	double _ballVelocityY = scrHeight*0.0026;

	
	//Top Wall
	final int _topWallY = (int) (scrHeight*0.07);
	
	
	//Bottom Bat
	final int _batLength = (int) (scrWidth*0.27);	final int _batHeight = (int) (scrHeight*0.03);
	int _bottomBatX = (_screenWidth/2);
	final int _bottomBatY = _screenHeight - (int) (scrHeight*0.15);
	
	Rect topWall = new Rect();
	Rect bottomBat = new Rect();
	RectF ball = new RectF();
	
	int i;
	int score;
	PongActivity gScore = new PongActivity();
	Random rand = new Random();
	

public void setBottomBatX(float x){
	_bottomBatX = (int) x;
}
	
public GameState(){}
	

	//The Update Method
	public void update() {
	_ballX += _ballVelocityX;
	_ballY -= _ballVelocityY;
	     
	
	//Collisions With the sides left/right, change velocity
	if(_ballX+_ballSize > _screenWidth || _ballX < 0)
		_ballVelocityX *= -1;                    

	//Collision with Top Wall
	if((_ballY < _topWallY+ _batHeight))   	
       if(i == 0)
       {
       _ballVelocityY *= -1.0;//Collisions with the bats    
       i++;
       }
       else
    	   i=1;
	if(_ballY <_topWallY)
	{_ballY =_topWallY+_batHeight+1;
	_ballVelocityY*=-1.0;
	}
       
	
	//Collisions with Bottom bat
	
	
	if((_ballX+_ballSize >= _bottomBatX-_batLength/2) &&(_ballX-_ballSize <= _bottomBatX+_batLength/2) &&(_ballY+_ballSize >= _bottomBatY-_batHeight/2))
		if(i==1)
		{   
			double randomGen = rand.nextDouble()+1;
			while(randomGen == 1.0 ||randomGen > 1.5){
				randomGen = rand.nextDouble()+1;
			}
		_ballVelocityY *= randomGen*-1;
	    _ballVelocityX *= randomGen;
	    i--;
	    score++;
	    }
		else 
			i=0;
	
	//LOSING , DEATH
	if(_ballY+_ballSize > _bottomBatY+_batHeight*0.25)
	{_ballX = _screenWidth/2;  
	_ballY = _screenHeight/2;
	_ballVelocityX = scrWidth*0.00463;
	_ballVelocityY = scrHeight*0.0026;
	score = 0;}   
	
	if(score > gScore.getHighScore())
		gScore.setHighScore(score);
}
	
	
	public boolean onTouchEvent(MotionEvent e){
		if(e.getY()< _bottomBatY+100 && 
			e.getY() > _bottomBatY-100 ) 
			{
	
		switch(e.getAction()){
		case MotionEvent.ACTION_DOWN:
		{if(e.getX()< scrWidth-_batLength/2 && e.getX()> _batLength/2)
			setBottomBatX(e.getX());
		else if(e.getX()> scrWidth-_batLength/2 && e.getX()<scrWidth)
			setBottomBatX(scrWidth-_batLength/2);
		else
			setBottomBatX(_batLength/2);}
			break;
		case MotionEvent.ACTION_MOVE:
		{if(e.getX()< scrWidth-_batLength/2 && e.getX()> _batLength/2)
			setBottomBatX(e.getX());}
			break;
		case MotionEvent.ACTION_UP:
		{if(e.getX()< scrWidth-_batLength/2 && e.getX()> _batLength/2)
			setBottomBatX(e.getX());}
			break;
			
		}
		}
		return true;
	}
	
	
	
	//The draw method
	public void draw(Canvas canvas, Paint paint) {
	
		//Clear the screen
		canvas.drawRGB(20, 20, 20);
		
		//set the color of bats
		paint.setARGB(200, 0 ,200 , 0);
		
		//color of ball
		Paint redBall = new Paint();
		redBall.setColor(Color.RED);
		
		//draw the ball
		ball.set(_ballX,_ballY,_ballX + _ballSize,_ballY + _ballSize);
		canvas.drawOval(ball, redBall);
		
		
		
		//draw the bats
		topWall.set(0, _topWallY, scrWidth, _topWallY + _batHeight);
		bottomBat.set(_bottomBatX-_batLength/2, _bottomBatY- _batHeight/2, _bottomBatX + _batLength/2, _bottomBatY + _batHeight/2);
		canvas.drawRect(topWall, paint); //top bat
		canvas.drawRect(bottomBat, paint); //bottom bat
		
		//colour of text
		Paint whiteScore = new Paint();
		whiteScore.setColor(Color.WHITE);
		whiteScore.setTextSize((float) (scrHeight*0.03));
		
		int hScore = gScore.getHighScore();

		canvas.drawText("Amount of Hits :"+score, 0, 50, whiteScore);
		canvas.drawText("HighScore :"+hScore, scrWidth/2, 50, whiteScore);
	}
}
