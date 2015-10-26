package com.first.ankit;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class GFXSurface extends Activity implements OnTouchListener {

	// Create our own view for the animations
	MyClassSurface ourSurfaceView;
	float x,y,sX,sY,fX,fY,dX,dY,aniX,aniY,scaledX,scaledY;
	Bitmap test,plus;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		// Initialize our view
		ourSurfaceView = new MyClassSurface(this);
		ourSurfaceView.setOnTouchListener(this);
		x = 0; y = 0;
		sX = 0;	sY = 0;
		fX = 0;	fY = 0;
		dX = 0;	dY = 0;
		aniX = 0;	aniY = 0;
		scaledX = 0;	scaledY = 0;
		test = BitmapFactory.decodeResource(getResources(), R.drawable.ok);
		plus = BitmapFactory.decodeResource(getResources(), R.drawable.plus);
		setContentView(ourSurfaceView);
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ourSurfaceView.pause();
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ourSurfaceView.resume();
	}
	
	//Define functions to be performed on the basis of events
	public boolean onTouch(View arg0, MotionEvent event) {
		// TODO Auto-generated method stub
		
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		x = event.getX();
		y = event.getY();
		
		switch(event.getAction()){
		
		case MotionEvent.ACTION_DOWN:
			sX = event.getX();
			sY = event.getY();
			dX = dY = aniX = aniY = scaledX = scaledY = fX = fY = 0;
			break;
		
		case MotionEvent.ACTION_UP:
			fX = event.getX();
			fY = event.getY();
			
			dX = fX-sX;
			dY = fY-sY;
			
			scaledX = dX/15;
			scaledY = dY/15;
			
			x = 0;
			y = 0;
			break;
		}
		return true;
	}
	public class MyClassSurface extends SurfaceView implements Runnable {

		// Surface holder contains all our effects  settings separately
		SurfaceHolder ourHolder;
		
		// Thread to run our animations
		Thread ourThread = null;
		
		// Check whether thread is running or not
		boolean isRunning = false;
		
		// Constructor to initialize our view's components
		public MyClassSurface(Context context) {
			// TODO Auto-generated constructor stub
			super(context);
			ourHolder = getHolder();
			
		}

		//Start if thread is paused... Check again and again... If resumes then go to resume()
		public void pause(){
			isRunning = false;
				while(true){
					try {
						ourThread.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
				ourThread = null;
		}
		
		// Start the thread if paused or not running
		public void resume(){
			isRunning = true;
			ourThread = new Thread(this);
			
			// Start the thread
			ourThread.start();
		}
		
		public void run() {
			// TODO Auto-generated method stub
			
			while(isRunning){
				
				// Check if the surface view is valid to use
				if(!ourHolder.getSurface().isValid())
					continue;
				
				// Lock the canvas so no other activity can change effects settings other than this
				Canvas canvas = ourHolder.lockCanvas();
				canvas.drawRGB(10, 250, 10);
				if(x!=0 && y!=0){
					
					// Define image and draw on canvas
					
					canvas.drawBitmap(test, x-(test.getWidth()/2), y-(test.getHeight()/2), null);
				}
				
				if(sX!=0 && sY!=0){
					
					canvas.drawBitmap(plus, sX-(plus.getWidth()/2), sY-(plus.getWidth()/2), null);
				}
				
				if(fX!=0 && fY!=0){
					
					canvas.drawBitmap(test, fX-(test.getWidth()/2)-aniX, fY-(test.getHeight()/2)-aniY, null);
					canvas.drawBitmap(plus, fX-(plus.getWidth()/2), fY-(plus.getWidth()/2), null);
				}
				
				aniX = aniX+scaledX;
				aniY = aniY+scaledY;
				// Unlock canvas and draw on it.
				ourHolder.unlockCanvasAndPost(canvas);
			}
		}
	}
}