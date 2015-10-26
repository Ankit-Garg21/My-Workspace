package com.first.ankit;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

public class GFX extends Activity {

	// Create our own class for view
	MyClass ourView;
	WakeLock wL;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		// Wake-Lock
		PowerManager pM;
		
		// Setting up of power manager
		pM = (PowerManager)getSystemService(Context.POWER_SERVICE);
		
		// Setting up of wake lock so as the screen's light will not diminish while the application is running
		// Uses permission wake lock
		wL = pM.newWakeLock(PowerManager.FULL_WAKE_LOCK, "Ankit");
		super.onCreate(savedInstanceState);
		
		wL.acquire();
		
		// Initialize class object using context
		ourView = new MyClass(this);
		setContentView(ourView);
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		wL.release();
	}
}
