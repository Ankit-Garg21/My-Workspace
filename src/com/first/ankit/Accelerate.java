package com.first.ankit;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Accelerate extends Activity implements SensorEventListener{
	
	float x, y, sensorX, sensorY;
	Bitmap ball;
	SensorManager sM;
	MyClassSurface ourView;

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
				canvas.drawBitmap(ball, ((canvas.getWidth()/2) + (sensorX*20)), ((canvas.getHeight()/2) + (sensorY*20)), null);
				ourHolder.unlockCanvasAndPost(canvas);
			}
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		// Initiate SensorManager
		sM = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		// Check if accelerometer exists on the device
		if(sM.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0){
			
			// Set up a sensor
			Sensor s = sM.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
			
			// register sensor listener.. Returns a boolean value
			sM.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
		}
			ball = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
			x = y = sensorX = sensorY = 0;
			
			ourView = new MyClassSurface(this);
			ourView.resume();
			setContentView(ourView);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		sM.unregisterListener(this);
	}

	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	public void onSensorChanged(SensorEvent arg0) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(16);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/* Get values on sensor change
		 * 0 for x axis
		 * 1 for y axis
		 * 2 for z axis
		*/
		sensorX = arg0.values[0];
		sensorY = arg0.values[1];
	}
}